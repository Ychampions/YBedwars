package fr.ychampions.config;

import fr.ychampions.gamemanager.GameManager;
import fr.ychampions.worlds.GameWorld;
import fr.ychampions.worlds.Island;
import fr.ychampions.worlds.generators.Generator;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class ConfigurationManager {

    private GameManager gameManager;
    private ConfigurationSection mapsConfiguration;

    public ConfigurationManager(GameManager gameManager){
        this.gameManager = gameManager;
        this.mapsConfiguration = gameManager.getPlugin().getConfig().getConfigurationSection("maps");
        // this.configuration = gameManager.getPlugin().get
    }

    public void saveIsland(Island island){
        GameWorld gameWorld = island.getGameWorld();
        String worldName = gameWorld.getConfigName();

        if (mapsConfiguration.isConfigurationSection(worldName)){
            mapsConfiguration.createSection(worldName);
        }
        ConfigurationSection mapSection = mapsConfiguration.getConfigurationSection(worldName);

        Map<String, Location> locationsToWrite = new HashMap<>();

        locationsToWrite.put("upgradeEntity", island.getUpgradesEntityLocation());
        locationsToWrite.put("bed", island.getBedLocation());
        locationsToWrite.put("shopEntity", island.getShopEntityLocation());
        locationsToWrite.put("spawn", island.getSpawnLocation());
        locationsToWrite.put("protectedCorderOne", island.getProtectedCornerOne());
        locationsToWrite.put("protectedCorderTwo", island.getProtectedCornerTwo());

        for (Map.Entry<String, Location> entry: locationsToWrite.entrySet()){
            ConfigurationSection section;
            if (!mapSection.isConfigurationSection(entry.getKey())){
                section = mapSection.createSection(entry.getKey());
            } else {
                section = mapSection.getConfigurationSection(entry.getKey());
            }

           writeLocation(entry.getValue(), section);
        }

        ConfigurationSection generatorSection = mapSection.getConfigurationSection("generators");

        for (Generator generator : island.getGenerators()){
            ConfigurationSection section;
            if (!mapSection.isConfigurationSection("generators")){
                section = mapSection.createSection("generators");
            } else {
                section = mapSection.getConfigurationSection("generators");

                // timecode : 1:41:31
            }

            writeLocation(entry.getValue(), section);
        }

        gameManager.getPlugin().saveConfig();
    }

    public void writeLocation(Location location, ConfigurationSection section){
        section.set("x", location.getX());
        section.set("y", location.getY());
        section.set("x", location.getZ());
        section.set("yaw", location.getYaw());
        section.set("pitch", location.getPitch());

    }

    private Location locationFrom(World world, ConfigurationSection section){
        return new Location(world, section.getDouble("x"), section.getDouble("y"),
                section.getDouble("z"), (float) section.getDouble("yaw"), (float) section.getDouble("pitch"));
    }

}
