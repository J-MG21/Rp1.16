package ca.kronoxx.RP;

import ca.kronoxx.RP.Bukkit.commands.RpCommandInterface;;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;
import ca.kronoxx.RP.Bukkit.listener.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


public class Main extends JavaPlugin {
    private static Main instance;

    private newCrafts crafts;
    private RpCommandInterface rpInterface = new RpCommandInterface();
    private PlayerListener pListener = new PlayerListener();
    private DeathListener dListener = new DeathListener();
    private HashMap<UUID, RPPlayer> rpPlayers = new HashMap<UUID, RPPlayer>();
    private ServerSave save = new ServerSave();

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        configure();
        setupCommands();
        setupListeners();
        addRecipes();
    }

    @Override
    public void onDisable() { }

    public void loadNewPlayerInGame(Player player){
        rpPlayers.put(player.getUniqueId(), save.loadUser(player));
    }

    public void removePlayerFromGame(RPPlayer player){
        save.savePlayerOnDb(player);
        rpPlayers.remove(player);
    }

    public void removePlayerFromGame(Player player){
        save.savePlayerOnDb(getRpPlayer(player));
        rpPlayers.remove(player);
    }

    private void addRecipes(){
        ArrayList<Recipe> recipes;
        this.crafts = new newCrafts();
        recipes = crafts.newRecipe();

        for(Recipe r: recipes){
            getServer().addRecipe(r);
        }
    }

    private void configure(){
        rpInterface = (rpInterface == null)? new RpCommandInterface(): rpInterface;
        pListener = (pListener == null)? new PlayerListener(): pListener;
        dListener = (dListener == null)? new DeathListener(): dListener;
    }

    private void setupListeners(){
        getServer().getPluginManager().registerEvents(pListener, this);
        getServer().getPluginManager().registerEvents(dListener, this);
    }

    private void setupCommands(){
        getCommand("job").setExecutor(rpInterface);
        getCommand("alert").setExecutor(rpInterface);
        getCommand("health").setExecutor(rpInterface);
        getCommand("rumor").setExecutor(rpInterface);
        getCommand("admin").setExecutor(rpInterface);
    }

    //GETTER///////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static Main getInstance(){
        return instance;
    }

    public RpCommandInterface getRpCommands(){return rpInterface;}

    public RPPlayer getRpPlayer(Player player){
        return rpPlayers.get(player.getUniqueId());
    }

    public newCrafts getNewCrafts(){return crafts;}
}


    /*private void removeRecipes(){
        ArrayList<String> noneRecipes;
        this.crafts = new newCrafts();
        noneRecipes = crafts.nRecipe();

        for(String s : noneRecipes){
            crafts.removeRecipeByKey(s);
        }
    }*/