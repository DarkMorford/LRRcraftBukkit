package net.darkmorford.lrrcraftbukkit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class LRRcraftBukkit extends JavaPlugin {

    Logger logger;

    @Override
    public void onEnable() {
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
        sender.sendMessage("That command is not yet implemented.");
        return true;
    }

    @Override
    public void onDisable() {
    }
}
