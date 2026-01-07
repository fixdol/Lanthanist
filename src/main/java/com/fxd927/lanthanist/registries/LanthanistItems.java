package com.fxd927.lanthanist.registries;

import com.fxd927.lanthanist.Lanthanist;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LanthanistItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Lanthanist.MODID);

    public static final DeferredItem<Item> CERIUM_INGOT;
    public static final DeferredItem<Item> CERIUM_NUGGET;
    public static final DeferredItem<Item> DYSPROSIUM_INGOT;
    public static final DeferredItem<Item> DYSPROSIUM_NUGGET;
    public static final DeferredItem<Item> LANTHANUM_INGOT;
    public static final DeferredItem<Item> LANTHANUM_NUGGET;
    public static final DeferredItem<Item> RAW_RARE_EARTH;


    static {
        CERIUM_INGOT = ITEMS.register("cerium_ingot", () -> new Item(new Item.Properties()));
        CERIUM_NUGGET = ITEMS.register("cerium_nugget", () -> new Item(new Item.Properties()));
        DYSPROSIUM_INGOT = ITEMS.register("dysprosium_ingot", () -> new Item(new Item.Properties()));
        DYSPROSIUM_NUGGET = ITEMS.register("dysprosium_nugget", () -> new Item(new Item.Properties()));
        LANTHANUM_INGOT = ITEMS.register("lanthanum_ingot", () -> new Item(new Item.Properties()));
        LANTHANUM_NUGGET = ITEMS.register("lanthanum_nugget", () -> new Item(new Item.Properties()));
        RAW_RARE_EARTH = ITEMS.register("raw_rare-earth", () -> new Item(new Item.Properties()));
    }

    private LanthanistItems(){
    }
}
