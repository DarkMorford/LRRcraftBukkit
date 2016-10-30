package net.darkmorford.lrrcraftbukkit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class LRRcraftBukkit extends JavaPlugin {

    static Logger logger;
    static JavaPlugin plugin;

    @Override
    public void onEnable() {
        // Keep a reference to this plugin for other use
        plugin = this;

        // Get the logger
        logger = getLogger();

        // Connect the refresh command to its implementation
        logger.info("Registering commands");
//      getCommand("lrrcraftbukkit").setExecutor(new RefreshCommand());

//      logger.info("Registering recipes");
//      Recipe leatherRecipe = new FurnaceRecipe(new ItemStack(Material.LEATHER), Material.ROTTEN_FLESH);
//      Bukkit.addRecipe(leatherRecipe);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (cmd.getName().equalsIgnoreCase("lrrcraftbukkit")) {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("map")) {
                    sender.sendMessage("http://minecraft.darkmorford.net:8123");
                    return true;
                }

                if (args[0].equalsIgnoreCase("discord")) {
                    sender.sendMessage("https://discord.gg/qgeJqVm");
                    return true;
                }
            }
        }

        sender.sendMessage("That command is not yet implemented.");
        return true;
    }

    @Override
    public void onDisable() {
    }
}
