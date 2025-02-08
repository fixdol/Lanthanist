package com.fxd927.lanthanist.registries;

import com.fxd927.lanthanist.Lanthanist;
import com.fxd927.lanthanist.block.LightAccumulatorBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class LanthanistBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Lanthanist.MODID);

    public static final DeferredBlock<Block> DYSPROSIUM_BLOCK;
    public static final DeferredBlock<Block> RARE_EARTH_ORE;
    public static final DeferredBlock<Block> DEEPSLATE_RARE_EARTH_ORE;
    public static final DeferredBlock<LightAccumulatorBlock> LIGHT_ACCUMULATOR;

    private static <T extends  Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block){
        LanthanistItems.ITEMS.register(name, ()-> new BlockItem(block.get(), new Item.Properties()));
    }

    static {
        DYSPROSIUM_BLOCK = registerBlock("dysprosium_block", () -> new Block(BlockBehaviour.Properties.of()
                .requiresCorrectToolForDrops()
                .strength(5.0F, 6.0F)
                .sound(SoundType.METAL)
        ));
        RARE_EARTH_ORE = registerBlock("rare-earth_ore", () -> new Block(BlockBehaviour.Properties.of()
                .mapColor(MapColor.STONE)
                .requiresCorrectToolForDrops()
                .strength(5.0F, 6.0F)
                .sound(SoundType.STONE)
        ));
        DEEPSLATE_RARE_EARTH_ORE = registerBlock("deepslate_rare-earth_ore", () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(RARE_EARTH_ORE.get())
                .mapColor(MapColor.DEEPSLATE)
                .requiresCorrectToolForDrops()
                .strength(6.5F, 6.0F)
                .sound(SoundType.DEEPSLATE)
        ));
        LIGHT_ACCUMULATOR = registerBlock("light_accumulator", () -> new LightAccumulatorBlock(BlockBehaviour.Properties.of()
                .requiresCorrectToolForDrops()
                .strength(5.0f,6.0F)
                .sound(SoundType.METAL)
        ));
    }

    private LanthanistBlocks(){
    }
}
