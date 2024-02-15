package de.imbadingerman.foodadder;

import de.imbadingerman.foodadder.handler.ConfigHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class FoodAdderCommand implements CommandExecutor {

    private ConfigHandler configHandler;
    private JavaPlugin plugin;

    public FoodAdderCommand(ConfigHandler configHandler, JavaPlugin plugin) {
        this.configHandler = configHandler;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be run by a player.");
            return true;
        }

        if (args.length != 2) {
            sender.sendMessage("Usage: /foodadder debug <true|false>");
            return true;
        }

        // Parse the boolean value
        boolean debugValue = Boolean.parseBoolean(args[1]);

        // Set the debug value in the configuration
        configHandler.set(debugValue);

        Player player = (Player) sender;
        player.sendMessage("Debug mode set to: " + debugValue);

        // Notify admins or operators about the update if available
        if (player.hasPermission("foodadder.admin") || player.isOp()) {
            if (configHandler.isUpdateAvailable()) {
                player.sendMessage("An update is available for FoodAdder!");
            }
        }

        return true;
    }
}
