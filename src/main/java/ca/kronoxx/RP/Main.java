package ca.kronoxx.RP;

import Database.Database;
import ca.kronoxx.RP.Bukkit.commands.Cmd;;
import ca.kronoxx.RP.Taks.Timer;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;
import ca.kronoxx.RP.Bukkit.listener.*;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;

import java.util.ArrayList;
import java.util.Collection;


public class Main extends JavaPlugin {
    private newCrafts crafts;


    private newCrafts crafts;


    private Database database;
    @Override
    public void onLoad() {
        String password = System.getenv("dbPassword");
        String user = System.getenv("dbUser");
        this.database = new Database("jdbc:mysql://localhost:3306/minrp", user, password);
        database.open();


    }

    @Override
    public void onEnable() {
        getCommand("job").setExecutor(new Cmd());
        getCommand("alert").setExecutor(new Cmd());
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new Mort(), this);

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

}
