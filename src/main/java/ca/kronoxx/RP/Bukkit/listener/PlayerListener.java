package ca.kronoxx.RP.Bukkit.listener;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public abstract class PlayerListener implements Listener {


    @Override
    public abstract void onPlayerJoin(PlayerJoinEvent event);
}
