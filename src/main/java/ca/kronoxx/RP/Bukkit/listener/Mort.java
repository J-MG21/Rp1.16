package ca.kronoxx.RP.Bukkit.listener;

import net.minecraft.server.v1_16_R1.DataWatcher;
import net.minecraft.server.v1_16_R1.EntityPose;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.EulerAngle;

import java.util.ArrayList;
import java.util.List;


public class Mort implements Listener {
    private Location location;
    private ItemStack[] inv;


    @EventHandler
    public void playerDeath(PlayerDeathEvent e){
        e.setDeathMessage("aiuydbaiubdaiwbdaiuwbdaiwubdiuawbdawiudbaiubdawiu");
        Player dP = e.getEntity();


        playerSave(dP, e.getDrops());
        dP.setGameMode(GameMode.SPECTATOR);
        spawnArmorStand(dP);

    }

    private void playerSave(Player player, List<ItemStack> drops){

        location = player.getLocation();
        inv = player.getInventory().getArmorContents();

        for(ItemStack i: inv){
            if(i != null){
                Bukkit.broadcastMessage("Lala");
                drops.remove(i);
                Bukkit.broadcastMessage(String.valueOf(inv.length));
            }
        }
    }

    private void notMove(Player player){

    }

    private void spawnArmorStand(Player player){
        ArmorStand body = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        body.setHelmet(inv[3]);
        body.setChestplate(inv[2]);
        body.setLeggings(inv[1]);
        body.setBoots(inv[0]);

        body.setCustomName(player.getName());
        body.setCustomNameVisible(true);
        body.setArms(true);
        body.setLeftArmPose(new EulerAngle(0,Math.toRadians(90),0));
        body.setRightArmPose(new EulerAngle(0,Math.toRadians(90),0));
        body.setBasePlate(false);
    }

}
