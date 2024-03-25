package fr.ychampions.worlds;

import fr.ychampions.teams.TeamColor;
import org.bukkit.Location;
import org.bukkit.World;

public class LobbyWorld extends GameWorld {

    @Override
    public boolean generateWorld(Runnable runnable) {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public World getWorld() {
        return null;
    }

    @Override
    public Location getLobbyPosition() {
        return null;
    }

    @Override
    public Location getSpawnForTeamColor(TeamColor color) {
        return null;
    }
}
