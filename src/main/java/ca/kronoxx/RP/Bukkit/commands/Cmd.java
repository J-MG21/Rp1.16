package ca.kronoxx.RP.Bukkit.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;


public class Cmd implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        //job
        Player player = (Player)sender;

        if(label.equalsIgnoreCase("job")){
            if(args.length <= 0){
                player.sendMessage("§cFaites la commandes /job <set/remove> <player name> <job>");
                return true;
            }
            String playerName = args[1];
            String newJob = args[2];

            if(args[0].equalsIgnoreCase("set")){
                player.sendMessage("§aLe joueur §b" + playerName + " §a est maintenant §b" + newJob);
                //changer pour le joueur en question    player.sendMessage("§aVous êtes maintenant §b" + newJob);
                return true;
            }
            else if(args[0].equalsIgnoreCase("remove")){
                player.sendMessage("§aLe joueur §b" + playerName + " §a n'est plus §b" + newJob);
                //changer pour le joueur en question    player.sendMessage("§aVous n'êtes plus §b" + newJob);
                return true;
            }
            //job set <player> <job>

            //job remove <player>

        }
            //alert
            if(label.equalsIgnoreCase("alert")){
                if(args.length == 0) {
                    player.sendMessage("§cVous devez écrire /alert <message> pour report un problème");
                    return true;
                }
                if(args.length >= 1) {
                    StringBuilder bc = new StringBuilder();
                    for(String part : args) {
                        bc.append(part + " ");
                    }
                    for(Player p: Bukkit.getOnlinePlayers()){
                        if(p.isOp()){
                            p.sendMessage("§b" + player.getName() +" §c" + bc.toString());
                        }
                    }
                    player.sendMessage("§aVous avez bien envoyé votre problème. Merci !!");
                    return true;
                }

            }


        return false;
    }

}
