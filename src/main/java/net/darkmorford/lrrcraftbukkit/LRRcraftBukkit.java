package net.darkmorford.lrrcraftbukkit;

import net.darkmorford.lrrcraftbukkit.Commands.EconomyCommands;
import net.darkmorford.lrrcraftbukkit.Economy.AccountManager;
import net.darkmorford.lrrcraftbukkit.Listeners.PlayerJoinListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class LRRcraftBukkit extends JavaPlugin {

    public static Logger logger;
    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        // Keep a reference to this plugin for other use
        plugin = this;

        // Get the logger
        logger = getLogger();

        // Save the default config if necessary
        // (If config.yml already exists, this will not overwrite it)
        saveDefaultConfig();

        // Connect the commands to their implementation
        logger.info("Registering commands");
        EconomyCommands econ = new EconomyCommands();
        getCommand("money").setExecutor(econ);

        // Set up our event listeners
        logger.info("Registering event listeners");
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);

        // Set up the account manager
        logger.info("Creating account manager");
        AccountManager mgr = new AccountManager();

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
