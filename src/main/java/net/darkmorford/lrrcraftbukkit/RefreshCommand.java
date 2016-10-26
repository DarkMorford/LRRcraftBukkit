package net.darkmorford.lrrcraftbukkit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RefreshCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        sender.sendMessage("You have reached the LRR twitch channel");
        return true;
    }
}
