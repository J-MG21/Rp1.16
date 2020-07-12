package ca.kronoxx.RP;

import ca.kronoxx.RP.Bukkit.commands.Cmd;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import ca.kronoxx.RP.Bukkit.listener.*;


public class Main extends JavaPlugin {

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {
        getCommand("job").setExecutor(new Cmd());
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new Mort(), this);






        ItemStack ironHelmet = new ItemStack(Material.IRON_HELMET);
        //crerate receipe
        ShapedRecipe rIronHelmet = new ShapedRecipe(ironHelmet);

        //receipeShape
        rIronHelmet.shape("***", "* *", "   ");

        rIronHelmet.setIngredient('*', Material.IRON_BLOCK);
        getServer().addRecipe(rIronHelmet);

    }

    @Override
    public void onDisable() {

    }
}
