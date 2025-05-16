package com.fxd927.lanthanist.block.cable;

import com.fxd927.lanthanist.light.ILightStorage;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.IBlockCapabilityProvider;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public abstract class OpticalFiberBlock extends CableBlock implements IBlockCapabilityProvider<ILightStorage, Direction> {
    public OpticalFiberBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canConnectTo(LevelAccessor levelAccessor, BlockPos pos, Direction direction) {
        if (!(levelAccessor instanceof Level level)) {
            return false;
        }

        return level.getCapability(Capabilities.EnergyStorage.BLOCK, pos, direction.getOpposite()) != null;
    }

    @Override
    public @Nullable ILightStorage getCapability(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState, @Nullable BlockEntity blockEntity, Direction direction) {
        if (blockEntity instanceof OpticalFiberBlockEntity opticalFiberBlockEntity) {
            return opticalFiberBlockEntity.getLightStorage();
        }
        return null;
    }

    @Override
    public abstract @Nullable BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState);

    @Override
    public abstract @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> blockEntityType);

    @Override
    public abstract @NotNull MapCodec<? extends DirectionalBlock> codec();
}
