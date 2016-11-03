package net.darkmorford.lrrcraftbukkit.Economy;

import org.bukkit.OfflinePlayer;

public class PlayerAccount {
    // Member variables
    private int _databaseId;
    private OfflinePlayer _player;
    private double _balance;

    // Constructors
    PlayerAccount(OfflinePlayer player, double balance, int id) {
        _player = player;
        _balance = balance;
        _databaseId = id;
    }

    // Accessors
    OfflinePlayer getPlayer() {
        return _player;
    }

    double getBalance() {
        return _balance;
    }

    int getDatabaseId() {
        return _databaseId;
    }

    // Mutators
    double setBalance(double amount) {
        _balance = amount;
        return _balance;
    }

    double addBalance(double amount) {
        _balance += amount;
        return _balance;
    }

    double subtractBalance(double amount) {
        _balance -= amount;
        return _balance;
    }
}
