package ca.kronoxx.RP.Bukkit.listener;

import ca.kronoxx.RP.RPPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PlayerConection {
    private ArrayList<RPPlayer> rpPlayers = new ArrayList<RPPlayer>();

    public void addPlayer(Player player){
                rpPlayers.add(new RPPlayer(player));
    }

    public void removePlayer(Player player){
        RPPlayer rpP = new RPPlayer(player);
        rpP.savePlayer();
        rpPlayers.remove(rpP);
    }


    //GETTER
    public RPPlayer getRpPlayer(Player player){
        for(RPPlayer rpPlayer: rpPlayers){
            Player p = rpPlayer.getPlayer();
            if(p == player){
                return rpPlayer;
            }
        }
        return null;
    }
}
