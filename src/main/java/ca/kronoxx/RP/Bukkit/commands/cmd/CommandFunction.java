package ca.kronoxx.RP.Bukkit.commands.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@FunctionalInterface
public interface CommandFunction {
    void commandExecution(CommandSender sender, Command cmd, String label, String[] args);
}
