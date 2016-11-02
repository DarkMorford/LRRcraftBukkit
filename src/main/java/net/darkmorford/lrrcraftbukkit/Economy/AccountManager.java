package net.darkmorford.lrrcraftbukkit.Economy;

import net.darkmorford.lrrcraftbukkit.LRRcraftBukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

public class AccountManager {
    private static JavaPlugin plugin = LRRcraftBukkit.plugin;

    private Map<String, PlayerAccount> accounts;
    private Connection dbConn = null;

    public AccountManager() {
        accounts = new HashMap<>();

        // Open a connection to the account database
        try {
            // Register the SQLite driver
            Class.forName("org.sqlite.JDBC");

            // Build the path to the database file
            File dbPath = new File(plugin.getDataFolder(), "accounts.db");

            // Create the connection
            dbConn = DriverManager.getConnection("jdbc:sqlite:" + dbPath.getAbsolutePath());

            // Set up the tables we need
            initializeDatabase();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PlayerAccount getPlayerAccount(String playerName) {
        String query = "SELECT * FROM `players`" +
                "WHERE `name` = ? LIMIT 1";

        OfflinePlayer player = null;
        double balance = 0;

        try {
            // Fetch the player information from the database
            PreparedStatement stmt = dbConn.prepareStatement(query);
            stmt.setString(1, playerName);
            ResultSet result = stmt.executeQuery();

            // Get a Player object from the UUID
            UUID playerId = UUID.fromString(result.getString("uuid"));
            player = plugin.getServer().getOfflinePlayer(playerId);

            // Get the global balance for the Player
            balance = result.getDouble("globalBalance");

            // Done with the database
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return new PlayerAccount(player, balance);
    }

    private void initializeDatabase() throws SQLException {
        Statement stmt = dbConn.createStatement();

        // Create the Players table
        String query = "CREATE TABLE IF NOT EXISTS `players` (" +
                "`id` INTEGER PRIMARY KEY AUTOINCREMENT," +
                "`name` TEXT NOT NULL," +
                "`uuid` TEXT NOT NULL UNIQUE," +
                "`globalBalance` NUMERIC NOT NULL DEFAULT 0)";
        stmt.execute(query);
        stmt.close();

        // Create the Worlds table
        query = "CREATE TABLE IF NOT EXISTS `worlds` (" +
                "`id` INTEGER PRIMARY KEY AUTOINCREMENT," +
                "`name` TEXT NOT NULL," +
                "`uuid` TEXT NOT NULL UNIQUE)";
        stmt.execute(query);
        stmt.close();

        // Create the PlayerAccounts table
        query = "CREATE TABLE IF NOT EXISTS `playerAccounts` (" +
                "`player` INTEGER NOT NULL," +
                "`world` INTEGER NOT NULL," +
                "`balance` NUMERIC NOT NULL DEFAULT 0," +
                "PRIMARY KEY(`player`, `world`))";
        stmt.execute(query);
        stmt.close();
    }

    void save() {
        try {
            YamlConfiguration accountFile = new YamlConfiguration();

            accountFile.createSection("accounts", accounts);

            accountFile.save(new File(plugin.getDataFolder(), "accounts.yml"));
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Save Failed!", e);
        }
    }

    void load() {
        try {
            File accountFile = new File(plugin.getDataFolder(), "accounts.yml");
            if (accountFile.exists()) {
                // Load the YAML file
                YamlConfiguration accountMap = new YamlConfiguration();
                accountMap.load(accountFile);

                // Grab the list of accounts
                ConfigurationSection section = accountMap.getConfigurationSection("accounts");

                // Iterate through each one
                for (String accountName: section.getKeys(false)) {
                    PlayerAccount account = (PlayerAccount) section.get(accountName);
                    accounts.put(accountName, account);
                }
            }
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Load Failed!", e);
        } catch (InvalidConfigurationException e) {
            plugin.getLogger().log(Level.SEVERE, "Invalid Account File!", e);
        }
    }
}
