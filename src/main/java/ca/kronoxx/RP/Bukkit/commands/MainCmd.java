package ca.kronoxx.RP.Bukkit.commands;

import ca.kronoxx.RP.Bukkit.commands.cmd.Rumor;
import ca.kronoxx.RP.Main;
import ca.kronoxx.RP.RPPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;


public class MainCmd implements CommandExecutor {
    private Main main;
    private Rumor msgVillage;
    private ArrayList<String> messages = new ArrayList<String>();
    String messageIntro = "§2Certaines rumeures courent dans le village:";

    public MainCmd(Main main){
        this.main = main;
    }
    public MainCmd(Rumor msgVillage){
        this.msgVillage = msgVillage;
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        RPPlayer rpPlayer = main.playerConection().getRpPlayer((Player) sender);
        Player p = rpPlayer.getPlayer();

        //job
        if(label.equalsIgnoreCase("job")){
            if(args.length <= 0){
                p.sendMessage("§cFaites la commandes /job <set/remove> <player name> <job>");
                return true;
            }
            String playerName = args[1];
            String newJob = args[2];

            if(args[0].equalsIgnoreCase("set")){
                p.sendMessage("§aLe joueur §b" + playerName + " §a est maintenant §b" + newJob);
                rpPlayer.changeJob(newJob);
                p.sendMessage("§aVous êtes maintenant §b" + newJob);
                return true;
            }
            //job set <player> <job>

            //job remove <player>
        }

        //health
        if(label.equalsIgnoreCase("health")){
            Bukkit.broadcastMessage("test");
            /*
            for(Player player: Bukkit.getOnlinePlayers()){
                if(player.getName().equalsIgnoreCase(args[0])){
                    RPPlayer pH = main.playerConection().getRpPlayer(player);
                    int vie = Integer.parseInt(args[1]);
                    player.setHealthScale(vie);
                    pH.setHealth(vie);
                    return true;
                }
            }
            */

        }

        //alert
        if(label.equalsIgnoreCase("alert")){
                if(args.length == 0) {
                    p.sendMessage("§cVous devez écrire /alert <message> pour report un problème");
                    return true;
                }
                if(args.length >= 1) {
                    StringBuilder bc = new StringBuilder();
                    for(String part : args) {
                        bc.append(part + " ");
                    }
                    for(Player pl: Bukkit.getOnlinePlayers()){
                        if(pl.isOp()){
                            pl.sendMessage("§b" + p.getName() +" §c" + bc.toString());
                        }
                    }
                    p.sendMessage("§aVous avez bien envoyé votre problème. Merci !!");
                    return true;
                }
            }

        //rumeure
        if(label.equalsIgnoreCase("rumeure")){
            if(args.length == 0){
                return true;
            }
            if(args[0].equalsIgnoreCase("add")){
                StringBuilder bc = new StringBuilder();
                for(String part : args) {
                    if(part.equalsIgnoreCase("add")){
                        part = " ";
                    }
                    bc.append(part + " ");
                }
                addMessage(bc.toString());
                return true;
            }
            if(args[0].equalsIgnoreCase("send")){
                for(Player player:Bukkit.getOnlinePlayers()){
                    sendMessage(player);
                }
            }
        }
        return false;
    }

    public void addMessage(String msg){
        messages.add(msg);
    }

    public void sendMessage(Player player){
        player.sendMessage(messageIntro);
        for(String s: messages){
            player.sendMessage("§a- " + s);
        }
    }
    public String msgVillage(){
        return messages.toString();
    }
}
