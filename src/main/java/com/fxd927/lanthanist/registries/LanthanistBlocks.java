package com.fxd927.lanthanist.registries;

import com.fxd927.lanthanist.Lanthanist;
import com.fxd927.lanthanist.block.BasicMachineCasingBlock;
import com.fxd927.lanthanist.block.LightAccumulatorBlock;
import com.fxd927.lanthanist.block.SunAbsorberBlock;
import com.fxd927.lanthanist.block.cable.GlassOpticalFiberCableBlock;
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

    public static final DeferredBlock<Block> CERIUM_BLOCK;
    public static final DeferredBlock<Block> DYSPROSIUM_BLOCK;
    public static final DeferredBlock<Block> LANTHANUM_BLOCK;
    public static final DeferredBlock<Block> RARE_EARTH_ORE;
    public static final DeferredBlock<Block> DEEPSLATE_RARE_EARTH_ORE;
    public static final DeferredBlock<Block> RAW_RARE_EARTH_BLOCK;
    public static final DeferredBlock<SunAbsorberBlock> SUN_ABSORBER;
    public static final DeferredBlock<GlassOpticalFiberCableBlock> GLASS_OPTICAL_FIBER_CABLE;
    public static final DeferredBlock<LightAccumulatorBlock> LIGHT_ACCUMULATOR_BLOCK;
    public static final DeferredBlock<BasicMachineCasingBlock> BASIC_MACHINE_CASING_BLOCK;

    private static <T extends  Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block){
        LanthanistItems.ITEMS.register(name, ()-> new BlockItem(block.get(), new Item.Properties()));
    }

    static {
        CERIUM_BLOCK = registerBlock("cerium_block", () -> new Block(BlockBehaviour.Properties.of()
                .requiresCorrectToolForDrops()
                .strength(5.0F, 6.0F)
                .sound(SoundType.METAL)
        ));
        DYSPROSIUM_BLOCK = registerBlock("dysprosium_block", () -> new Block(BlockBehaviour.Properties.of()
                .requiresCorrectToolForDrops()
                .strength(5.0F, 6.0F)
                .sound(SoundType.METAL)
        ));
        LANTHANUM_BLOCK = registerBlock("lanthanum_block", () -> new Block(BlockBehaviour.Properties.of()
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
        RAW_RARE_EARTH_BLOCK = registerBlock("raw_rare-earth_block", () -> new Block(BlockBehaviour.Properties.of()
                .requiresCorrectToolForDrops()
                .strength(5.0F, 6.0F)
                .sound(SoundType.STONE)
        ));
        SUN_ABSORBER = registerBlock("sun_absorber", () -> new SunAbsorberBlock(BlockBehaviour.Properties.of()
                .requiresCorrectToolForDrops()
                .strength(5.0f,6.0F)
                .sound(SoundType.METAL)
        ));
        GLASS_OPTICAL_FIBER_CABLE = registerBlock("glass_optical_fiber_block", () -> new GlassOpticalFiberCableBlock(BlockBehaviour.Properties.of()
                .requiresCorrectToolForDrops()
                .strength(5.0f,6.0F)
                .sound(SoundType.GLASS)
        ));
        LIGHT_ACCUMULATOR_BLOCK = registerBlock("light_accumulator", () -> new LightAccumulatorBlock(BlockBehaviour.Properties.of()
                .requiresCorrectToolForDrops()
                .strength(5.0f,6.0F)
                .sound(SoundType.METAL)
        ));
        BASIC_MACHINE_CASING_BLOCK = registerBlock("basic_machine_casing", () -> new BasicMachineCasingBlock(BlockBehaviour.Properties.of()
                .requiresCorrectToolForDrops()
                .strength(5.0f,6.0F)
                .sound(SoundType.METAL)
        ));
    }

    private LanthanistBlocks(){
    }
}
