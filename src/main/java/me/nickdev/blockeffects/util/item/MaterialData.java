package me.nickdev.blockeffects.util.item;

import org.bukkit.Material;

public class MaterialData {

    private Material material;
    private short data;

    public MaterialData(Material material, short data) {
        this.material = material;
        this.data = data;
    }

    public Material getMaterial() {
        return material;
    }

    public short getData() {
        return data;
    }
}
