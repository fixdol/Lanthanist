package com.fxd927.lanthanist.registries;

import com.fxd927.lanthanist.Lanthanist;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LanthanistItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Lanthanist.MODID);

    public static final DeferredItem<Item> DYSPROSIUM_INGOT;
    public static final DeferredItem<Item> DYSPROSIUM_NUGGET;

    static {
        DYSPROSIUM_INGOT = ITEMS.register("dysprosium_ingot", () -> new Item(new Item.Properties()));
        DYSPROSIUM_NUGGET = ITEMS.register("dysprosium_nugget", () -> new Item(new Item.Properties()));
    }
}
