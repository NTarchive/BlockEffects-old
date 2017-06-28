package me.nickdev.blockeffects.commands;

import me.nickdev.blockeffects.BlockEffects;
import me.nickdev.blockeffects.block.EBlock;
import me.nickdev.blockeffects.block.EBlockManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DefaultCommand implements CommandExecutor {

    private BlockEffects blockEffects;
    private EBlockManager eBlockManager;

    public DefaultCommand(BlockEffects blockEffects, EBlockManager eBlockManager) {
        this.blockEffects = blockEffects;
        this.eBlockManager = eBlockManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length == 0) {
            this.sendHelp(sender);
            return true;
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("help")) {
                this.sendHelp(sender);
                return true;
            }
            /*if (args[0].equalsIgnoreCase("blocks")) {
                sender.sendMessage(blockEffects.getPrefix() + ChatColor.GRAY + "Registered Blocks");
                for (EBlock block : eBlockManager.getEBlocks().values()) {
                    sender.sendMessage(ChatColor.GRAY + "Name: " + ChatColor.WHITE + block.getName());
                    sender.sendMessage(ChatColor.GRAY + "Cooldown: " + ChatColor.WHITE + block.getCooldown());
                    sender.sendMessage(ChatColor.GRAY + "Message: " + ChatColor.WHITE + block.getMessage());
                    sender.sendMessage(ChatColor.GRAY + "Potion: " + ChatColor.WHITE + block.getPotionEffect());
                    sender.sendMessage(ChatColor.GRAY + "Duration: " + ChatColor.WHITE + block.getDuration());
                    sender.sendMessage(ChatColor.GRAY + "Amplifier: " + ChatColor.WHITE + block.getAmplifier());
                    sender.sendMessage(ChatColor.GRAY + "===========================");
                }
                return true;
            }*/
            sender.sendMessage(blockEffects.getPrefix() + ChatColor.RED + "Unknown args! /blockeffects");
            return true;
        }
        if (args.length > 1) {
            sender.sendMessage(blockEffects.getPrefix() + ChatColor.RED + "Wrong syntax! /blockeffects");
            return true;
        }
        return true;
    }

    private void sendHelp(CommandSender sender) {
        sender.sendMessage(ChatColor.GOLD + "BlockEffects v" + blockEffects.getVersion() + ChatColor.GRAY + " coded by NickDEV.");
    }
}
