package net.darkmorford.lrrcraftbukkit.Economy;

import net.darkmorford.lrrcraftbukkit.LRRcraftBukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AccountManager implements IAccountManager {
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

    @Override
    public boolean savePlayerAccount(PlayerAccount account) {
        String query = "UPDATE `players` " +
                "SET `globalBalance` = ? " +
                "WHERE `id` = ?";

        try {
            PreparedStatement stmt = dbConn.prepareStatement(query);
            stmt.setInt(1, account.getDatabaseId());
            stmt.setDouble(2, account.getBalance());

            stmt.executeUpdate();

            stmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public PlayerAccount createPlayerAccount(String playerName) {
        return createPlayerAccount(playerName, 0);
    }

    @Override
    public PlayerAccount createPlayerAccount(String playerName, double balance) {
        String query = "INSERT OR IGNORE INTO `players` " +
                "(`name`, `uuid`, `balance`) VALUES " +
                "(?, ?, ?)";

        OfflinePlayer player = plugin.getServer().getOfflinePlayer(playerName);
        int databaseId = 0;

        try {
            PreparedStatement stmt = dbConn.prepareStatement(query);
            stmt.setString(1, player.getName());
            stmt.setString(2, player.getUniqueId().toString());
            stmt.setDouble(3, balance);

            stmt.execute();

            ResultSet results = stmt.getGeneratedKeys();
            if (results.next()) {
                databaseId = results.getInt(1);
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (databaseId > 0) {
            return new PlayerAccount(player, balance, databaseId);
        }
        else
            return null;
    }

    @Override
    public PlayerAccount getPlayerAccount(String playerName) {
        String query = "SELECT * FROM `players` " +
                "WHERE `name` = ? LIMIT 1";

        OfflinePlayer player = null;
        double balance = 0;
        int id = 0;

        if (dbConn == null) {
            return null;
        }

        try {
            // Fetch the player information from the database
            PreparedStatement stmt = dbConn.prepareStatement(query);
            stmt.setString(1, playerName);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                // Get an OfflinePlayer object from the UUID
                UUID playerId = UUID.fromString(result.getString("uuid"));
                player = plugin.getServer().getOfflinePlayer(playerId);

                // Get the global balance for the Player
                balance = result.getDouble("globalBalance");

                // Get the player's database ID
                id = result.getInt("id");
            } else {
                plugin.getLogger().warning("No account exists for player " + playerName);
                stmt.close();
                return null;
            }

            // Done with the database
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return new PlayerAccount(player, balance, id);
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
}
