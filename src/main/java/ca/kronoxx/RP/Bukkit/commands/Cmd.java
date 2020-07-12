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

        return false;
    }




}
