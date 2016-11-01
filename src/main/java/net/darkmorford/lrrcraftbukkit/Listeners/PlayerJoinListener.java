package net.darkmorford.lrrcraftbukkit.Listeners;

import net.darkmorford.lrrcraftbukkit.LRRcraftBukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String name = player.getDisplayName();

        LRRcraftBukkit.logger.info(name + " has joined the server.");
    }

    @EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getDisplayName();
        String fromWorld = event.getFrom().getName();
        String toWorld = player.getWorld().getName();

        LRRcraftBukkit.logger.info(playerName + " has just gone from " + fromWorld + " to " + toWorld);
    }

}
