package net.darkmorford.lrrcraftbukkit.Economy;

import org.bukkit.entity.Player;

public class PlayerAccount {
    // Member variables
    private Player _player;
    private double _balance;

    // Constructors
    PlayerAccount(Player player, double balance) {
        _player = player;
        _balance = balance;
    }

    // Accessors
    Player getPlayer() {
        return _player;
    }

    double getBalance() {
        return _balance;
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
