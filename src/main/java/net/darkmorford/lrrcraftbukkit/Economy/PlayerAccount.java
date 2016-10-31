package net.darkmorford.lrrcraftbukkit.Economy;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerAccount implements ConfigurationSerializable {
    // Member variables
    private UUID   playerId;
    private String playerName;
    private int    playerBalance;

    // Constructors
    PlayerAccount(Player player, int balance) {
        playerId      = player.getUniqueId();
        playerName    = player.getName();
        playerBalance = balance;
    }

    PlayerAccount(UUID id, String name, int balance) {
        playerId      = id;
        playerName    = name;
        playerBalance = balance;
    }

    PlayerAccount(Map<String, Object> account) {
        playerId      = (UUID)   account.get("ID");
        playerName    = (String) account.get("Name");
        playerBalance = (int)    account.get("Balance");
    }

    // Accessors
    UUID getId() {
        return playerId;
    }

    String getName() {
        return playerName;
    }

    int getBalance() {
        return playerBalance;
    }

    // Mutators
    int addBalance(int amount) {
        playerBalance += amount;
        return playerBalance;
    }

    int subtractBalance(int amount) {
        playerBalance -= amount;
        return playerBalance;
    }

    // Serialization
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> serialized = new HashMap<>();

        serialized.put("ID", playerId);
        serialized.put("Name", playerName);
        serialized.put("Balance", playerBalance);

        return serialized;
    }
}
