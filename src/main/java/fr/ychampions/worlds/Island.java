package fr.ychampions.worlds;

import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import fr.ychampions.worlds.generators.Generator;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.List;

public class Island {

    private GameWorld gameWorld;

    private Location protectedCornerOne;
    private Location protectedCornerTwo;

    private Location upgradesEntityLocation;
    private Location shopEntityLocation;
    private Location bedLocation;

    private Location spawnLocation;

    private List<Generator> generatorList;

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public Location getProtectedCornerOne() {
        return protectedCornerOne;
    }

    public Location getProtectedCornerTwo() {
        return protectedCornerTwo;
    }

    public Location getUpgradesEntityLocation() {
        return upgradesEntityLocation;
    }

    public Location getShopEntityLocation() {
        return shopEntityLocation;
    }

    public Location getBedLocation() {
        return bedLocation;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public List<Generator> getGenerators() {
        return generatorList;
    }

    public boolean isBlockWithinProtectedZone(Block block){
        Location blockLocation = block.getLocation();

        BlockVector3 one = BlockVector3.at(protectedCornerOne.getX(), protectedCornerOne.getY(), protectedCornerOne.getBlockZ());
        BlockVector3 two = BlockVector3.at(protectedCornerTwo.getX(), protectedCornerTwo.getY(), protectedCornerTwo.getBlockZ());
        CuboidRegion region = new CuboidRegion(one, two);
        
        return region.contains(BlockVector3.at(blockLocation.getX(), blockLocation.getY(), blockLocation.getZ()));
    }
}
