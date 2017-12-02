package me.nickdev.blockeffects.listeners;

import me.nickdev.blockeffects.BlockEffects;

public class RegisterListeners {

    public RegisterListeners(final BlockEffects blockEffects) {
        new PlayerMoveListener(blockEffects);
    }
}
