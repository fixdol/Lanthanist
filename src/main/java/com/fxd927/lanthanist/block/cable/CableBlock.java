package com.fxd927.lanthanist.block.cable;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public abstract class CableBlock extends Block implements EntityBlock {
    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty WEST = BooleanProperty.create("west");
    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty DOWN = BooleanProperty.create("down");

    public CableBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(NORTH, false)
                .setValue(EAST, false)
                .setValue(SOUTH, false)
                .setValue(WEST, false)
                .setValue(UP, false)
                .setValue(DOWN, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
    }

    @Override
    public BlockState getStateForPlacement(net.minecraft.world.item.context.BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        return this.defaultBlockState()
                .setValue(NORTH, canConnectTo(level, pos.north(), Direction.NORTH))
                .setValue(EAST, canConnectTo(level, pos.east(), Direction.EAST))
                .setValue(SOUTH, canConnectTo(level, pos.south(), Direction.SOUTH))
                .setValue(WEST, canConnectTo(level, pos.west(), Direction.WEST))
                .setValue(UP, canConnectTo(level, pos.above(), Direction.UP))
                .setValue(DOWN, canConnectTo(level, pos.below(), Direction.DOWN));
    }

    @Override
    public @NotNull BlockState updateShape(@NotNull BlockState state, @NotNull Direction direction, @NotNull BlockState neighborState, @NotNull LevelAccessor level, @NotNull BlockPos pos, @NotNull BlockPos neighborPos) {
        boolean canConnect = canConnectTo(level, neighborPos, direction);
        BooleanProperty property = getProperty(direction);
        return state.setValue(property, canConnect);
    }

    @Override
    protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return makeShape(state);
    }

    private static final VoxelShape[] CONNECTION_SHAPES = new VoxelShape[]{
            Shapes.box(0.3125, 0, 0.3125, 0.6875, 0.375, 0.6875),      // DOWN
            Shapes.box(0.3125, 0.625, 0.3125, 0.6875, 1.0, 0.6875),   // UP
            Shapes.box(0.3125, 0.3125, 0, 0.6875, 0.6875, 0.375),     // NORTH
            Shapes.box(0.3125, 0.3125, 0.625, 0.6875, 0.6875, 1.0),   // SOUTH
            Shapes.box(0, 0.3125, 0.3125, 0.375, 0.6875, 0.6875),     // WEST
            Shapes.box(0.625, 0.3125, 0.3125, 1.0, 0.6875, 0.6875)    // EAST
    };

    public VoxelShape makeShape(@NotNull BlockState state){
        VoxelShape shape = Shapes.empty();

        shape = Shapes.join(shape, Shapes.box(0.3125, 0.3125, 0.3125, 0.6875, 0.6875, 0.6875), BooleanOp.OR);

        for (Direction direction : Direction.values()) {
            BooleanProperty prop = getProperty(direction);
            if (state.getValue(prop)) {
                VoxelShape connectionShape = CONNECTION_SHAPES[direction.get3DDataValue()];
                shape = Shapes.join(shape, connectionShape, BooleanOp.OR);
            }
        }

        return shape;
    }

    public abstract boolean canConnectTo(LevelAccessor level, BlockPos pos, Direction direction);

    private static final Property<?>[] DIRS = {DOWN, UP, NORTH, SOUTH, WEST, EAST};
    private static BooleanProperty getProperty(Direction direction) {
        return (BooleanProperty) DIRS[direction.get3DDataValue()];
    }

    @Override
    public abstract @NotNull MapCodec<? extends DirectionalBlock> codec();

    @Override
    public abstract @Nullable BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState);

    @Override
    public abstract @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> blockEntityType);
}