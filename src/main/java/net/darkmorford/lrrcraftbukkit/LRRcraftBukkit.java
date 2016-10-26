package net.darkmorford.lrrcraftbukkit;

import org.bukkit.plugin.java.JavaPlugin;

public class LRRcraftBukkit extends JavaPlugin {
    @Override
    public void onEnable() {
        // Connect the refresh command to its implementation
        getCommand("lrr refresh").setExecutor(new RefreshCommand());
    }

    @Override
    public void onDisable() {
    }
}
