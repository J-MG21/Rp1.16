package ca.kronoxx.RP.Bukkit.listener;

import ca.kronoxx.RP.Bukkit.listener.crafts.HealPotionCraft;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class newCrafts  {
    private ArrayList<Recipe> recipes = new ArrayList<Recipe>();
    private ArrayList<String> noneRecipes = new ArrayList<String>();
    public HealPotionCraft HealPotion = new HealPotionCraft();

    public ArrayList<Recipe> newRecipe(){
        //create ItemStack***********************************************************************************************
        ItemStack ironHelmet = new ItemStack(Material.IRON_HELMET);
        ItemStack ironChestplate = new ItemStack(Material.IRON_CHESTPLATE);
        ItemStack ironLeggings = new ItemStack(Material.IRON_LEGGINGS);
        ItemStack ironBoots = new ItemStack(Material.IRON_BOOTS);

        ItemStack ironBars = new ItemStack(Material.IRON_BARS, 3);

        ItemStack chainHelmet = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemStack chainChestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemStack chainLeggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        ItemStack chainBoots = new ItemStack(Material.CHAINMAIL_BOOTS);

        ItemStack healPotion = HealPotion.getHealPotion();



        //create receipe***********************************************************************************************
        ShapedRecipe rIronHelmet = new ShapedRecipe(ironHelmet);
        ShapedRecipe rIronChestplate = new ShapedRecipe(ironChestplate);
        ShapedRecipe rIronLeggings = new ShapedRecipe(ironLeggings);
        ShapedRecipe rIronBoots = new ShapedRecipe(ironBoots);

        ShapedRecipe rIronBars = new ShapedRecipe(ironBars);

        ShapedRecipe rChainHelmet = new ShapedRecipe(chainHelmet);
        ShapedRecipe rChainChestplate = new ShapedRecipe(chainChestplate);
        ShapedRecipe rChainLeggings = new ShapedRecipe(chainLeggings);
        ShapedRecipe rChainBoots = new ShapedRecipe(chainBoots);

        ShapedRecipe rHealPotion = new ShapedRecipe(healPotion);

        //receipeShape***********************************************************************************************
        rIronHelmet.shape("***", "* *");
        rIronChestplate.shape("* *", "***", "***");
        rIronLeggings.shape("***", "* *", "* *");
        rIronBoots.shape("* *", "* *");

        rIronBars.shape("***","***");

        rChainHelmet.shape("***", "* *");
        rChainChestplate.shape("* *", "***", "***");
        rChainLeggings.shape("***", "* *", "* *");
        rChainBoots.shape("* *", "* *");

        rHealPotion.shape("*/*","454","343");


        //recipesIngredients***********************************************************************************************
        rIronHelmet.setIngredient('*', Material.IRON_BLOCK);
        rIronChestplate.setIngredient('*', Material.IRON_BLOCK);
        rIronLeggings.setIngredient('*', Material.IRON_BLOCK);
        rIronBoots.setIngredient('*', Material.IRON_BLOCK);

        rIronBars.setIngredient('*', Material.IRON_INGOT);

        rChainHelmet.setIngredient('*', Material.IRON_BARS);
        rChainChestplate.setIngredient('*', Material.IRON_BARS);
        rChainLeggings.setIngredient('*', Material.IRON_BARS);
        rChainBoots.setIngredient('*', Material.IRON_BARS);

        rHealPotion.setIngredient('*', Material.ROTTEN_FLESH);
        rHealPotion.setIngredient('/', Material.HEART_OF_THE_SEA);
        rHealPotion.setIngredient('4', Material.HONEY_BOTTLE);
        rHealPotion.setIngredient('5', Material.POTION);
        rHealPotion.setIngredient('3', Material.GOLD_BLOCK);

        //addRecipes***********************************************************************************************
        recipes.add(rIronHelmet);
        recipes.add(rIronChestplate);
        recipes.add(rIronLeggings);
        recipes.add(rIronBoots);

        recipes.add(rIronBars);

        recipes.add(rChainHelmet);
        recipes.add(rChainChestplate);
        recipes.add(rChainLeggings);
        recipes.add(rChainBoots);

        recipes.add(rHealPotion);

        return recipes;
    }

    public ArrayList<String> nRecipe(){
        //create None recipes
        noneRecipes.add("iron_helmet");
        noneRecipes.add("iron_chestplate");
        noneRecipes.add("iron_leggings");
        noneRecipes.add("iron_boots");

        return noneRecipes;
    }

    /*private ItemStack createHealPotion(){
        ItemStack customHealPotion = new ItemStack(Material.SPLASH_POTION);
        ItemMeta healPotion = customHealPotion.getItemMeta();

        healPotion.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1,true);
        healPotion.setDisplayName("Potion de vie");
        healPotion.setLore(Arrays.asList("Cette potion permet de", "regagner des coeurs de façon permanente"));
        customHealPotion.setItemMeta(healPotion);

        return customHealPotion;
    }*/


   /* public void removeRecipeByKey(String recipeKey) {
        MinecraftKey key = new MinecraftKey(recipeKey);
        for (Object2ObjectLinkedOpenHashMap<MinecraftKey, IRecipe<?>> recipes : ((CraftServer) Bukkit.getServer()).getServer().getCraftingManager().recipes.values()) {
            recipes.remove(key);
        }
    }*/

}
