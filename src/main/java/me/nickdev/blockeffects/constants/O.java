package me.nickdev.blockeffects.constants;

public enum O {
    /**
     * Messages used by the plugin.
     */
    WRONG_SYNTAX("Wrong syntax! /blockeffects"),
    UNKNOWN_ARGS("Unknown arguments! /blockeffects"),
    NO_PERMISSION("You don't have the permission to do that!");

    private String text;

    O(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
