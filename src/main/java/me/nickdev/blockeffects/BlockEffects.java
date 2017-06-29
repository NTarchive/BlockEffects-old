package me.nickdev.blockeffects;

import me.nickdev.blockeffects.block.EBlockManager;
import me.nickdev.blockeffects.commands.RegisterCommands;
import me.nickdev.blockeffects.listeners.RegisterListeners;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class BlockEffects extends JavaPlugin {

    private String prefix = ChatColor.GOLD + "[BlockEffects] ";
    private String prefixNoColor = "[BlockEffects] ";
    private String prefixFlat = "BlockEffects ";

    private PluginDescriptionFile pdf = this.getDescription();
    private String version = pdf.getVersion();

    @Override
    public void onEnable() {
        Bukkit.getLogger().info(prefixNoColor + prefixFlat + "v" + version + " has been enabled.");

        this.print("Preparing the config files.");
        saveDefaultConfig();

        EBlockManager eBlockManager = new EBlockManager(this);

        this.print("Registering commands & listeners.");
        new RegisterCommands(this, eBlockManager);
        new RegisterListeners(this, eBlockManager);

        updateConfig();
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(prefixNoColor + prefixFlat + "v" + version + " has been disabled.");
    }

    public void print(String message) {
        System.out.println(prefixNoColor + message);
    }

    public String getPrefix() {
        return prefix;
    }

    public String getVersion() {
        return version;
    }

    private void updateConfig() {
        // not working
        boolean updated = false;
        if (getConfig().getString("config-version").equals("1.0")) {
            ArrayList<String> worlds = new ArrayList<>();
            worlds.add("world");
            getConfig().addDefault("enabled-worlds", worlds);
            updated = true;
        }
        if (updated) print("The config has been updated to a newer version.");
        saveDefaultConfig();
    }
}
