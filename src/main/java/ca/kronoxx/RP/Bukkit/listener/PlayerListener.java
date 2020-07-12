package ca.kronoxx.RP.Bukkit.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerListener implements Listener {

    @EventHandler
    private void playerJoin(PlayerJoinEvent pje){
        pje.setJoinMessage("");

    }

    @EventHandler
    private void playerLeave(PlayerQuitEvent pqe){
        pqe.setQuitMessage("");
    }

    @EventHandler
    private void playerChat(AsyncPlayerChatEvent pce){
        pce.getPlayer().sendMessage("§cLe chat est désactivé");
        pce.getPlayer().sendMessage("§cFaites la commades /alert <msg> pour report un problème");
        pce.setCancelled(true);

    }

}
