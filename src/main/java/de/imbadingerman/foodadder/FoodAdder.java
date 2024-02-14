package de.imbadingerman.foodadder;

import de.imbadingerman.foodadder.commands.debug;
import de.imbadingerman.foodadder.handler.ConfigHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class FoodAdder extends JavaPlugin implements Listener {
    private ConfigHandler configHandler;
    @Override
    public void onEnable() {
        getLogger().info("FoodAdder enabled");
        configHandler = new ConfigHandler(this);
        configHandler.setupConfig();

        getCommand("foodadder").setExecutor(new debug(configHandler));

        new BukkitRunnable() {
            @Override
            public void run() {
                UpdateChecker.checkForUpdates(FoodAdder.this);
            }
        }.runTaskTimer(this, 0,72000);
    }

    public void onDisable() {
        getLogger().info("FoodAdder disabled");
        configHandler.saveConfig();
    }

}
