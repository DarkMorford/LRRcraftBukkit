package net.darkmorford.lrrcraftbukkit.Economy;

import net.darkmorford.lrrcraftbukkit.LRRcraftBukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class AccountManager {
    private static JavaPlugin plugin = LRRcraftBukkit.plugin;

    private SessionFactory sessionFactory;

    public AccountManager() {
        // Build the database configuration for Hibernate
        File dbFile = new File(plugin.getDataFolder(), "accounts.db");
        Configuration config = new Configuration()
                .setProperty("hibernate.connection.url", "jdbc:sqlite:" + dbFile.getAbsolutePath())
                .setProperty("hibernate.connection.driver_class", "org.sqlite.JDBC");

        // Create the Hibernate session factory
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties())
                .build();

        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }
}
