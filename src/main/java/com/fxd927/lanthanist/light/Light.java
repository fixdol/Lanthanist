package com.fxd927.lanthanist.light;

import net.minecraft.resources.ResourceLocation;

public class Light {
    private final ResourceLocation name;
    private final int color;
    private final double wavelength;

    public Light(ResourceLocation name, int color, double wavelength) {
        this.name = name;
        this.color = color;
        this.wavelength = wavelength;
    }

    public ResourceLocation getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public double getWavelength() {
        return wavelength;
    }
}
