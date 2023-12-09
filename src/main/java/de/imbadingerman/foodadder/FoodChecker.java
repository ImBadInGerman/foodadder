package de.imbadingerman.foodadder;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class FoodChecker {
    private final Plugin plugin;

    public FoodChecker(Plugin plugin) {
        this.plugin = plugin;
    }

    public boolean isFood(ItemStack item) {
        FileConfiguration config = plugin.getConfig();
        List<String> foodItems = config.getStringList("item");

        for (String foodItem : foodItems) {
            if (item.getType().toString().equalsIgnoreCase(foodItem)) {
                return true;
            }
        }
        return false;
    }
}
