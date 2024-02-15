package de.imbadingerman.foodadder.commands;

import de.imbadingerman.foodadder.handler.ConfigHandler;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class debug implements CommandExecutor {
    private ConfigHandler configHandler;

    public debug(ConfigHandler configHandler) {
        this.configHandler = configHandler;
    }
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this command!");
            return true;
        }

        if (args.length == 2) {
            sender.sendMessage("Usage: /foodadder debug <true|false>");
            return true;
        }

        boolean debugValue = Boolean.parseBoolean(args[1]);

        configHandler.set(debugValue);

        Player player = (Player) sender;
        player.sendMessage("Debug mode set to " + debugValue);

        return true;
    }
}
