package com.fxd927.lanthanist.datagen;

import com.fxd927.lanthanist.Lanthanist;
import com.fxd927.lanthanist.registries.LanthanistBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class LanthanistBlockStateProvider extends BlockStateProvider {
    public LanthanistBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Lanthanist.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(LanthanistBlocks.DYSPROSIUM_BLOCK);
        blockWithItem(LanthanistBlocks.RARE_EARTH_ORE);
        blockWithItem(LanthanistBlocks.DEEPSLATE_RARE_EARTH_ORE);
        blockWithItem(LanthanistBlocks.LIGHT_ACCUMULATOR);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
