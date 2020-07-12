package ca.kronoxx.RP;

import Database.Database;
import ca.kronoxx.RP.Bukkit.commands.Cmd;;
import net.minecraft.server.v1_16_R1.IRecipe;
import net.minecraft.server.v1_16_R1.MinecraftKey;
import net.minecraft.server.v1_16_R1.ShapedRecipes;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import org.bukkit.craftbukkit.v1_16_R1.CraftServer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import ca.kronoxx.RP.Bukkit.listener.*;

import java.util.ArrayList;
import java.util.Collection;


public class Main extends JavaPlugin {
    private newCrafts crafts;

    @Override
    public void onLoad() {
        crafts = new newCrafts();
    }


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
