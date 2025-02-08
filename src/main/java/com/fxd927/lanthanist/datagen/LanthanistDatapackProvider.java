package com.fxd927.lanthanist.datagen;

import com.fxd927.lanthanist.Lanthanist;
import com.fxd927.lanthanist.worldgen.LanthanistBiomeModifiers;
import com.fxd927.lanthanist.worldgen.LanthanistConfiguredFeatures;
import com.fxd927.lanthanist.worldgen.LanthanistPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class LanthanistDatapackProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, LanthanistConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, LanthanistPlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, LanthanistBiomeModifiers::bootstrap)
            ;
    public LanthanistDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries){
        super(output, registries, BUILDER, Set.of(Lanthanist.MODID));
    }
}
