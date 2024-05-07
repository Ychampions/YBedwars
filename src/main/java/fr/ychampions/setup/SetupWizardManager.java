package fr.ychampions.setup;

import fr.ychampions.ItemBuilder;
import fr.ychampions.gamemanager.GameManager;
import fr.ychampions.teams.TeamColor;
import fr.ychampions.worlds.GameWorld;
import fr.ychampions.worlds.Island;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class SetupWizardManager {

    public Map<Player, Island> playerToIslandMap = new HashMap<>();
    public Map<Player, GameWorld> playerToGameWorldMap = new HashMap<>();

    private GameManager gameManager;

    public SetupWizardManager(GameManager gameManager){
        this.gameManager = gameManager;
    }

    public boolean isInWizard(Player player){
        return playerToGameWorldMap.containsKey(player);
    }

    public void activateSetupWizard(Player player, GameWorld world){
        player.getInventory().clear();
        player.setGameMode(GameMode.CREATIVE);
        player.teleport(new Location(world.getWorld(), 0, 69, 0));

        worldSetupWizard(player, world);

    }

    public void worldSetupWizard(Player player, GameWorld world) {
        player.getInventory().clear();

        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND).setName("&aSet Diamond Generator").toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.EMERALD).setName("&aSet Emerald Generator").toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.ANVIL).setName("&aChange Island").toItemStack());

        playerToGameWorldMap.put(player, world);

    }

    public void teamSetupWizard(Player player, TeamColor teamColor){
        player.getInventory().clear();

        player.getInventory().addItem(new ItemBuilder(Material.STICK).setName("&aFirst Corner Stick").toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.BLAZE_ROD).setName("&aSecond Corner Stick").toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.EGG).setName("&aSet Shop Location").toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.IRON_INGOT).setName("&aSet Generator Location").toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_SWORD).setName("&aSet Team Upgrade Location").toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.BOWL).setName("&aSet Spawn Location").toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.MELON).setName("&aSet Bed Location").toItemStack());
        player.getInventory().addItem(new ItemBuilder(teamColor.woolMaterial()).setName("&aChange Island").toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.NETHER_STAR).setName("&aSave Island").toItemStack());

        Island island = new Island(getWorld(player), teamColor);
        playerToIslandMap.put(player, island);
    }

    public GameWorld getWorld(Player player){
        return playerToGameWorldMap.get(player);
    }

    public Island getIsland(Player player){
        return playerToIslandMap.get(player);
    }

}
