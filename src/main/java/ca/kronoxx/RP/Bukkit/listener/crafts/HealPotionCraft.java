package ca.kronoxx.RP.Bukkit.listener.crafts;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class HealPotionCraft {
        ItemStack customHealPotion = new ItemStack(Material.SPLASH_POTION);
        ItemMeta healPotion = customHealPotion.getItemMeta();


        private void addMeta(){
            healPotion.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1,true);
            healPotion.setDisplayName("Potion de vie");
            healPotion.setLore(Arrays.asList("Cette potion permet de", "regagner des coeurs", "de façon permanente"));
            customHealPotion.setItemMeta(healPotion);
        }

        public ItemStack getHealPotion(){
            addMeta();
            return customHealPotion;
        }



}
