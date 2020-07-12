package ca.kronoxx.RP;

import Database.Database;
import ca.kronoxx.RP.Bukkit.commands.Cmd;;
import org.bukkit.plugin.java.JavaPlugin;
import ca.kronoxx.RP.Bukkit.listener.*;


public class Main extends JavaPlugin {

    /*
    private Database database;
    @Override
    public void onLoad() {
        String password = System.getenv("dbPassword");
        String user = System.getenv("dbUser");
        this.database = new Database("jdbc:mysql://localhost:3306/minrp", user, password);
        database.open();
    }
    */


    @Override
    public void onEnable() {
        getCommand("job").setExecutor(new Cmd());
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new Mort(), this);

        new newCrafts();

    }

    @Override
    public void onDisable() {

    }
}
