package com.fxd927.lanthanist.datagen;

import com.fxd927.lanthanist.Lanthanist;
import com.fxd927.lanthanist.registries.LanthanistItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class LanthanistItemModelProvider extends ItemModelProvider {
    public LanthanistItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Lanthanist.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(LanthanistItems.DYSPROSIUM_INGOT.get());
        basicItem(LanthanistItems.DYSPROSIUM_NUGGET.get());
        basicItem(LanthanistItems.RAW_RARE_EARTH.get());
    }
}
