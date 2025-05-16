package com.fxd927.lanthanist.light;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Mth;
import net.neoforged.neoforge.common.util.INBTSerializable;

public class LightStorage implements ILightStorage, INBTSerializable<Tag> {
    protected int light;
    protected int capacity;
    protected int maxReceive;
    protected int maxExtract;

    public LightStorage(int capacity) {
        this(capacity, capacity, capacity, 0);
    }

    public LightStorage(int capacity, int maxTransfer) {
        this(capacity, maxTransfer, maxTransfer, 0);
    }

    public LightStorage(int capacity, int maxReceive, int maxExtract) {
        this(capacity, maxReceive, maxExtract, 0);
    }

    public LightStorage(int capacity, int maxReceive, int maxExtract, int light) {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.light = Math.max(0, Math.min(capacity, light));
    }

    public int receiveLight(int toReceive, boolean simulate) {
        if (this.canReceive() && toReceive > 0) {
            int lightReceived = Mth.clamp(this.capacity - this.light, 0, Math.min(this.maxReceive, toReceive));
            if (!simulate) {
                this.light += lightReceived;
            }

            return lightReceived;
        } else {
            return 0;
        }
    }

    public int extractLight(int toExtract, boolean simulate) {
        if (this.canExtract() && toExtract > 0) {
            int lightExtracted = Math.min(this.light, Math.min(this.maxExtract, toExtract));
            if (!simulate) {
                this.light -= lightExtracted;
            }

            return lightExtracted;
        } else {
            return 0;
        }
    }

    public int getLightStored() {
        return this.light;
    }

    public int getMaxLightStored() {
        return this.capacity;
    }

    public boolean canExtract() {
        return this.maxExtract > 0;
    }

    public boolean canReceive() {
        return this.maxReceive > 0;
    }

    public Tag serializeNBT(HolderLookup.Provider provider) {
        return IntTag.valueOf(this.getLightStored());
    }

    public void deserializeNBT(HolderLookup.Provider provider, Tag nbt) {
        if (nbt instanceof IntTag intNbt) {
            this.light = intNbt.getAsInt();
        } else {
            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
        }
    }
}
