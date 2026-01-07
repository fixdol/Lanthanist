package com.fxd927.lanthanist.registries;

import com.fxd927.lanthanist.Lanthanist;
import com.fxd927.lanthanist.block.blockentity.BasicMachineCasingBlockEntity;
import com.fxd927.lanthanist.block.blockentity.LightAccumulatorBlockEntity;
import com.fxd927.lanthanist.block.blockentity.SunAbsorberBlockEntity;
import com.fxd927.lanthanist.block.cable.GlassOpticalFiberCableBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class LanthanistBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Lanthanist.MODID);

    public static final Supplier<BlockEntityType<SunAbsorberBlockEntity>> SUN_ABSORBER;
    public static final Supplier<BlockEntityType<GlassOpticalFiberCableBlockEntity>> GLASS_OPTICAL_FIBER_CABLE;
    public static final Supplier<BlockEntityType<LightAccumulatorBlockEntity>> LIGHT_ACCUMULATOR;
    public static final Supplier<BlockEntityType<BasicMachineCasingBlockEntity>> BASIC_MACHINE_CASING;


    static {
        SUN_ABSORBER = BLOCK_ENTITY_TYPES.register(
                "sun_absorber",
                () -> BlockEntityType.Builder.of(
                                SunAbsorberBlockEntity::new,
                                LanthanistBlocks.SUN_ABSORBER.get()
                        )
                        .build(null)
        );
        GLASS_OPTICAL_FIBER_CABLE = BLOCK_ENTITY_TYPES.register(
                "grass_optical_fiber_cable",
                () -> BlockEntityType.Builder.of(
                        GlassOpticalFiberCableBlockEntity::new,
                        LanthanistBlocks.GLASS_OPTICAL_FIBER_CABLE.get()
                ).build(null)
        );
        LIGHT_ACCUMULATOR = BLOCK_ENTITY_TYPES.register(
                "light_accumulator",
                () -> BlockEntityType.Builder.of(
                        LightAccumulatorBlockEntity::new,
                        LanthanistBlocks.LIGHT_ACCUMULATOR_BLOCK.get()
                ).build(null)
        );
        BASIC_MACHINE_CASING = BLOCK_ENTITY_TYPES.register(
                "basic_machine_casing",
                () -> BlockEntityType.Builder.of(
                        BasicMachineCasingBlockEntity::new,
                        LanthanistBlocks.BASIC_MACHINE_CASING_BLOCK.get()
                ).build(null)
        );
    }

    private LanthanistBlockEntityTypes(){
    }
}
