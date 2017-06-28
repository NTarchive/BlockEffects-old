package me.nickdev.blockeffects.block;

import me.nickdev.blockeffects.util.StringManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class EBlock {

    private String name;
    private Material material;
    private short data;

    private String message;
    private int cooldown;
    private PotionEffectType potionEffect;
    private int duration;
    private int amplifier;
    private ArrayList<String> commands = new ArrayList<>();

    public EBlock(String name, Material material, short data, String message, int cooldown, PotionEffectType potionEffect, int duration, int amplifier, ArrayList<String> commands) {
        this.name = name;
        this.material = material;
        this.data = data;
        this.message = message;
        this.cooldown = cooldown;
        this.potionEffect = potionEffect;
        this.duration = duration;
        this.amplifier = amplifier;
        this.commands = commands;
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }

    public short getData() {
        return data;
    }

    public String getMessage() {
        return StringManager.color(message);
    }

    public int getCooldown() {
        return cooldown;
    }

    public PotionEffectType getPotionEffect() {
        return potionEffect;
    }

    public int getDuration() {
        return duration;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public ArrayList<String> getCommands() {
        return commands;
    }

    public void addEffect(Player player) {
        player.removePotionEffect(this.potionEffect);
        player.addPotionEffect(new PotionEffect(this.potionEffect, this.duration*20, this.amplifier-1));
    }

    public void sendMessage(Player player) {
        if (this.message == null) return;
        player.sendMessage(StringManager.color(this.message));
    }

    public void dispatchCommands(Player player) {
        if (this.commands == null || this.commands.size() == 0) return;
        for (String command : this.commands) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), StringManager.playerFormat(command, player));
        }
    }
}
