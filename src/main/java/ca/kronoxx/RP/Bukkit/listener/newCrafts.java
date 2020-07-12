package ca.kronoxx.RP.Bukkit.listener;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_16_R1.block.impl.CraftAnvil;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;


public class newCrafts extends JavaPlugin {

    private void newRecipe(){
        //create ItemStack
        ItemStack ironHelmet = new ItemStack(Material.IRON_HELMET);


        //crerate receipe
        ShapedRecipe rIronHelmet = new ShapedRecipe(ironHelmet);



        //receipeShape
        rIronHelmet.shape("***", "***", "***");


        rIronHelmet.setIngredient('*', Material.IRON_BLOCK);
        getServer().addRecipe(rIronHelmet);
    }

    private void removeRecipe(){
        getServer().getRecipesFor(new ItemStack(Material.IRON_HELMET));


    }






}
