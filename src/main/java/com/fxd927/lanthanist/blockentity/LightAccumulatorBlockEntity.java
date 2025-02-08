package com.fxd927.lanthanist.blockentity;

import com.fxd927.lanthanist.light.ILightStorage;
import com.fxd927.lanthanist.registries.LanthanistBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class LightAccumulatorBlockEntity extends BlockEntity implements ILightStorage {
    private static final int MAX_LIGHT = 100000;
    private static final int GENERATION_RATE = 100;
    private int lightStored = 0;

    public LightAccumulatorBlockEntity(BlockPos pos, BlockState state) {
        super(LanthanistBlockEntity.LIGHT_ACCUMULATOR.get(), pos, state);
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T t) {
        if (t instanceof LightAccumulatorBlockEntity blockEntity) {
            if (level.isDay()) {
                blockEntity.lightStored = Math.min(blockEntity.lightStored + GENERATION_RATE, MAX_LIGHT);
            }

            if (!level.isClientSide) {
                level.getEntitiesOfClass(Player.class, new AABB(pos).inflate(5))
                        .forEach(player -> player.sendSystemMessage(Component.literal("Light Stored: " + blockEntity.lightStored)));
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
