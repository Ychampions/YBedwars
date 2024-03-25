package fr.ychampions.worlds;

import fr.ychampions.teams.TeamColor;
import org.bukkit.Location;
import org.bukkit.World;

public abstract class GameWorld {

    public abstract boolean generateWorld(Runnable runnable);
    public abstract String getName();
    public abstract String getConfigName();
    public abstract World getWorld();
    public abstract Location getLobbyPosition();
    public abstract Location getSpawnForTeamColor(TeamColor color);

}
