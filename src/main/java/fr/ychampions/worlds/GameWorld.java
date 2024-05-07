package fr.ychampions.worlds;

import fr.ychampions.teams.TeamColor;
import fr.ychampions.worlds.generators.Generator;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.util.List;

public class GameWorld {

    private String name;
    private World world;

    public GameWorld(String name){
        this.name = name;
    }

    public void loadWorld(Runnable runnable) {
        WorldCreator worldCreator = new WorldCreator(name);
        world = worldCreator.createWorld();
        runnable.run();
    }
    public String getConfigName(){
        return name;
    }
    public World getWorld(){
        return world;
    }
    public Location getLobbyPosition() {
        return null;
    }
    public Location getSpawnForTeamColor(TeamColor color){
        return null;
    }
    public List<Generator> getGenerators(){
        return null;
    }


}
