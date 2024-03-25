package fr.ychampions.setup;

import fr.ychampions.ItemBuilder;
import fr.ychampions.teams.TeamColor;
import fr.ychampions.worlds.GameWorld;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class SetupWizardManager {

    public Map<Player, TeamColor> playerCurrentTeamSetupMap = new HashMap<>();
    public Map<Player, GameWorld> playerToGameWorldMap = new HashMap<>();

    public void activateSetupWizard(Player player, GameWorld world){
        player.getInventory().clear();
        player.setGameMode(GameMode.CREATIVE);
        player.teleport(world.getLobbyPosition());

        playerToGameWorldMap.put(player, world);

    }

    public void worldSetupWizard(Player player, GameWorld world) {
        player.getInventory().clear();

        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND).setName("&aSet Diamond Generator").toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.EMERALD).setName("&aSet Emerald Generator").toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.MAGENTA_WOOL).setName("&aChange Island").toItemStack());

    }

    public void teamSetupWizard(Player player, TeamColor teamColor, GameWorld world){
        player.getInventory().clear();

        player.getInventory().addItem(new ItemBuilder(Material.STICK).setName("&aFirst Corner Stick").toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.BLAZE_ROD).setName("&aSecond Corner Stick").toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.EGG).setName("&aSet Shop Location").toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.IRON_INGOT).setName("&aSet Generator Location").toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_SWORD).setName("&aSet Team Upgrade Location").toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.BOWL).setName("&aSet Spawn Location").toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.MELON).setName("&aSet Bed Location").toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.ARROW).setName("&aChange Island").toItemStack());
        player.getInventory().addItem(new ItemBuilder(teamColor.woolMaterial()).setName("&aChange Island").toItemStack());

        playerCurrentTeamSetupMap.put(player, TeamColor.RED);
    }

}
