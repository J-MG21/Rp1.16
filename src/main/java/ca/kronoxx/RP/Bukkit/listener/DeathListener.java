package ca.kronoxx.RP.Bukkit.listener;

import ca.kronoxx.RP.Main;
import ca.kronoxx.RP.RPPlayer;
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
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.EulerAngle;
import java.util.List;


public class DeathListener implements Listener {
    private Location location;
    private ItemStack[] inv;

    public DeathListener(){}

    @EventHandler
    public void playerDeath(PlayerDeathEvent e){
        Player dP = e.getEntity();
        RPPlayer player = Main.getInstance().getRpPlayer(dP);
        e.setDeathMessage("");
        spawnDeadBody(dP, e.getDrops());
        player.permaDamage(2);
        //TODO player.permaDamage already do this function(I just can't test it works to reduce health)
        //dP.setHealthScale(5d);
        dP.setMaxHealth(player.getHealth());
        Bukkit.broadcastMessage(Integer.toString(player.getHealth()));
    }

    private void spawnDeadBody(Player dP, List<ItemStack> drops){
        playerSave(dP, drops);
        //dP.setGameMode(GameMode.SPECTATOR);
        spawnArmorStand(dP);
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
