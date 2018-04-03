package me.nickdev.blockeffects.commands;

import me.nickdev.blockeffects.BlockEffects;
import me.nickdev.blockeffects.block.EBlock;
import me.nickdev.blockeffects.block.EBlockManager;
import me.nickdev.blockeffects.constants.O;
import me.nickdev.blockeffects.constants.P;
import me.nickdev.blockeffects.util.StringManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DefaultCommand implements CommandExecutor {

    private BlockEffects blockEffects;
    private EBlockManager blockManager;

    public DefaultCommand(BlockEffects blockEffects, EBlockManager blockManager) {
        this.blockEffects = blockEffects;
        this.blockManager = blockManager;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length == 0) {
            sendHelp(sender);
            return true;
        }
        if (args.length != 1) {
            sender.sendMessage(P.PREFIX + ChatColor.RED + O.WRONG_SYNTAX.getText());
            return true;
        }
        if (args[0].equalsIgnoreCase("help")) {
            sendHelp(sender);
            return true;
        }
        if (!args[0].equalsIgnoreCase("blocks")) {
            sender.sendMessage(P.PREFIX + ChatColor.RED + O.UNKNOWN_ARGS.getText());
            return true;
        }
        if (!sender.hasPermission("blockeffects.blocks")) {
            sender.sendMessage(P.PREFIX + O.NO_PERMISSION.getText());
            return true;
        }
        sender.sendMessage(P.PREFIX + ChatColor.GRAY + "Registered Blocks");
        for (EBlock block : blockManager.getEBlocks()) {
            sender.sendMessage("----------------");
            for (String line : block.info()) {
                sender.sendMessage(ChatColor.GRAY + StringManager.color(line));
            }
        }
        return true;
    }

    private void sendHelp(CommandSender sender) {
        sender.sendMessage(P.PREFIX + ChatColor.GOLD + "BlockEffects v" + blockEffects.getVersion() + ChatColor.GRAY + " coded by NickDEV.");
        sender.sendMessage(ChatColor.GRAY + "To see the registered blocks use: " + ChatColor.WHITE + "/be blocks");
    }
}
