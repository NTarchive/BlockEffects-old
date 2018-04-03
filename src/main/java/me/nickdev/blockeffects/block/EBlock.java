package me.nickdev.blockeffects.block;

import me.nickdev.blockeffects.util.StringManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffect;

import java.util.List;

public class EBlock  {

    // Required
    private String name;
    private MaterialData materialData;
    private PotionEffect potionEffect;

    // Optional
    private String message;
    private String permission;
    private List<String> commands;

    /**
     * Creates a new EBlock.
     *
     * @param name  Name
     * @param materialData  MaterialData
     * @param potionEffect  PotionEffect
     * @param message  Message
     * @param permission  Permission
     * @param commands  List of commands
     */
    public EBlock(String name, MaterialData materialData, PotionEffect potionEffect, String message, String permission, List<String> commands) {
        this.name = name;
        this.materialData = materialData;
        this.potionEffect = potionEffect;
        this.message = message;
        this.permission = permission;
        this.commands = commands;
    }

    /**
     * Adds PotionEffect to the player.
     *
     * @param player  Player
     */
    private void addEffect(Player player) {
        player.removePotionEffect(potionEffect.getType());
        player.addPotionEffect(potionEffect);
    }

    /**
     * Sends message (if specified) to the player.
     *
     * @param player  Player
     */
    private void sendMessage(Player player) {
        if (message == null) return;
        player.sendMessage(StringManager.color(message));
    }

    /**
     * Dispatches commands (if specified).
     *
     * @param player  Player
     */
    private void dispatchCommands(Player player) {
        if (commands == null || commands.size() == 0) return;
        for (String command : commands) Bukkit.dispatchCommand(Bukkit.getConsoleSender(), StringManager.playerFormat(command, player));
    }

    /**
     * Activates the effect.
     *
     * @param player  Player
     */
    public void activate(Player player) {
        addEffect(player);
        sendMessage(player);
        dispatchCommands(player);
    }

    public String getName() {
        return name;
    }

    public MaterialData getMaterialData() {
        return materialData;
    }

    public PotionEffect getPotionEffect() {
        return potionEffect;
    }

    public String getMessage() {
        return message;
    }

    public String getPermission() {
        return permission;
    }

    public List<String> getCommands() {
        return commands;
    }

    /**
     * Returns info about the EBlock.
     *
     * @return  String[] information
     */
    public String[] info() {
        if (message == null) message = "none";
        if (permission == null) permission = "none";

        return new String[] {
                "Name: " + name,
                "Block: " + materialData.getItemType() + ":" + materialData.getData(),
                "Effect: " + potionEffect.getType().getName() + " d: " + potionEffect.getDuration() + " a: " + potionEffect.getAmplifier(),
                "-----------------", "Message: " + message,
                "Permission: " + permission
        };
    }
}
