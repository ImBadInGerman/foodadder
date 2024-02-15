package de.imbadingerman.foodadder;

import de.imbadingerman.foodadder.handler.ConfigHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class FoodAdder extends JavaPlugin implements Listener {

    private ConfigHandler configHandler;

    @Override
    public void onEnable() {
        // Initialize ConfigHandler
        configHandler = new ConfigHandler(this);
        configHandler.setupConfig();

        new BukkitRunnable() {
            @Override
            public void run() {
                // Check for updates
                UpdateChecker.checkForUpdates(FoodAdder.this);
            }
        }.runTaskTimer(this,0,72000);

        // Register the command executor
        getCommand("foodadder").setExecutor(new de.imbadingerman.foodadder.FoodAdderCommand(configHandler, this));

        // Register event listener
        getServer().getPluginManager().registerEvents(this, this);

        // Notify players with permission or operators when they join
        for (Player player : getServer().getOnlinePlayers()) {
            if (player.hasPermission("foodadder.admin") || player.isOp()) {
                notifyUpdate(player);
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("foodadder.admin") || player.isOp()) {
            notifyUpdate(player);
        }
    }

    private void notifyUpdate(Player player) {
        if (configHandler.isUpdateAvailable()) {
            player.sendMessage("An update is available for FoodAdder!");
        }
    }
}
