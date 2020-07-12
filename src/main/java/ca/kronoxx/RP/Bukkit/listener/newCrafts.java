package ca.kronoxx.RP.Bukkit.listener;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_16_R1.block.impl.CraftAnvil;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;


public class newCrafts extends JavaPlugin {

    public void newRecipe(){
        //create ItemStack
        ItemStack ironHelmet = new ItemStack(Material.IRON_HELMET);
        ItemStack ironChestplate = new ItemStack(Material.IRON_CHESTPLATE);
        ItemStack ironLeggings = new ItemStack(Material.IRON_LEGGINGS);
        ItemStack ironBoots = new ItemStack(Material.IRON_BOOTS);


        //crerate receipe
        ShapedRecipe rIronHelmet = new ShapedRecipe(ironHelmet);
        ShapedRecipe rIronChestplate = new ShapedRecipe(ironChestplate);
        ShapedRecipe rIronLeggings = new ShapedRecipe(ironLeggings);
        ShapedRecipe rIronBoots = new ShapedRecipe(ironBoots);



        //receipeShape
        rIronHelmet.shape("***", "* *", "  ");
        rIronChestplate.shape("* *", "***", "***");
        rIronLeggings.shape("***", "* *", "* *");
        rIronBoots.shape("   ", "* *", "* *");


        rIronHelmet.setIngredient('*', Material.IRON_BLOCK);
        rIronChestplate.setIngredient('*', Material.IRON_BLOCK);
        rIronLeggings.setIngredient('*', Material.IRON_BLOCK);
        rIronBoots.setIngredient('*', Material.IRON_BLOCK);

        getServer().addRecipe(rIronHelmet);
        getServer().addRecipe(rIronChestplate);
        getServer().addRecipe(rIronLeggings);
        getServer().addRecipe(rIronBoots);
    }

    private void removeRecipe(){
        getServer().getRecipesFor(new ItemStack(Material.IRON_HELMET));


    }






}
