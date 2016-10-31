package net.darkmorford.lrrcraftbukkit.Economy;

import net.darkmorford.lrrcraftbukkit.LRRcraftBukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class AccountManager {
    private Map<String, PlayerAccount> accounts;

    AccountManager() {
        accounts = new HashMap<>();
    }

    void save() {
        try {
            YamlConfiguration accountFile = new YamlConfiguration();

            accountFile.createSection("accounts", accounts);

            accountFile.save(new File(LRRcraftBukkit.plugin.getDataFolder(), "accounts.yml"));
        } catch (IOException e) {
            LRRcraftBukkit.plugin.getLogger().log(Level.SEVERE, "Save Failed!", e);
        }
    }

    void load() {
        try {
            File accountFile = new File(LRRcraftBukkit.plugin.getDataFolder(), "accounts.yml");
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
            LRRcraftBukkit.plugin.getLogger().log(Level.SEVERE, "Load Failed!", e);
        } catch (InvalidConfigurationException e) {
            LRRcraftBukkit.plugin.getLogger().log(Level.SEVERE, "Invalid Account File!", e);
        }
    }
}
