package com.fxd927.lanthanist;

import com.fxd927.lanthanist.registries.*;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(Lanthanist.MODID)
public class Lanthanist
{
    public static final String MODID = "lanthanist";

    public Lanthanist(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);
        LanthanistCreativeModeTabs.CREATIVE_MODE_TAB.register(modEventBus);
        LanthanistBlocks.BLOCKS.register(modEventBus);
        LanthanistBlockEntityTypes.BLOCK_ENTITY_TYPES.register(modEventBus);
        LanthanistItems.ITEMS.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(LanthanistItems.DYSPROSIUM_INGOT);
            event.accept(LanthanistItems.DYSPROSIUM_NUGGET);
        }
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.accept(LanthanistBlocks.DYSPROSIUM_BLOCK);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
        }

        //@SubscribeEvent
        //public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
            //event.registerBlockEntityRenderer(LanthanistBlockEntityTypes.BASIC_MACHINE_CASING.get(),
                    //context -> new BasicMachineCasingRenderer()
            //);
        //}
    }
}
