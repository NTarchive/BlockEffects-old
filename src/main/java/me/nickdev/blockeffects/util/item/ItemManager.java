package me.nickdev.blockeffects.util.item;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class ItemManager {

    /**
     * Gets Material from string.
     *
     * @param str  String
     * @return  Material
     */
    public static Material getMaterial(String str) {
        return Material.valueOf(str.toUpperCase().replaceAll("\\d", "").replace(":", ""));
    }

    /**
     * Gets data from string.
     *
     * @param str  String
     * @return  Data
     */
    public static short getData(String str) {
        try {
            return Short.parseShort(str.replace(":", "").replaceAll(" ", "").replaceAll("[a-zA-Z]", "").replace("_", ""));
        }
        catch (NumberFormatException exc) {
            return 0;
        }
    }

    /**
     * Checks if a block is null.
     *
     * @param block  Block
     * @return  Boolean
     */
    public static boolean isNull(Block block) {
        return block == null || block.getType() == null || block.getType() == Material.AIR;
    }
}
