package com.fxd927.lanthanist.datagen;

import com.fxd927.lanthanist.Lanthanist;
import com.fxd927.lanthanist.registries.LanthanistBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class LanthanistBlockTagProvider extends BlockTagsProvider {
    public LanthanistBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Lanthanist.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
       tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(LanthanistBlocks.CERIUM_BLOCK.get())
                .add(LanthanistBlocks.DYSPROSIUM_BLOCK.get())
                .add(LanthanistBlocks.LANTHANUM_BLOCK.get())
                .add(LanthanistBlocks.RARE_EARTH_ORE.get())
                .add(LanthanistBlocks.DEEPSLATE_RARE_EARTH_ORE.get())
                .add(LanthanistBlocks.RAW_RARE_EARTH_BLOCK.get())
                .add(LanthanistBlocks.SUN_ABSORBER.get());
    }
}
