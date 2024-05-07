package fr.ychampions.worlds;

import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import fr.ychampions.teams.TeamColor;
import fr.ychampions.worlds.generators.Generator;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class Island {

    private GameWorld gameWorld;
    private TeamColor teamColor;

    private Location protectedCornerOne = null;
    private Location protectedCornerTwo = null;

    private Location upgradesEntityLocation = null;
    private Location shopEntityLocation = null;
    private Location bedLocation = null;

    private Location spawnLocation = null;

    private List<Generator> generatorList = new ArrayList<>();

    public Island(GameWorld gameWorld, TeamColor color){
        this.gameWorld = gameWorld;
        this.teamColor = color;
    }

    public void setProtectedCornerOne(Location protectedCornerOne) {
        this.protectedCornerOne = protectedCornerOne;
    }

    public void setProtectedCornerTwo(Location protectedCornerTwo) {
        this.protectedCornerTwo = protectedCornerTwo;
    }

    public void setShopEntityLocation(Location shopEntityLocation) {
        this.shopEntityLocation = shopEntityLocation;
    }

    public void setSpawnLocation(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    public void setBedLocation(Location bedLocation) {
        this.bedLocation = bedLocation;
    }

    public void setUpgradesEntityLocation(Location upgradesEntityLocation) {
        this.upgradesEntityLocation = upgradesEntityLocation;
    }

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

    public TeamColor getColor() {
        return teamColor;
    }

    public void addGenerator(Generator islandGenerator) {
        this.generatorList.add(islandGenerator);
    }
}
