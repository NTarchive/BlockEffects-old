package me.nickdev.blockeffects.listeners;

import me.nickdev.blockeffects.BlockEffects;
import me.nickdev.blockeffects.block.EBlockManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class RegisterListeners {

    public RegisterListeners(BlockEffects blockEffects, EBlockManager eBlockManager) {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerMoveListener(blockEffects, eBlockManager), blockEffects);
    }
}
