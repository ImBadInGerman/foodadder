package de.imbadingerman.foodadder;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class FoodAdder extends JavaPlugin implements Listener {

    private static final String GITHUB_API_URL = "https://api.github.com/repos/imbadingerman/FoodAdder/releases/latest";
    @Override
    public void onEnable() {
        getLogger().info("FoodAdder enabled");

        new BukkitRunnable() {
            @Override
            public void run() {
                checkForUpdates();
            }
        }.runTaskTimer(this, 0, 20);
    }


    private void checkForUpdates() {
        try {
            URL url = new URL(GITHUB_API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStream inputStream = connection.getInputStream();
            String jsonResponse = convertStreamToString(inputStream);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonResponse);
            String latestVersion = (String) jsonObject.get("tag_name");

            String currentVersion = getDescription().getVersion();
            if (!latestVersion.equals(currentVersion)) {
                getLogger().warning("There is a new version available: " + latestVersion);
            }
        } catch (Exception e) {
            getLogger().warning("Failed to check for updates: " + e.getMessage());
        }
    }

    private String convertStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }
    public void onDisable() {
        getLogger().info("FoodAdder disabled");
    }

}
