package com.fxd927.lanthanist.block;

import com.fxd927.lanthanist.block.blockentity.BasicMachineCasingBlockEntity;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class BasicMachineCasingBlock extends BaseEntityBlock {
    public static final MapCodec<BasicMachineCasingBlock> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    BlockBehaviour.Properties.CODEC.fieldOf("properties").forGetter(block -> block.properties)
            ).apply(instance, BasicMachineCasingBlock::new)
    );

    public BasicMachineCasingBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player,
                                              InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) return ItemInteractionResult.SUCCESS;

        Direction dir = hit.getDirection();
        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof BasicMachineCasingBlockEntity slotBE)) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        if (!stack.isEmpty()) {
            boolean success = slotBE.insertItem(dir, stack, pos);
            return success
                    ? ItemInteractionResult.sidedSuccess(true)
                    : ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION; // ← ALLOWED_ITEMS 以外は PASS
        } else {
            boolean success = slotBE.extractItem(dir, player, pos);
            return ItemInteractionResult.sidedSuccess(success);
        }
    }


    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!(entity instanceof Player player)) return;
        if (level.isClientSide) return;

        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof BasicMachineCasingBlockEntity slotBE)) return;

        if (slotBE.isSmeltingEnabled()) {
            if (player.getHealth() < player.getMaxHealth()) {
                player.heal(1.0F); // ハート0.5個回復
            }
        }

        super.stepOn(level, pos, state, entity);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!level.isClientSide && state.getBlock() != newState.getBlock()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof BasicMachineCasingBlockEntity slotBE) {
                for (Direction dir : Direction.values()) {
                    ItemStack stack = slotBE.peekItem(dir);
                    if (!stack.isEmpty()) {
                        popResource(level, pos, stack);
                    }
                }
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BasicMachineCasingBlockEntity(pos, state);
    }
}
