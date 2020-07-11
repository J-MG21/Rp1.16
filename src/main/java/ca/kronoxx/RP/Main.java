package ca.kronoxx.RP;

import ca.kronoxx.RP.Bukkit.commands.Cmd;
import org.bukkit.plugin.java.JavaPlugin;
import ca.kronoxx.RP.Bukkit.listener.*;

public class Main extends JavaPlugin {

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {
        getCommand("test").setExecutor(new Cmd());
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

    }

    @Override
    public void onDisable() {

    }
}
