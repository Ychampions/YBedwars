package fr.ychampions.gamemanager;

import fr.ychampions.YBedwars;
import fr.ychampions.config.ConfigurationManager;
import fr.ychampions.gui.GUIManager;
import fr.ychampions.setup.SetupWizardManager;
import jscoreboards.JPerPlayerScoreboard;
import jscoreboards.JScoreboard;
import jscoreboards.JScoreboardOptions;
import jscoreboards.JScoreboardTabHealthStyle;
import org.bukkit.GameMode;
import org.bukkit.configuration.Configuration;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private YBedwars plugin;
    private JPerPlayerScoreboard scoreboard;
    private SetupWizardManager setupWizardManager;
    private ConfigurationManager configurationManager;
    private GUIManager guiManager;

    private GameState state;

    public GameManager(YBedwars plugin){
        this.plugin = plugin;

        this.setupWizardManager = new SetupWizardManager(this);
        this.configurationManager = new ConfigurationManager(this);

        this.guiManager = new GUIManager();

        this.scoreboard = new JPerPlayerScoreboard(player -> "&e&lYBedwars ", player -> {
            List<String> lines = new ArrayList<>();
            //todo : scoreboard
            if (player.getGameMode().equals(GameMode.SPECTATOR)) {
                lines.add("&6Dead!");
            }
            lines.add("&a");
            return lines;
        }, new JScoreboardOptions(JScoreboardTabHealthStyle.NONE, false));
    }

    public void setState(GameState state) {
        this.state = state;

        switch (state) {
            case PRELOBBY:
                // load worlds
                break;
            case LOBBY:
                // ???
            case STARTING:
                // teleport people into area
                break;
            case ACTIVE:
                // ???
                break;
            case WON:
                // annonce winner, etc.
                break;
        }
    }

    public GameState getState() {
        return state;
    }

    public JScoreboard getScoreboard() {
        return scoreboard;
    }

    public YBedwars getPlugin() {
        return this.plugin;
    }

    public SetupWizardManager getSetupWizardManager() {
        return setupWizardManager;
    }

    public GUIManager getGuiManager(){
        return this.guiManager;
    }

    public ConfigurationManager getConfigurationManager() {
        return this.configurationManager;
    }
}
