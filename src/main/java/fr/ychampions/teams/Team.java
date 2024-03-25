package fr.ychampions.teams;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private TeamColor color;
    private List<Player> players = new ArrayList<>();
    private Team(TeamColor color) {
        this.color = color;
    }
    public String getName(){
        return color.formattedName();
    }
    public TeamColor getColor() {
        return color;
    }
    public void setColor(TeamColor color) {
        this.color = color;
    }
    public List<Player> getPlayers() {
        return players;
    }
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    public boolean isMember(Player player){
        return players.contains(player);
    }

    public boolean isBedPlaced(){
        return false; //todo: fix
    }

    public int alivePlayerCount() {
        return players.stream().filter(player -> player.getGameMode() != GameMode.SPECTATOR).toArray().length;
    }
}
