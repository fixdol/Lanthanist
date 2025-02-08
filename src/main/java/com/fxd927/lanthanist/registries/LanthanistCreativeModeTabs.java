package com.fxd927.lanthanist.registries;

import com.fxd927.lanthanist.Lanthanist;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class LanthanistCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Lanthanist.MODID);

    public static final Supplier<CreativeModeTab> LANTHANIST = CREATIVE_MODE_TAB.register("lanthanist",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(LanthanistItems.DYSPROSIUM_INGOT.get()))
                    .title(Component.translatable("creativetab.lanthanist.lanthanist"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(LanthanistBlocks.RARE_EARTH_ORE);
                        output.accept(LanthanistBlocks.DEEPSLATE_RARE_EARTH_ORE);
                        output.accept(LanthanistBlocks.RAW_RARE_EARTH_BLOCK);
                        output.accept(LanthanistBlocks.DYSPROSIUM_BLOCK);
                        output.accept(LanthanistBlocks.LIGHT_ACCUMULATOR);
                        output.accept(LanthanistItems.RAW_RARE_EARTH);
                        output.accept(LanthanistItems.DYSPROSIUM_INGOT);
                        output.accept(LanthanistItems.DYSPROSIUM_NUGGET);
                    }).build());

    private LanthanistCreativeModeTabs(){
    }
}
