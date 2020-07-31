package ca.kronoxx.RP.Bukkit.commands.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class MinecraftCommand {
    private String name;
    private CommandFunction task;

    public MinecraftCommand (String name, CommandFunction task){
        this.name = name;
        this.task = task;
    }

    public void executeCommandTask(CommandSender sender, Command cmd, String label, String[] args){
        task.commandExecution(sender, cmd, label, args);
    }

    public void changeTask(CommandFunction cmd){
        this.task = cmd;
    }

    public boolean isCommand(String name){
        return this.name.equals(name);
    }

    public boolean after(MinecraftCommand cmd){
        return (this.name.compareTo(cmd.name) == -1);
    }

    public boolean after(String cmd){
        return (this.name.compareTo(cmd) == -1);
    }


}
