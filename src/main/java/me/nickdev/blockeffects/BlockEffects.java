package me.nickdev.blockeffects;

import me.nickdev.blockeffects.block.EBlockManager;
import me.nickdev.blockeffects.commands.RegisterCommands;
import me.nickdev.blockeffects.config.ConfigManager;
import me.nickdev.blockeffects.listeners.RegisterListeners;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockEffects extends JavaPlugin {

    private PluginDescriptionFile pdf = getDescription();
    private String version = pdf.getVersion();

    public void onEnable() {
        Bukkit.getLogger().info("[BlockEffects] BlockEffects v" + version + " has been enabled.");

        print("Preparing the config files.");
        saveDefaultConfig();

        ConfigManager configManager = new ConfigManager(this);
        EBlockManager blockManager = new EBlockManager(configManager);

        print("Registering commands & listeners.");
        new RegisterCommands(this, blockManager);
        new RegisterListeners(this, blockManager, configManager);
    }

    public void onDisable() {
        Bukkit.getLogger().info("[BlockEffects] BlockEffects v" + version + " has been disabled.");
    }

    public static void print(String message) {
        System.out.println("[BlockEffects] " + message);
    }

    public String getVersion() {
        return version;
    }
}
