package ca.kronoxx.RP.Bukkit.listener;

import ca.kronoxx.RP.Taks.Timer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.EulerAngle;
import java.util.List;


public class Mort implements Listener {
    private Location location;
    private ItemStack[] inv;

    @EventHandler
    public void playerDeath(PlayerDeathEvent e){
        e.setDeathMessage("");
        Player dP = e.getEntity();
        playerSave(dP, e.getDrops());
        dP.setGameMode(GameMode.SPECTATOR);
        spawnArmorStand(dP);
        time();
    }

    private void playerSave(Player player, List<ItemStack> drops){

        location = player.getLocation();
        inv = player.getInventory().getArmorContents();

        for(ItemStack i: inv){
            if(i != null){
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


    private void time(){
        Timer timer = new Timer();
        timer.runTaskTimer((Plugin)this, 0, 40);
    }

}
