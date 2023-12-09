package de.imbadingerman.foodadder;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public class Foodadder extends JavaPlugin {
    @Override
    public void onEnable() {
        File messagesFile = new File(getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            saveResource("messages.yml", false);
        }

        FileConfiguration messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);

        String message = messagesConfig.getString("plugin_enabled");

        getLogger().info(message);
        Objects.requireNonNull(getCommand("fooditems")).setExecutor(new FoodCommand(this));
    }

    @Override
    public void onDisable() {
        File messagesFile = new File(getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            saveResource("messages.yml", false);
        }

        FileConfiguration messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
    String disable_message = messagesConfig.getString("plugin_disabled");

    getLogger().info(disable_message);
    }
}