package ca.kronoxx.RP.Bukkit.commands.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.util.List;

public class MinecraftCommandManager {
    private MinecraftCommand[] commands;

    public MinecraftCommandManager(List<MinecraftCommand> commands){
        this.commands = commands.toArray(new MinecraftCommand[commands.size()]);
        sortCommands();
    }

    public boolean executeCommand(CommandSender sender, Command cmd, String label, String[] args){
        int max = commands.length - 1;
        int min = 0;
        int index = max / 2;
        while (!commands[index].isCommand(label)){
            if(commands[index].after(label)){
                max = index - 1;
                index -= getMaxMinDif(max, min);
            }else {
                min = index + 1;
                index += getMaxMinDif(max, min);
            }
            if(index > max || index < min) return false;
        }
        commands[index].executeCommandTask(sender, cmd, label, args);
        return true;
    }

    private int getMaxMinDif(int max, int min){
        int f = min - max;
        return (f<=0)?1:f;
    }

    private void sortCommands(){
        for (int i=1; i<commands.length; ++i){
            int j=i;
            MinecraftCommand swap = commands[j];
            while (j >= 0 && !commands[j].after(commands[j-1])){
                commands[j] = commands[j-1];
                --j;
            }
            commands[j] = swap;
        }
    }
}