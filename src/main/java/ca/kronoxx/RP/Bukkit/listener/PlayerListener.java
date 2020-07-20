package ca.kronoxx.RP.Bukkit.listener;

import ca.kronoxx.RP.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;



public class PlayerListener implements Listener {
    private Main main;

    public PlayerListener(Main main){
        this.main = main;
    }


    @EventHandler
    private void playerJoin(PlayerJoinEvent pje){
        pje.setJoinMessage("");
        main.playerConection().addPlayer(pje.getPlayer());
    }

    @EventHandler
    private void playerLeave(PlayerQuitEvent pqe){
        pqe.setQuitMessage("");
        main.playerConection().removePlayer(pqe.getPlayer());
    }

    @EventHandler
    private void playerChat(AsyncPlayerChatEvent pce){
        if(pce.getPlayer().isOp()){
            Bukkit.broadcastMessage("§4[Modérateur] §4" + pce.getPlayer().getName() + "§e > " +pce.getMessage());
        }
        else{
            pce.getPlayer().sendMessage("§cLe chat est désactivé");
            pce.getPlayer().sendMessage("§cFaites la commande /alert <msg> pour report un problème");
        }
        pce.setCancelled(true);
    }
}

