package de.imbadingerman.foodadder.handler;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class ConfigHandler {
    private final Plugin plugin;
    private FileConfiguration config;
    private File configFile;

    public ConfigHandler(Plugin plugin) {
        this.plugin = plugin;
    }

    public void setupConfig() {
        configFile = new File(plugin.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            plugin.saveResource("config.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getString(String path) {
        return config.getString(path);
    }

    public void set(String path, Object value) {
        config.set(path, value);
        saveConfig();
    }

    public boolean isDebug() {
        return config.getBoolean("debug", false);
    }

    public void setDebug(boolean value) {
        set("debug", value);
    }
}
