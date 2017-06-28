package me.nickdev.blockeffects.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class StringManager {

    public static String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static String playerFormat(String str, Player player) {
        return str.replaceAll("%player%", player.getName());
    }
}
