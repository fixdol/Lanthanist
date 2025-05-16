package com.fxd927.lanthanist.block.blockentity;

import com.fxd927.lanthanist.light.ILightStorage;
import com.fxd927.lanthanist.registries.LanthanistBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SunAbsorberBlockEntity extends BlockEntity implements ILightStorage {
    private static final int MAX_LIGHT = 1000;
    private static final int GENERATION_RATE = 1;
    private int lightStored = 0;

    public SunAbsorberBlockEntity(BlockPos pos, BlockState state) {
        super(LanthanistBlockEntityTypes.SUN_ABSORBER.get(), pos, state);
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T t) {
        if (t instanceof SunAbsorberBlockEntity blockEntity) {
            if (level.isDay() && !level.isRaining()) {
                blockEntity.lightStored = Math.min(blockEntity.lightStored + GENERATION_RATE, MAX_LIGHT);
            }
        }
    }

    @Override
    public int receiveLight(int maxReceive, boolean simulate) {
        int energyReceived = Math.min(MAX_LIGHT - lightStored, maxReceive);
        if (!simulate) {
            lightStored += energyReceived;
        }
        return energyReceived;
    }

    @Override
    public int extractLight(int maxExtract, boolean simulate) {
        int energyExtracted = Math.min(lightStored, maxExtract);
        if (!simulate) {
            lightStored -= energyExtracted;
        }
        return energyExtracted;
    }

    @Override public int getLightStored() {
        return lightStored;
    }

    @Override public int getMaxLightStored() {
        return MAX_LIGHT;
    }

    @Override public boolean canExtract() {
        return true;
    }

    @Override public boolean canReceive() {
        return false;
    }
}
