package com.fxd927.lanthanist.block.blockentity;

import com.fxd927.lanthanist.light.ILightStorage;
import com.fxd927.lanthanist.registries.LanthanistBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class LightAccumulatorBlockEntity extends BlockEntity implements ILightStorage {
    private static final int MAX_LIGHT = 500000;
    private int lightStored = 0;

    public LightAccumulatorBlockEntity(BlockPos pos, BlockState state) {
        super(LanthanistBlockEntityTypes.LIGHT_ACCUMULATOR.get(), pos, state);
    }

    @Override
    public int receiveLight(int maxReceive, boolean simulate) {
        int received = Math.min(MAX_LIGHT - lightStored, maxReceive);
        if (!simulate) {
            lightStored += received;
        }
        return received;
    }

    @Override
    public int extractLight(int maxExtract, boolean simulate) {
        int extracted = Math.min(lightStored, maxExtract);
        if (!simulate) {
            lightStored -= extracted;
        }
        return extracted;
    }

    @Override public int getLightStored() {
        return lightStored;
    }

    @Override public int getMaxLightStored() {
        return MAX_LIGHT;
    }

    @Override public boolean canExtract() {
        return false;
    }

    @Override public boolean canReceive() {
        return true;
    }
}
