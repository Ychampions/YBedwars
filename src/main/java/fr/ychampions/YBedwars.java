package fr.ychampions;

import fr.ychampions.events.PlayerLoginEventListener;
import fr.ychampions.gamemanager.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class YBedwars extends JavaPlugin {

    private GameManager gameManager;

    @Override
    public void onEnable() {

        this.gameManager = new GameManager(this);
        for (Player player : Bukkit.getOnlinePlayers()){
            gameManager.getScoreboard().addPlayer(player);
        }

        getServer().getPluginManager().registerEvents(new PlayerLoginEventListener(gameManager), this);

    }

    @Override
    public void onDisable() {

    }
}