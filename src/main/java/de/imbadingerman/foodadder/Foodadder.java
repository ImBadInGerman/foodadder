package de.imbadingerman.foodadder;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Foodadder extends JavaPlugin {

    private FileConfiguration messagesConfig;

    @Override
    public void onEnable() {
        loadMessagesConfig();

        String pluginEnabledMessage = messagesConfig.getString("plugin_enabled");
        getLogger().info(pluginEnabledMessage);
    }

    @Override
    public void onDisable() {
        String pluginDisabledMessage = messagesConfig.getString("plugin_disabled");
        getLogger().info(pluginDisabledMessage);
    }

    private void loadMessagesConfig() {
        File messagesFile = new File(getDataFolder(), "messages.yml");

        if (!messagesFile.exists()) {
            saveResource("messages.yml", false);
        }

        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
    }
}