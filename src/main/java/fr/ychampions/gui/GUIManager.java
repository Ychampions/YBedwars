package fr.ychampions.gui;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class GUIManager {

    private Map<Player, GUI> playerGUIMap = new HashMap<>();

    public GUI getOpenGUI(Player player) {
        return playerGUIMap.get(player);
    }

    public void setGui(Player player, GUI gui){
        if (gui == null) {
            player.closeInventory();
            return;
        }
        playerGUIMap.put(player, gui);
        player.closeInventory();
        player.openInventory(gui.getInventory());
    }

    public void clear(Player player){
        playerGUIMap.remove(player);
    }
}
