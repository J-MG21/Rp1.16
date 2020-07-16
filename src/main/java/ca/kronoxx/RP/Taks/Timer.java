package ca.kronoxx.RP.Taks;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;

public class Timer extends BukkitRunnable {



    private int timer  = 10;
    @Override
    public void run() {

        if(timer ==0) {
            cancel();
        }
        timer--;

    }
}