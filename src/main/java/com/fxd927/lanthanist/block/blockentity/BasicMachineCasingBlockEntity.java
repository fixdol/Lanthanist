package com.fxd927.lanthanist.block.blockentity;

import com.fxd927.lanthanist.registries.LanthanistBlockEntityTypes;
import com.fxd927.lanthanist.registries.LanthanistItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public class BasicMachineCasingBlockEntity extends BlockEntity {
    private final Map<Direction, ItemStack> slotMap = new EnumMap<>(Direction.class);

    private static final Set<Item> ALLOWED_ITEMS = Set.of(
            LanthanistItems.DYSPROSIUM_INGOT.get(),
            LanthanistItems.CERIUM_INGOT.get(),
            LanthanistItems.LANTHANUM_INGOT.get()
    );

    private boolean smeltingEnabled = false;

    public boolean isSmeltingEnabled() {
        return smeltingEnabled;
    }

    public BasicMachineCasingBlockEntity(BlockPos pos, BlockState state) {
        super(LanthanistBlockEntityTypes.BASIC_MACHINE_CASING.get(), pos, state);
        for (Direction dir : Direction.values()) {
            slotMap.put(dir, ItemStack.EMPTY);
        }
    }

    private void updateFunctionFlags() {
        smeltingEnabled = slotMap.values().stream().anyMatch(stack ->
                !stack.isEmpty() && stack.getItem() == LanthanistItems.CERIUM_INGOT.get()
        );
    }

    public boolean insertItem(Direction dir, ItemStack stack, BlockPos pos) {
        if (stack.isEmpty()) return false;
        if (!slotMap.get(dir).isEmpty()) return false;

        // ★ ホワイトリストチェック
        if (!ALLOWED_ITEMS.contains(stack.getItem())) return false;

        ItemStack oneItem = stack.copy();
        oneItem.setCount(1);
        slotMap.put(dir, oneItem);
        stack.shrink(1);
        setChanged();
        level.playSound(null, pos, SoundEvents.LANTERN_PLACE, SoundSource.BLOCKS, 0.8f, 1.0f);
        return true;
    }


    public boolean extractItem(Direction dir, Player player, BlockPos pos) {
        ItemStack stored = slotMap.get(dir);
        if (stored.isEmpty()) return false;

        if (!player.getInventory().add(stored.copy())) return false;
        slotMap.put(dir, ItemStack.EMPTY);
        setChanged();
        level.playSound(null, pos, SoundEvents.LANTERN_BREAK, SoundSource.BLOCKS, 0.8f, 1.0f);
        return true;
    }


    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);

        // スロットを NBT に保存
        ListTag listTag = new ListTag();
        for (Map.Entry<Direction, ItemStack> entry : slotMap.entrySet()) {
            if (!entry.getValue().isEmpty()) {
                CompoundTag slotTag = new CompoundTag();
                slotTag.putInt("Direction", entry.getKey().ordinal());
                slotTag.put("Item", entry.getValue().save(provider));
                listTag.add(slotTag);
            }
        }
        tag.put("Slots", listTag);

        tag.putBoolean("SmeltingEnabled", smeltingEnabled);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);

        slotMap.clear();
        for (Direction dir : Direction.values()) {
            slotMap.put(dir, ItemStack.EMPTY);
        }

        if (tag.contains("Slots", Tag.TAG_LIST)) {
            ListTag listTag = tag.getList("Slots", Tag.TAG_COMPOUND);
            for (int i = 0; i < listTag.size(); i++) {
                CompoundTag slotTag = listTag.getCompound(i);
                if (slotTag.contains("Direction", Tag.TAG_INT) && slotTag.contains("Item", Tag.TAG_COMPOUND)) {
                    Direction dir = Direction.values()[slotTag.getInt("Direction")];
                    ItemStack stack = ItemStack.parse(provider, slotTag.getCompound("Item")).orElse(ItemStack.EMPTY);
                    slotMap.put(dir, stack);
                }
            }
        }

        smeltingEnabled = tag.getBoolean("SmeltingEnabled");
    }


    public ItemStack peekItem(Direction dir) {
        return slotMap.get(dir);
    }

    public Map<Direction, ItemStack> getSlotMap() {
        return slotMap;
    }

    public ItemStack getItemForFace(Direction dir) {
        return slotMap.getOrDefault(dir, ItemStack.EMPTY);
    }
}
