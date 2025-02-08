package com.fxd927.lanthanist.block;

import com.fxd927.lanthanist.blockentity.LightAccumulatorBlockEntity;
import com.fxd927.lanthanist.registries.LanthanistBlockEntity;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class LightAccumulatorBlock extends BaseEntityBlock {
    public static final MapCodec<LightAccumulatorBlock> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    BlockBehaviour.Properties.CODEC.fieldOf("properties").forGetter(block -> block.properties)
            ).apply(instance, LightAccumulatorBlock::new)
    );

    public LightAccumulatorBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LightAccumulatorBlockEntity(pos, state);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return type == LanthanistBlockEntity.LIGHT_ACCUMULATOR.get() ? LightAccumulatorBlockEntity::tick : null;
    }
}
