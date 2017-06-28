package me.nickdev.blockeffects.util;

import org.bukkit.Material;

public class ItemManager {

    public static Material getMaterial(String str) {
        return Material.valueOf(str.toUpperCase().replaceAll("\\d", "").replace(":", ""));
    }

    public static short getData(String str) {
        try {
            return Short.parseShort(str.replace(":", "").replaceAll(" ", "").replaceAll("[a-zA-Z]", "").replace("_", ""));
        } catch (NumberFormatException exc) {
            return 0;
        }
    }
}
