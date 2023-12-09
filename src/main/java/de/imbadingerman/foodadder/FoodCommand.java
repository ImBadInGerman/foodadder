package de.imbadingerman.foodadder;

import org.bukkit.command.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class FoodCommand implements CommandExecutor {
    private final Plugin plugin;

    public FoodCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("fooditems")) {
            FileConfiguration config = plugin.getConfig();
            ConfigurationSection itemsSection = config.getConfigurationSection("items");

            if (itemsSection == null) {
                sender.sendMessage("Es wurden keine Items in der Konfigurationsdatei gefunden.");
                return true;
            }

            sender.sendMessage("Die folgenden Items sind in der Konfigurationsdatei definiert:");
            for (String itemName : itemsSection.getKeys(false)) {
                ConfigurationSection itemSection = itemsSection.getConfigurationSection(itemName);
                int amountOfBites = itemSection.getInt("amount_of_bites");
                List<String> foodItems = itemSection.getStringList("items");
                boolean setItemBack = itemSection.getBoolean("set_item_back");
                String itemBackItem = itemSection.getString("item_back.item");
                int itemBackAmount = itemSection.getInt("item_back.amount");

                sender.sendMessage(itemName + ":");
                sender.sendMessage("  amount_of_bites: " + amountOfBites);
                sender.sendMessage("  items:");
                for (String foodItem : foodItems) {
                    sender.sendMessage("    - " + foodItem);
                }
                sender.sendMessage("  set_item_back: " + setItemBack);
                sender.sendMessage("  item_back:");
                sender.sendMessage("    item: " + itemBackItem);
                sender.sendMessage("    amount: " + itemBackAmount);
            }

            return true;
        }

        return false;
    }
}
