package ca.kronoxx.RP.Bukkit.commands;

import ca.kronoxx.RP.Bukkit.commands.cmd.MinecraftCommand;
import ca.kronoxx.RP.Bukkit.commands.cmd.MinecraftCommandManager;
import ca.kronoxx.RP.Bukkit.commands.cmd.RumorManager;
import ca.kronoxx.RP.Main;
import ca.kronoxx.RP.RPPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;


public class RpCommandInterface implements CommandExecutor {
    public static final String opAdminPermission = "minecraft.command.kill";
    private RumorManager rumorManager = new RumorManager();
    private MinecraftCommandManager commandManager;

    public RpCommandInterface(){
        commandManager = new MinecraftCommandManager(GetCommandSetups());
    }


    public void jobCommand(CommandSender sender, Command cmd, String label, String[] args){

        RPPlayer rpPlayer = Main.getInstance().getRpPlayer((Player) sender);
        System.out.println(rpPlayer.getHealth());
        if(args.length <= 0){
            rpPlayer.getPlayer().sendMessage("§cFaites la commandes /job set <player name> <job>");
        }else if(args[0].equalsIgnoreCase("set")){
            rpPlayer.changeJob(args[2]);
            rpPlayer.getPlayer().sendMessage("§aLe joueur §b" + args[1] + " §a est maintenant §b" + args[2]);
            rpPlayer.getPlayer().sendMessage("§aVous êtes maintenant §b" + args[2]);
        }
    }

    public void healthCommand(CommandSender sender, Command cmd, String label, String[] args){
        String nomJoueur = args[0];
        int vie = Integer.parseInt(args[1]);
        Player playerH = Bukkit.getPlayer(nomJoueur);
        RPPlayer rpPlayer = Main.getInstance().getRpPlayer(playerH);

        if(playerH == null){
            sender.sendMessage("§cLe joueur §4" + nomJoueur + "§c  n'est pas en ligne");
        }
        else{
            sender.sendMessage("§aLe joueur §2" + nomJoueur + " §aa maintenant §2" + vie + " §ademi coeur(s)");
            playerH.setMaxHealth(vie);
            playerH.setHealth(vie);
            rpPlayer.setHealth(vie);
        }
    }

    public void rumorCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(args.length == 0){
            sender.sendMessage(CommandMSG.basicCommandError);
        }else if(args[0].equalsIgnoreCase("add")){
            rumorManager.addRumor(args);
            sender.sendMessage(CommandMSG.addMessage);
        }else if(args[0].equalsIgnoreCase("send")){
            rumorManager.displayRumors();
        }else if(args[0].equalsIgnoreCase("wipe")){
            rumorManager.cleanRumors();
            sender.sendMessage(CommandMSG.wipeMessage);
        }
        else{
            sender.sendMessage(CommandMSG.wrongLabelError);
        }
    }

    public void alertCommand (CommandSender sender, Command cmd, String label, String[] args){
        Player rpPlayer = (Player)sender;
        if(args.length == 0) {
            rpPlayer.getPlayer().sendMessage("§cVous devez écrire /alert <message> pour report un problème");
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(String part : args) {
            stringBuilder.append(part + " ");
        }
        Bukkit.broadcast("§b" + rpPlayer.getName() +" §c" + stringBuilder.toString(), opAdminPermission);
        rpPlayer.sendMessage("§aVous avez bien envoyé votre problème. Merci !!");
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        this.commandManager.executeCommand(sender, cmd, label, args);
        return false;
    }

    private ArrayList<MinecraftCommand> GetCommandSetups(){
        ArrayList<MinecraftCommand> cmds = new ArrayList<>();
        cmds.add(new MinecraftCommand("health", this::healthCommand));
        cmds.add(new MinecraftCommand("job", this::jobCommand));
        cmds.add(new MinecraftCommand("rumor", this::rumorCommand));
        cmds.add(new MinecraftCommand("alert", this::alertCommand));


        return cmds;
    }

     public void useRumor(Player player){
        rumorManager.displayRumorsConnection(player);
     }
}
