package me.nickdev.blockeffects.listeners;

import me.nickdev.blockeffects.BlockEffects;
import me.nickdev.blockeffects.block.EBlock;
import me.nickdev.blockeffects.block.EBlockManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class PlayerMoveListener implements Listener {

    private BlockEffects blockEffects;
    private EBlockManager eBlockManager;
    private ArrayList<World> allowedWorlds = new ArrayList<>();

    private ArrayList<Player> securityCooldown = new ArrayList<>();

    public PlayerMoveListener(BlockEffects blockEffects, EBlockManager eBlockManager) {
        this.blockEffects = blockEffects;
        this.eBlockManager = eBlockManager;
        for (String worldName : blockEffects.getConfig().getStringList("enabled-worlds")) {
            allowedWorlds.add(Bukkit.getWorld(worldName));
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        Block block = player.getLocation().subtract(0, 1, 0).getBlock();

        if (!allowedWorlds.contains(player.getWorld())) return;

        if (securityCooldown.contains(player)) return;
        if (this.isNull(block)) return;
        if (!(eBlockManager.containsEBlock(block.getType()))) return;

        EBlock eBlock = eBlockManager.getEBlocks().get(block.getType());
        if (block.getData() != eBlock.getData()) return;
        eBlock.addEffect(player);
        eBlock.sendMessage(player);
        eBlock.dispatchCommands(player);

        if (blockEffects.getConfig().getBoolean("security.enabled")) {
            securityCooldown.add(player);
            new BukkitRunnable() {

                @Override
                public void run() {
                    securityCooldown.remove(player);
                }
            }.runTaskLater(blockEffects, blockEffects.getConfig().getInt("security.cooldown")*20L);
        }
    }

    private boolean isNull(Block block) {
        return block == null || block.getType() == null || block.getType() == Material.AIR;
    }
}
