package com.fxd927.lanthanist.block;

import com.fxd927.lanthanist.Lanthanist;
import com.fxd927.lanthanist.item.LanthanistItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class LanthanistBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Lanthanist.MODID);

    public static final DeferredBlock<Block> DYSPROSIUM_BLOCK;

    private static <T extends  Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block){
        LanthanistItems.ITEMS.register(name, ()-> new BlockItem(block.get(), new Item.Properties()));
    }

    static {
        DYSPROSIUM_BLOCK = BLOCKS.register("dysprosium_block", () -> new Block(BlockBehaviour.Properties.of()
                .requiresCorrectToolForDrops()
                .strength(5.0F, 6.0F)
                .sound(SoundType.METAL)
        ));
    }
}
