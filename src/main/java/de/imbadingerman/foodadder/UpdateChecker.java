package de.imbadingerman.foodadder;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.bukkit.plugin.Plugin;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class UpdateChecker {

    private static final String GITHUB_API_URL = "https://api.github.com/repos/imbadingerman/foodadder/releases/latest";

    public static void checkForUpdates(Plugin plugin) {
        try {
            URL url = new URL(GITHUB_API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStream inputStream = connection.getInputStream();
            String jsonResponse = convertStreamToString(inputStream);

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonResponse);
            String latestVersion = (String) jsonObject.get("tag_name");

            // Compare latestVersion with your plugin's current version
            String currentVersion = plugin.getDescription().getVersion();
            if (!latestVersion.equals(currentVersion)) {
                // Notify server admins about the update
                plugin.getLogger().info("An update is available for YourPlugin! Current version: " + currentVersion + ", Latest version: " + latestVersion);
            }

        } catch (IOException | ParseException e) {
            plugin.getLogger().warning("Failed to check for updates: " + e.getMessage());
        }
    }

    private static String convertStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }
}
