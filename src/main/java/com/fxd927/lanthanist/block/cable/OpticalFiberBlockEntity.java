package com.fxd927.lanthanist.block.cable;

import com.fxd927.lanthanist.light.ILightStorage;
import com.fxd927.lanthanist.light.ILightStorageProvider;
import com.fxd927.lanthanist.light.LightStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public abstract class OpticalFiberBlockEntity extends CableBlockEntity<OpticalFiberBlockEntity> implements ILightStorageProvider {
    protected final int maxLightTransfer;
    protected final LightStorage simpleLightStorage;

    public OpticalFiberBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState, int maxLightTransfer) {
        super(type, pos, blockState);

        this.maxLightTransfer = maxLightTransfer;
        this.simpleLightStorage = new LightStorage(4000, 4000, maxLightTransfer);
    }

    @Override
    public ILightStorage getLightStorage() {
        return simpleLightStorage;
    }

    public void tick(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull OpticalFiberBlockEntity blockEntity) {
        if (level.isClientSide) return;
        int currentLight = simpleLightStorage.getLightStored();

        if (currentLight <= 0) return;


    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.loadAdditional(tag, registries);

        simpleLightStorage.receiveLight(tag.getInt("light"), false);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.saveAdditional(tag, registries);

        tag.putInt("light", simpleLightStorage.getLightStored());
    }
}
