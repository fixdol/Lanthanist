package com.fxd927.lanthanist.worldgen;

import com.fxd927.lanthanist.Lanthanist;
import com.fxd927.lanthanist.registries.LanthanistBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class LanthanistConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> RARE_EARTH_ORE_KEY = registerKey("rare-earth_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?,?>> context){
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> rareEarthOres = List.of(
                OreConfiguration.target(stoneReplaceables, LanthanistBlocks.RARE_EARTH_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, LanthanistBlocks.DEEPSLATE_RARE_EARTH_ORE.get().defaultBlockState()));

        register(context, RARE_EARTH_ORE_KEY, Feature.ORE, new OreConfiguration(rareEarthOres, 6));
    }

    public static ResourceKey<ConfiguredFeature<?,?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Lanthanist.MODID, name));
    }
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?,?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?,?>> key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
