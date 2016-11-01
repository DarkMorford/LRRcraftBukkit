package net.darkmorford.lrrcraftbukkit.Economy;

import net.milkbowl.vault.economy.AbstractEconomy;
import net.milkbowl.vault.economy.EconomyResponse;

import java.util.List;

public class LRRconomy extends AbstractEconomy {
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return "LRRconomy";
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 0;
    }

    @Override
    public String format(double amount) {
        return String.format("%d DB", (int) amount);
    }

    @Override
    public String currencyNamePlural() {
        return "Desert Bucks";
    }

    @Override
    public String currencyNameSingular() {
        return "Desert Buck";
    }

    @Override
    public boolean hasAccount(String playerName) {
        return false;
    }

    @Override
    public boolean hasAccount(String playerName, String worldName) {
        return hasAccount(playerName);
    }

    @Override
    public double getBalance(String playerName) {
        return 0;
    }

    @Override
    public double getBalance(String playerName, String worldName) {
        return getBalance(playerName);
    }

    @Override
    public boolean has(String playerName, double amount) {
        return false;
    }

    @Override
    public boolean has(String playerName, String worldName, double amount) {
        return has(playerName, amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        return withdrawPlayer(playerName, amount);
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, double amount) {
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        return depositPlayer(playerName, amount);
    }

    @Override
    public EconomyResponse createBank(String bankName, String playerName) {
        return null;
    }

    @Override
    public EconomyResponse deleteBank(String bankName) {
        return null;
    }

    @Override
    public EconomyResponse bankBalance(String bankName) {
        return null;
    }

    @Override
    public EconomyResponse bankHas(String bankName, double amount) {
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String bankName, double amount) {
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String bankName, double amount) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String bankName, String playerName) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String bankName, String playerName) {
        return null;
    }

    @Override
    public List<String> getBanks() {
        return null;
    }

    @Override
    public boolean createPlayerAccount(String playerName) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(String playerName, String worldName) {
        return createPlayerAccount(playerName);
    }
}
