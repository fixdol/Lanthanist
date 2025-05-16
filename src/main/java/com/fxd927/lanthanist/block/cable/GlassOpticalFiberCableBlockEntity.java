package com.fxd927.lanthanist.block.cable;

import com.fxd927.lanthanist.registries.LanthanistBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class GlassOpticalFiberCableBlockEntity extends OpticalFiberBlockEntity {
    public GlassOpticalFiberCableBlockEntity(BlockPos pos, BlockState blockState) {
        super(LanthanistBlockEntityTypes.GLASS_OPTICAL_FIBER_CABLE.get(), pos, blockState, 1000);
    }
}
