package ca.kronoxx.RP.Bukkit.commands.cmd;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class RumorManager {
    public static final double milisToMin = 60000;
    private ArrayList<String> rumors;
    private int delayH;
    private Date nextRumorWipe;

    public RumorManager(){
        rumors = new ArrayList<>();
    }

    public void addRumor(String rumor){
        rumors.add(rumor);
    }

    public void addRumor(String[] rumors){
        StringBuilder bc = new StringBuilder();
        for(String part : rumors) {
            if(part.equalsIgnoreCase("add")){
                part = " ";
            }
            bc.append(part + " ");
        }
        this.rumors.add(bc.toString());
    }

    public void displayRumors(){
        Bukkit.broadcastMessage("§2Rumeurs:");
        int length = rumors.size();
        for (int i=0; i<length; ++i){
            Bukkit.broadcastMessage(rumors.get(i));
        }
    }

    public void displayRumorsConnection(Player player){
        player.sendMessage("§2Voici les rumeurs qui courent en ville:");
        int length = rumors.size();
        for (int i=0; i<length; ++i){
            player.sendMessage(rumors.get(i));
        }
    }

    public void cleanRumors(){
        rumors = new ArrayList<>();
    }
}
