package ca.kronoxx.RP.Bukkit.listener;

import ca.kronoxx.RP.Main;
import ca.kronoxx.RP.RPPlayer;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.Collection;
import java.util.HashMap;


public class PlayerListener implements Listener {
    public PlayerListener(){}

    @EventHandler
    private void playerJoin(PlayerJoinEvent pje){
        pje.setJoinMessage("");
        Main.getInstance().getRpCommands().useRumor(pje.getPlayer());
        Main.getInstance().loadNewPlayerInGame(pje.getPlayer());


    }

    @EventHandler
    private void playerLeave(PlayerQuitEvent pqe){
        pqe.setQuitMessage("");
        Main.getInstance().removePlayerFromGame(pqe.getPlayer());
    }

    @EventHandler
    private void playerChat(AsyncPlayerChatEvent pce){
        if(pce.getPlayer().isOp()){
            Bukkit.broadcastMessage("§4[Modérateur] §4" + pce.getPlayer().getName() + "§e > " +pce.getMessage());
        }else{
            pce.getPlayer().sendMessage("§cLe chat est désactivé");
            pce.getPlayer().sendMessage("§cFaites la commande /alert <msg> pour report un problème");
        }
        pce.setCancelled(true);
    }

    @EventHandler
    public void onSplash(PotionSplashEvent e){
        ItemStack healPotion = e.getPotion().getItem();

        if(healPotion.getItemMeta().getDisplayName().equals(Main.getInstance().getNewCrafts().HealPotion.getHealPotion().getItemMeta().getDisplayName())){
             Collection<LivingEntity> entitees = e.getAffectedEntities();
             for(Entity p: entitees){
                 if(p instanceof Player){
                     //TODO rpPlayer
                     RPPlayer rpPlayer = Main.getInstance().getRpPlayer((Player) p);
                     int vie = rpPlayer.getHealth();
                     rpPlayer.permaHeal(2);

                     ((Player) p).setHealthScale(vie+2);
                     ((Player) p).setHealth(vie+2);
                 }
             }
        }

    }
}

