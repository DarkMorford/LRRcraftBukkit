package net.darkmorford.lrrcraftbukkit.Economy;

public interface IAccountManager {
    public PlayerAccount createPlayerAccount(String playerName);
    public PlayerAccount createPlayerAccount(String playerName, double balance);

    public PlayerAccount getPlayerAccount(String playerName);

    public boolean savePlayerAccount(PlayerAccount account);
}
