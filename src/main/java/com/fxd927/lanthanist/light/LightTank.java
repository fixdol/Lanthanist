package com.fxd927.lanthanist.light;

import net.neoforged.neoforge.registries.DeferredHolder;

public class LightTank {
    private final int capacity;
    private int storedAmount;
    private DeferredHolder<Light, Light> storedLight;

    public LightTank(int capacity) {
        this.capacity = capacity;
        this.storedAmount = 0;
        this.storedLight = null;
    }

    public boolean canStore(DeferredHolder<Light, Light> light) {
        return storedLight == null || storedLight.equals(light);
    }

    public int addLight(DeferredHolder<Light, Light> light, int amount) {
        if (!canStore(light)) return 0;
        if (storedLight == null) storedLight = light;

        int space = capacity - storedAmount;
        int toStore = Math.min(amount, space);
        storedAmount += toStore;
        return toStore;
    }

    public int removeLight(int amount) {
        int toRemove = Math.min(amount, storedAmount);
        storedAmount -= toRemove;
        if (storedAmount == 0) storedLight = null;
        return toRemove;
    }

    public DeferredHolder<Light, Light> getStoredLight() {
        return storedLight;
    }

    public int getStoredAmount() {
        return storedAmount;
    }

    public int getCapacity() {
        return capacity;
    }
}
