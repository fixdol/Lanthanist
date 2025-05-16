package com.fxd927.lanthanist.capabilities;

import com.fxd927.lanthanist.light.ILightStorage;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.ItemCapability;
import org.jetbrains.annotations.Nullable;

public final class LanthanistCapabilities {
    private static ResourceLocation create(String path) {
        return ResourceLocation.fromNamespaceAndPath("lanthanist", path);
    }

    private LanthanistCapabilities() {
    }

    public static final class LightStorage {
        public static final BlockCapability<ILightStorage, @Nullable Direction> BLOCK = BlockCapability.createSided(LanthanistCapabilities.create("light"), ILightStorage.class);
        public static final EntityCapability<ILightStorage, @Nullable Direction> ENTITY = EntityCapability.createSided(LanthanistCapabilities.create("light"), ILightStorage.class);
        public static final ItemCapability<ILightStorage, @Nullable Void> ITEM = ItemCapability.createVoid(LanthanistCapabilities.create("light"), ILightStorage.class);

        private LightStorage() {
        }
    }
}
