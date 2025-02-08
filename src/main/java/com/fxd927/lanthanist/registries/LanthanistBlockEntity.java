package com.fxd927.lanthanist.registries;

import com.fxd927.lanthanist.Lanthanist;
import com.fxd927.lanthanist.blockentity.LightAccumulatorBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class LanthanistBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Lanthanist.MODID);

    public static final Supplier<BlockEntityType<LightAccumulatorBlockEntity>> LIGHT_ACCUMULATOR;

    static {
        LIGHT_ACCUMULATOR = BLOCK_ENTITY_TYPES.register(
                "light_accumulator",
                () -> BlockEntityType.Builder.of(
                                LightAccumulatorBlockEntity::new,
                                LanthanistBlocks.LIGHT_ACCUMULATOR.get()
                        )
                        .build(null)
        );
    }

    private LanthanistBlockEntity(){
    }
}
