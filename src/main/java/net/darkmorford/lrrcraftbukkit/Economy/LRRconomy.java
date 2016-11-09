package net.darkmorford.lrrcraftbukkit.Economy;

import net.milkbowl.vault.economy.AbstractEconomy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;

import java.util.List;

@SuppressWarnings("deprecation")
public class LRRconomy extends AbstractEconomy {
    private IAccountManager acctManager;

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
        PlayerAccount acct = acctManager.getPlayerAccount(playerName);
        return (acct != null);
    }

    @Override
    public boolean hasAccount(String playerName, String worldName) {
        return hasAccount(playerName);
    }

    @Override
    public double getBalance(String playerName) {
        PlayerAccount acct = acctManager.getPlayerAccount(playerName);
        if (acct == null)
            return 0;
        
        return acct.getBalance();
    }

    @Override
    public double getBalance(String playerName, String worldName) {
        return getBalance(playerName);
    }

    @Override
    public boolean has(String playerName, double amount) {
        double balance = getBalance(playerName);
        return balance >= amount;
    }

    @Override
    public boolean has(String playerName, String worldName, double amount) {
        double balance = getBalance(playerName, worldName);
        return balance >= amount;
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        // Get the account for the player
        PlayerAccount acct = acctManager.getPlayerAccount(playerName);

        // Make sure we actually got an account object
        if (acct == null)
            return new EconomyResponse(0, 0, ResponseType.FAILURE, playerName + " does not have an account");

        // Make sure they have enough
        if (acct.getBalance() < amount)
            return new EconomyResponse(0, acct.getBalance(), ResponseType.FAILURE, playerName + " doesn't have enough money");

        // Actually perform the transaction and commit it
        acct.subtractBalance(amount);
        acctManager.savePlayerAccount(acct);

        return new EconomyResponse(amount, acct.getBalance(), ResponseType.SUCCESS, "Withdrawal successful");
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        return withdrawPlayer(playerName, amount);
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, double amount) {
        // Get the account for the player
        PlayerAccount acct = acctManager.getPlayerAccount(playerName);

        // Make sure we actually got an account object
        if (acct == null)
            return new EconomyResponse(0, 0, ResponseType.FAILURE, playerName + " does not have an account");

        // Perform and commit the transaction
        acct.addBalance(amount);
        acctManager.savePlayerAccount(acct);

        return new EconomyResponse(amount, acct.getBalance(), ResponseType.SUCCESS, "Deposit successful");
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
        PlayerAccount acct = acctManager.createPlayerAccount(playerName);
        return (acct != null);
    }

    @Override
    public boolean createPlayerAccount(String playerName, String worldName) {
        return createPlayerAccount(playerName);
    }
}
