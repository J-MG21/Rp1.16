package ca.kronoxx.RP;

import Database.Database;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import java.lang.management.PlatformLoggingMXBean;

public class ServerSave {
    private static ServerSave instace;
    private Database db;

    private ServerSave(){
        String password = System.getenv("dbPassword");
        String user = System.getenv("dbUser");
        this.db = new Database("jdbc:mysql://localhost:3306/minrp", user, password);
        db.open();
    }

    public static ServerSave getInstance(){
        if(instace ==  null){
            instace = new ServerSave();
        }
        return instace;
    }

    public void loadUser(RPPlayer player){
        db.query("select uuid, health, job from Player");
        if(db.getResult("uuid") != null){
            registerUser(player.getPlayer(), "none");
        }
        player.loadValue(db.getResult("job"), Integer.parseInt(db.getResult("health")));
        db.closeResult();

    }

    public boolean savePlayerOnDb(RPPlayer player){
        return db.update("update Player set health = ?, job = ?", player.getHealth(), player.getJob()) == 0;
    }

    private void registerUser(Player player, String job){
        db.update("insert into Player (uuid, job, health) values (?, ?, ?)", player.getUniqueId().toString(), job, 10);
        player.getUniqueId();
    }
}
