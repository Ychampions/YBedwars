package fr.ychampions.events;

import fr.ychampions.gamemanager.GameManager;
import fr.ychampions.gui.SetupWizardIslandSelectorGUI;
import fr.ychampions.worlds.Island;
import fr.ychampions.worlds.generators.Generator;
import fr.ychampions.worlds.generators.GeneratorType;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerItemInteractListener implements Listener {

    private GameManager gameManager;

    public PlayerItemInteractListener(GameManager gameManager){
        this.gameManager = gameManager;
    }

    // Setup Wizard
    @EventHandler
    public void onInteractWithSetupWizardItem(PlayerInteractEvent event){
        if (!event.hasItem()) return;
        if (!gameManager.getSetupWizardManager().isInWizard(event.getPlayer())) return;
        if (event.getItem() == null) return;
        if (!event.getItem().hasItemMeta()) return;

        Player player = event.getPlayer();
        Location currentLocation = player.getLocation();

        Location clicked = null;

        if (event.getClickedBlock() != null){
            clicked = event.getClickedBlock().getLocation();
        }

        Island island = gameManager.getSetupWizardManager().getIsland(player);

        String itemName = event.getItem().getItemMeta().getDisplayName();
        itemName = ChatColor.stripColor(itemName);

        switch (itemName.toLowerCase()){
            case "set diamond generator":
                Generator diamondGenerator = new Generator(currentLocation, GeneratorType.DIAMOND);
                gameManager.getConfigurationManager().saveUnownedGenerator(player.getWorld().getName(), diamondGenerator);
                player.sendMessage("Diamond Generator Setup");
                break;
            case "set emerald generator":
                Generator emeraldGenerator = new Generator(currentLocation, GeneratorType.EMERALD);
                gameManager.getConfigurationManager().saveUnownedGenerator(player.getWorld().getName(), emeraldGenerator);
                player.sendMessage("Emerald Generator Setup");
                break;
            case "change island":
                SetupWizardIslandSelectorGUI gui = new SetupWizardIslandSelectorGUI(gameManager);
                gameManager.getGuiManager().setGui(player, gui);
                break;
            case "first corner stick":
                if (clicked != null) {
                    island.setProtectedCornerOne(clicked);
                    player.sendMessage("First Corner selected");
                }
                break;
            case "second corner stick":
                if (clicked != null) {
                    island.setProtectedCornerTwo(clicked);
                    player.sendMessage("Second Corner Selected");
                }
                break;
            case "set shop location":
                island.setShopEntityLocation(currentLocation);
                player.sendMessage("Shop Setup");
                break;
            case "set generator location":
            {
                Generator islandGenerator = new Generator(currentLocation, GeneratorType.IRON);
                island.addGenerator(islandGenerator);
                player.sendMessage("Iron Generator Setup");
            }
            {
                Generator islandGenerator = new Generator(currentLocation, GeneratorType.GOLD);
                island.addGenerator(islandGenerator);
                player.sendMessage("Gold Generator Setup");
            }
            {
                Generator islandGenerator = new Generator(currentLocation, GeneratorType.EMERALD);
                island.addGenerator(islandGenerator);
                player.sendMessage("Emerald island Generator Setup");
            }
                break;
            case "set team upgrade location":
                island.setUpgradesEntityLocation(currentLocation);
                player.sendMessage("Team Upgrades Setup");
                break;
            case "set spawn location":
                island.setSpawnLocation(currentLocation);
                player.sendMessage("Spawn Island Setup");
                break;
            case "set bed location":
                if (clicked != null) {
                    island.setBedLocation(clicked);
                    player.sendMessage("Bed Setup");
                }
                break;
            case "save island":
                gameManager.getConfigurationManager().saveIsland(island);
                gameManager.getSetupWizardManager().worldSetupWizard(player, island.getGameWorld());
                break;
            default: return;
        }

        event.setCancelled(true);
    }

}
