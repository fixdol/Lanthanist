package com.fxd927.lanthanist.datagen;

import com.fxd927.lanthanist.registries.LanthanistBlocks;
import com.fxd927.lanthanist.registries.LanthanistItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class LanthanistBlockLootTableProvider extends BlockLootSubProvider {
    protected LanthanistBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(LanthanistBlocks.DYSPROSIUM_BLOCK.get());
        dropSelf(LanthanistBlocks.RAW_RARE_EARTH_BLOCK.get());
        dropSelf(LanthanistBlocks.LIGHT_ACCUMULATOR.get());

        add(LanthanistBlocks.RARE_EARTH_ORE.get(),
                block -> createOreDrop(LanthanistBlocks.RARE_EARTH_ORE.get(), LanthanistItems.RAW_RARE_EARTH.get()));
        add(LanthanistBlocks.DEEPSLATE_RARE_EARTH_ORE.get(),
                block -> createOreDrop(LanthanistBlocks.DEEPSLATE_RARE_EARTH_ORE.get(), LanthanistItems.RAW_RARE_EARTH.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return LanthanistBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
