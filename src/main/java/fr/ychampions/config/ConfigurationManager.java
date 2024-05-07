package fr.ychampions.config;

import fr.ychampions.gamemanager.GameManager;
import fr.ychampions.worlds.Island;
import fr.ychampions.worlds.generators.Generator;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ConfigurationManager {

    private GameManager gameManager;
    private ConfigurationSection mapsConfiguration;

    /**
     *
     * @param gameManager
     */
    public ConfigurationManager(GameManager gameManager){
        this.gameManager = gameManager;
        FileConfiguration fileConfiguration = gameManager.getPlugin().getConfig();
        if (!fileConfiguration.isConfigurationSection("maps")){
            mapsConfiguration = fileConfiguration.createSection("maps");
        } else {
            mapsConfiguration = fileConfiguration.getConfigurationSection("maps");
        }
        gameManager.getPlugin().saveConfig();
    }

    /**
     *
     * @param mapName
     * @return
     */
    public ConfigurationSection getMapSection(String mapName){

        if (!mapsConfiguration.isConfigurationSection(mapName)){
            mapsConfiguration.createSection(mapName);
        }
        return mapsConfiguration.getConfigurationSection(mapName);
    }

    /**
     *
     * @param worldConfigName
     * @param generator
     */
    public void saveUnownedGenerator(String worldConfigName, Generator generator){
        ConfigurationSection mapSection = getMapSection(worldConfigName);

        ConfigurationSection generatorSection;
        if (mapSection.isConfigurationSection("generators")){
            generatorSection = mapSection.getConfigurationSection("generators");
        } else {
            generatorSection = mapSection.createSection("generators");
        }

        ConfigurationSection section = generatorSection.createSection(String.valueOf(UUID.randomUUID().toString()));
        section.set("type", generator.getType().toString());
        writeLocation(generator.getLocation(), section.createSection("location"));

        gameManager.getPlugin().saveConfig();
    }

    /**
     *
     * @param island
     */
    public void saveIsland(Island island){
        ConfigurationSection mapSection = getMapSection(island.getGameWorld().getConfigName());

        ConfigurationSection teamColorSection;
        if (mapSection.isConfigurationSection(island.getColor().toString())){
            teamColorSection = mapSection.getConfigurationSection(island.getColor().toString());
        } else {
            teamColorSection = mapSection.createSection(island.getColor().toString());
        }


        Map<String, Location> locationsToWrite = new HashMap<>();
        locationsToWrite.put("upgradeEntity", island.getUpgradesEntityLocation());
        locationsToWrite.put("bed", island.getBedLocation());
        locationsToWrite.put("shopEntity", island.getShopEntityLocation());
        locationsToWrite.put("spawn", island.getSpawnLocation());
        locationsToWrite.put("protectedCorderOne", island.getProtectedCornerOne());
        locationsToWrite.put("protectedCorderTwo", island.getProtectedCornerTwo());

        for (Map.Entry<String, Location> entry: locationsToWrite.entrySet()){
            ConfigurationSection section;
            if (!teamColorSection.isConfigurationSection(entry.getKey())){
                section = teamColorSection.createSection(entry.getKey());
            } else {
                section = teamColorSection.getConfigurationSection(entry.getKey());
            }
           writeLocation(entry.getValue(), section);
        }


        teamColorSection.set("generators", null);
        ConfigurationSection generatorSection = mapSection.createSection("generators");

        for (Generator generator : island.getGenerators()){
            ConfigurationSection section = generatorSection.createSection(String.valueOf(UUID.randomUUID().toString()));
            section.set("type", generator.getType().toString());
            writeLocation(generator.getLocation(), section.createSection("location"));
        }
        gameManager.getPlugin().saveConfig();
    }

    /**
     *
     * @param location
     * @param section
     */
    public void writeLocation(Location location, ConfigurationSection section){
        section.set("x", location.getX());
        section.set("y", location.getY());
        section.set("z", location.getZ());
        section.set("yaw", location.getYaw());
        section.set("pitch", location.getPitch());

    }

    /**
     *
     * @param world
     * @param section
     * @return
     */
    private Location locationFrom(World world, ConfigurationSection section){
        return new Location(world, section.getDouble("x"), section.getDouble("y"),
                section.getDouble("z"), (float) section.getDouble("yaw"), (float) section.getDouble("pitch"));
    }

}
