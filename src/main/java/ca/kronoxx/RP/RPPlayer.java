package ca.kronoxx.RP;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.management.PlatformLoggingMXBean;

public class RPPlayer{
    public static int MAX_HEALTH = 20;
    public static int MIN_HEALTH = 4;

    private String job;
    private Player player;
    private int health;
    private Boolean admin = false;
    private ItemStack[] inventaire;
    private Location position;

    public static RPPlayer genPlayerForSaveLoad(Player player){
        RPPlayer p = new RPPlayer();
        p.player = player;
        return p;
    }

    public RPPlayer(){}

    public RPPlayer(Player player, String job, int health){
        this.player = player;
        this.job = job;
        this.health = health;
    }

    public Player getPlayer(){
        return this.player;
    }

    public void loadValue(String job, int health){
        setHealth(health);
        this.job = job;
    }

    public String getJob(){
        return this.job;
    }

    public int getHealth(){
        return this.health;
    }

    public boolean getAdmin(){return this.admin;}

    public ItemStack[] getInventaire(){return this.inventaire;}

    public Location getPosition(){return this.position;}

    public void permaDamage(int damage){
        int health = this.health - damage;
        if(health <= MAX_HEALTH && health >= MIN_HEALTH) {
            this.health = health;
            return;
        }
        this.health = MIN_HEALTH;
        player.setHealthScale(this.health);
    }

    public void permaHeal(int heal){
        int health = this.health + heal;
        if(health <= MAX_HEALTH && health >= MIN_HEALTH) {
            this.health = health;
            return;
        }
        this.health = MAX_HEALTH;
        player.setHealthScale(this.health);
    }

    public void changeJob(String job){
        this.job = job;
    }

    public void setHealth(int health){
        if(health <= MAX_HEALTH && health >= MIN_HEALTH) {
            this.health = health;
            return;
        }
        this.health = MAX_HEALTH;
    }

    public void setAdmin(boolean bool){
        admin = bool;
    }
    public void setInventaire(ItemStack[] inv){
        inventaire = inv;
    }

    public void setPosition(Location co){
        position = co;
    }
}
