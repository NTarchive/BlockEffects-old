package me.nickdev.blockeffects.commands;

import me.nickdev.blockeffects.BlockEffects;

public class RegisterCommands {

    public RegisterCommands(final BlockEffects blockEffects) {
        blockEffects.getCommand("blockeffects").setExecutor(new DefaultCommand(blockEffects));
    }
}
