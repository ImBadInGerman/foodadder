package de.imbadingerman.foodadder;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class FoodAdder extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("FoodAdder enabled");
    }

    public void onDisable() {
        getLogger().info("FoodAdder disabled");
    }

}
