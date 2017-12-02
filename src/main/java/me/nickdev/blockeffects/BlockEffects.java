package me.nickdev.blockeffects;

import me.nickdev.blockeffects.block.EBlockManager;
import me.nickdev.blockeffects.commands.RegisterCommands;
import me.nickdev.blockeffects.config.ConfigManager;
import me.nickdev.blockeffects.listeners.RegisterListeners;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockEffects extends JavaPlugin {

    private PluginDescriptionFile pdf = this.getDescription();
    private String version = pdf.getVersion();

    private ConfigManager configManager;
    private EBlockManager blockManager;

    public void onEnable() {
        Bukkit.getLogger().info("[BlockEffects] BlockEffects v" + version + " has been enabled.");

        this.print("Preparing the config files.");
        this.saveDefaultConfig();

        this.configManager = new ConfigManager(this);
        this.blockManager = new EBlockManager(this.configManager);

        this.print("Registering commands & listeners.");
        new RegisterCommands(this);
        new RegisterListeners(this);
    }

    public void onDisable() {
        Bukkit.getLogger().info("[BlockEffects] BlockEffects v" + this.version + " has been disabled.");
    }

    private void print(String message) {
        System.out.println("[BlockEffects] " + message);
    }

    public String getVersion() {
        return version;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public EBlockManager getBlockManager() {
        return blockManager;
    }
}
