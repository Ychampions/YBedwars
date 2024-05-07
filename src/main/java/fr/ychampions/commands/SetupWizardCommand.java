package fr.ychampions.commands;

import fr.ychampions.gamemanager.GameManager;
import fr.ychampions.worlds.GameWorld;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetupWizardCommand implements CommandExecutor {

    private GameManager gameManager;

    public SetupWizardCommand(GameManager gameManager){
        this.gameManager = gameManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;

        if (!player.hasPermission("ybedwars.admin")){
            player.sendMessage("Tu n'es pas administrateur");
            return true;
        }

        if (args.length < 1){
            player.sendMessage("/setup <map name>");
            return true;
        }

        String mapName = args[0];
        player.sendMessage("Chargement de la map...");

        GameWorld world = new GameWorld(mapName);
        world.loadWorld(()-> gameManager.getSetupWizardManager().activateSetupWizard(player, world));

        return false;
    }
}
