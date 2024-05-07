package fr.ychampions.events;

import fr.ychampions.gamemanager.GameManager;
import fr.ychampions.gui.GUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryClickListener implements Listener {

    private GameManager gameManager;

    public InventoryClickListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return;
        if (!event.getCurrentItem().hasItemMeta()) return;

        Player player = (Player) event.getWhoClicked();

        GUI gui = gameManager.getGuiManager().getOpenGUI(player);
        if (gui == null) {
            player.sendMessage("Une erreur est survenue");
            return;
        }

        event.setCancelled(true);

        GUI newGui = gui.handleClick(player, event.getCurrentItem(), event.getView());

        event.getView().close();

        if (newGui != null){
            gameManager.getGuiManager().setGui(player, newGui);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        gameManager.getGuiManager().clear(player);
    }
}
