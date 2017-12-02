package me.nickdev.blockeffects.config;

import me.nickdev.blockeffects.BlockEffects;
import me.nickdev.blockeffects.block.EBlock;
import me.nickdev.blockeffects.util.item.ItemManager;
import me.nickdev.blockeffects.util.item.MaterialData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class ConfigManager  {
    
    private ConfigurationSection blockSection;
    
    private ArrayList<World> enabledWorlds = new ArrayList<>();
    private boolean security;
    private boolean permission;
    private int securityTime;

    public ConfigManager(BlockEffects blockEffects) {
        FileConfiguration config = blockEffects.getConfig();
        this.blockSection = config.getConfigurationSection("blocks");

        for (String worldName : config.getStringList("enabled-worlds")) {
            enabledWorlds.add(Bukkit.getWorld(worldName));
        }
        security = config.getBoolean("security.enabled");
        permission = config.getBoolean("no-permission.send");
        securityTime = config.getInt("security.cooldown") * 20;
    }

    public EBlock getEBlock( String name) {
         Material mat = ItemManager.getMaterial(blockSection.getString(name + ".material"));
         short data = ItemManager.getData(blockSection.getString(name + ".material"));
         String message = blockSection.getString(name + ".message");
         String permission = blockSection.getString(name + ".permission");
         List<String> commands = blockSection.getStringList(name + ".commands");
         PotionEffectType potionEffectType = PotionEffectType.getByName(blockSection.getString(name + ".effect.type"));
         int duration = blockSection.getInt(name + ".effect.duration");
         int amplifier = blockSection.getInt(name + ".effect.amplifier");

         return new EBlock(name, new MaterialData(mat, data), new PotionEffect(potionEffectType, duration, amplifier), message, permission, commands);
    }

    public ConfigurationSection getBlockSection() {
        return blockSection;
    }

    public ArrayList<World> getEnabledWorlds() {
        return enabledWorlds;
    }

    public boolean isSecurityEnabled() {
        return security;
    }

    public boolean isNoPermissionEnabled() {
        return permission;
    }

    public int getSecurityTime() {
        return securityTime;
    }
}
