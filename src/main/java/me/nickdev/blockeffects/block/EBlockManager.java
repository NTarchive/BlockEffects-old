package me.nickdev.blockeffects.block;

import me.nickdev.blockeffects.BlockEffects;
import me.nickdev.blockeffects.util.ItemManager;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;

public class EBlockManager {

    private HashMap<Material, EBlock> effectBlockHashMap = new HashMap<>();

    public EBlockManager(BlockEffects blockEffects) {
        ConfigurationSection conf = blockEffects.getConfig().getConfigurationSection("blocks");
        for (String string : conf.getKeys(false)) {
            Material mat = ItemManager.getMaterial(conf.getString(string + ".material"));
            short data = ItemManager.getData(conf.getString(string + ".material"));
            String message = conf.getString(string + ".message");
            PotionEffectType potionEffectType = PotionEffectType.getByName(conf.getString(string + ".effect.type"));
            int duration = conf.getInt(string + ".effect.duration");
            int amplifier = conf.getInt(string + ".effect.amplifier");

            effectBlockHashMap.put(mat, new EBlock(string, mat, data, message, 0, potionEffectType, duration, amplifier, (ArrayList<String>) conf.getStringList(string + ".commands")));
        }
    }

    public HashMap<Material, EBlock> getEBlocks() {
        return effectBlockHashMap;
    }

    public boolean containsEBlock(Material material) {
        return effectBlockHashMap.keySet().contains(material);
    }
}
