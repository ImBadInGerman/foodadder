package de.imbadingerman.foodadder.handler;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class ConfigHandler {

    private final Plugin plugin;
    private FileConfiguration config;
    private File configFile;

    public ConfigHandler(Plugin plugin) {
        this.plugin = plugin;
    }

    public void setupConfig() {
        configFile = new File(plugin.getDataFolder(), "config.yml");

        // If config file doesn't exist, create it and load defaults
        if (!configFile.exists()) {
            plugin.saveResource("config.yml", false);
        }

        // Load configuration
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            plugin.getLogger().warning("Unable to save config.yml!");
        }
    }

    public boolean isUpdateAvailable() {
        // Logic to check if an update is available
        // Return true if an update is available, otherwise false
        return false;
    }

    public void set(boolean debugValue) {
        return;
    }
}
