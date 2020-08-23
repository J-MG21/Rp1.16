package ca.kronoxx.RP;

import Database.Database;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import javax.print.DocFlavor;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.management.PlatformLoggingMXBean;
import java.util.Scanner;

public class ServerSave {
    private Database db;

    public ServerSave(){
        String[] auths = setUserAndPass("/var/auth.txt");
        this.db = new Database("jdbc:mysql://localhost:3306/minrp", auths[0], auths[1]);
        db.open();
    }

    public RPPlayer loadUser(Player player){
        db.query("select uuid, health, job from Player where uuid = ?", player.getUniqueId().toString());
        if(db.getResult(1) == null){
            registerUser(player.getPlayer(), "none");
            db.query("select uuid, health, job from Player where uuid = ?", player.getUniqueId().toString());
        }
        if (db.nextResult()){
            RPPlayer rp = new RPPlayer(player, db.getResult(3), Integer.parseInt(db.getResult(2)));
            System.out.println("job:" + db.getResult(3) + "\nhealth:" + db.getResult(2) + "\nuuid:" + db.getResult(1) + "\n");
            db.closeResult();
            return rp;
        }
        db.closeResult();
        return null;
    }

    public boolean savePlayerOnDb(RPPlayer player){
        return db.update("update Player set health = ?, job = ?", player.getHealth(), player.getJob()) == 0;
    }

    private void registerUser(Player player, String job){
        db.update("insert into Player (uuid, job, health) values (?, ?, ?)", player.getUniqueId().toString(), job, 10);
        player.getUniqueId();
        System.out.println("Saved new player " + player.getDisplayName());
    }

    private String[] setUserAndPass(String filePath){
        String[] auths = new String[2];
        try {
            File f = new File(filePath);
            Scanner scan = new Scanner(f);
            if (scan.hasNextLine()) {
                auths[0] = scan.nextLine();
                auths[1] = scan.nextLine();
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred can not load credentials from file");
            e.printStackTrace();
        }
        return auths;
    }
}