package fr.ychampions.events;

import fr.ychampions.gamemanager.GameManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLoginEventListener implements Listener {

    private GameManager gameManager;

    public PlayerLoginEventListener(GameManager gameManager){
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        event.setJoinMessage(null);

        gameManager.getScoreboard().addPlayer(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        event.setQuitMessage(null);

        gameManager.getScoreboard().removePlayer(event.getPlayer());
    }

}
