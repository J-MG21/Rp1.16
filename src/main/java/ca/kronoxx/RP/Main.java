package ca.kronoxx.RP;

import Database.Database;
import ca.kronoxx.RP.Bukkit.commands.MainCmd;;
import ca.kronoxx.RP.Bukkit.commands.cmd.Rumor;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;
import ca.kronoxx.RP.Bukkit.listener.*;

import java.util.ArrayList;


public class Main extends JavaPlugin {
    private newCrafts crafts;
    private Database database;
    private PlayerConection pC = new PlayerConection();



    @Override
    public void onLoad() {
        String password = System.getenv("dbPassword");
        String user = System.getenv("dbUser");
        this.database = new Database("jdbc:mysql://localhost:3306/minrp", user, password);
        database.open();
    }

    @Override
    public void onEnable() {
        getCommand("job").setExecutor(new MainCmd(this));
        getCommand("alert").setExecutor(new MainCmd(this));
        getCommand("health").setExecutor(new MainCmd(this));

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new Mort(this), this);

        /*removeRecipes();*/
        //addRecipes();
    }

    @Override
    public void onDisable() {

    }

    private void addRecipes(){
        ArrayList<Recipe> recipes;
        this.crafts = new newCrafts();
        recipes = crafts.newRecipe();

        for(Recipe r: recipes){
            getServer().addRecipe(r);
        }
    }

    /*private void removeRecipes(){
        ArrayList<String> noneRecipes;
        this.crafts = new newCrafts();
        noneRecipes = crafts.nRecipe();

        for(String s : noneRecipes){
            crafts.removeRecipeByKey(s);
        }
    }*/


    //****GETTER
    public PlayerConection playerConection(){
        return pC;
    }

}
