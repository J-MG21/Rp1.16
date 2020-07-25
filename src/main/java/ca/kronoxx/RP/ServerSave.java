package ca.kronoxx.RP;

import Database.Database;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import java.lang.management.PlatformLoggingMXBean;

public class ServerSave {
    private Database db;

    public ServerSave(){
        String password = System.getenv("dbPassword");
        String user = System.getenv("dbUser");
        this.db = new Database("jdbc:mysql://localhost:3306/minrp", user, password);
        db.open();
    }

    public RPPlayer loadUser(Player player){
        db.query("select uuid, health, job from Player");
        if(db.getResult("uuid") != null){
            registerUser(player.getPlayer(), "none");
        }
        RPPlayer rp = new RPPlayer(player, db.getResult("job"), Integer.parseInt(db.getResult("health")));
        db.closeResult();
        return rp;
    }

    public boolean savePlayerOnDb(RPPlayer player){
        return db.update("update Player set health = ?, job = ?", player.getHealth(), player.getJob()) == 0;
    }

    private void registerUser(Player player, String job){
        db.update("insert into Player (uuid, job, health) values (?, ?, ?)", player.getUniqueId().toString(), job, 10);
        player.getUniqueId();
    }
}
