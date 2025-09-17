package com.dobrynia.shrekparts;

import com.mojang.serialization.MapCodec;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModBlocks {
    private ModBlocks() {}

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(Registries.BLOCK, ShrekPartsMod.MOD_ID);

    public static final DeferredHolder<Block, ShrekHeadBlock> SHREK_HEAD = BLOCKS.register(
            "shrek_head",
            () -> new ShrekHeadBlock(defaultProps())
    );

    public static final DeferredHolder<Block, ShrekLeftArmBlock> SHREK_LEFT_ARM = BLOCKS.register(
            "shrek_left_arm",
            () -> new ShrekLeftArmBlock(defaultProps())
    );

    public static final DeferredHolder<Block, ShrekRightArmBlock> SHREK_RIGHT_ARM = BLOCKS.register(
            "shrek_right_arm",
            () -> new ShrekRightArmBlock(defaultProps())
    );

    public static final DeferredHolder<Block, ShrekBodyBlock> SHREK_BODY = BLOCKS.register(
            "shrek_body",
            () -> new ShrekBodyBlock(defaultProps())
    );

    public static final DeferredHolder<Block, ShrekLegsBlock> SHREK_LEGS = BLOCKS.register(
            "shrek_legs",
            () -> new ShrekLegsBlock(defaultProps())
    );

    // One-block statue registered as "shrek_statue"
    public static final DeferredHolder<Block, ShrekStatue> SHREK_STATUE = BLOCKS.register(
            "shrek_statue",
            () -> new ShrekStatue(defaultProps())
    );

    private static BlockBehaviour.Properties defaultProps() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_GREEN)
                .strength(0.5F)
                .noOcclusion()
                .sound(SoundType.SLIME_BLOCK);
    }

    /** Base pattern for 1-block tall oriented pieces. */
    private static abstract class SimpleFacingBlock extends HorizontalDirectionalBlock {
        public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

        protected SimpleFacingBlock(BlockBehaviour.Properties props) {
            super(props);
            registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
        }

        @Override
        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
            b.add(FACING);
        }

        @Nullable
        @Override
        public BlockState getStateForPlacement(BlockPlaceContext ctx) {
            // If your placed piece looks “backwards”, remove getOpposite()
            return defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
        }

        @Override
        public BlockState rotate(BlockState s, Rotation r) {
            return s.setValue(FACING, r.rotate(s.getValue(FACING)));
        }

        @Override
        public BlockState mirror(BlockState s, Mirror m) {
            return s.rotate(m.getRotation(s.getValue(FACING)));
        }
    }

    // ==== Head with ears (12x12x12 head + side ears) ====
    public static class ShrekHeadBlock extends SimpleFacingBlock {
        public static final MapCodec<ShrekHeadBlock> CODEC = simpleCodec(ShrekHeadBlock::new);
        @Override public MapCodec<ShrekHeadBlock> codec() { return CODEC; }

        private static final VoxelShape HEAD  = Block.box(3, 3, 3, 13, 13, 13);
        private static final VoxelShape EAR_L = Block.box(6, 9, 1,  10, 11, 3);
        private static final VoxelShape EAR_R = Block.box(6, 9, 13, 10, 11, 15);
        private static final VoxelShape SHAPE = Shapes.or(HEAD, EAR_L, EAR_R);

        public ShrekHeadBlock(BlockBehaviour.Properties props) { super(props); }

        @Override
        public VoxelShape getShape(BlockState s, BlockGetter level, BlockPos pos, CollisionContext ctx) {
            return SHAPE;
        }
    }

    // ==== Left Arm (thin vertical, 14 tall) ====
    public static class ShrekLeftArmBlock extends SimpleFacingBlock {
        public static final MapCodec<ShrekLeftArmBlock> CODEC = simpleCodec(ShrekLeftArmBlock::new);
        @Override public MapCodec<ShrekLeftArmBlock> codec() { return CODEC; }

        private static final VoxelShape SHAPE = Block.box(6, 0, 6, 10, 14, 10);

        public ShrekLeftArmBlock(BlockBehaviour.Properties props) { super(props); }

        @Override
        public VoxelShape getShape(BlockState s, BlockGetter level, BlockPos pos, CollisionContext ctx) {
            return SHAPE;
        }
    }

    // ==== Right Arm ====
    public static class ShrekRightArmBlock extends SimpleFacingBlock {
        public static final MapCodec<ShrekRightArmBlock> CODEC = simpleCodec(ShrekRightArmBlock::new);
        @Override public MapCodec<ShrekRightArmBlock> codec() { return CODEC; }

        private static final VoxelShape SHAPE = Block.box(6, 0, 6, 10, 14, 10);

        public ShrekRightArmBlock(BlockBehaviour.Properties props) { super(props); }

        @Override
        public VoxelShape getShape(BlockState s, BlockGetter level, BlockPos pos, CollisionContext ctx) {
            return SHAPE;
        }
    }

    // ==== Body (full height, slimmer width) ====
    public static class ShrekBodyBlock extends SimpleFacingBlock {
        public static final MapCodec<ShrekBodyBlock> CODEC = simpleCodec(ShrekBodyBlock::new);
        @Override public MapCodec<ShrekBodyBlock> codec() { return CODEC; }

        private static final VoxelShape SHAPE = Block.box(3, 0, 3, 13, 16, 13);

        public ShrekBodyBlock(BlockBehaviour.Properties props) { super(props); }

        @Override
        public VoxelShape getShape(BlockState s, BlockGetter level, BlockPos pos, CollisionContext ctx) {
            return SHAPE;
        }
    }

    // ==== Legs (two columns, ~12 high) ====
    public static class ShrekLegsBlock extends SimpleFacingBlock {
        public static final MapCodec<ShrekLegsBlock> CODEC = simpleCodec(ShrekLegsBlock::new);
        @Override public MapCodec<ShrekLegsBlock> codec() { return CODEC; }

        private static final VoxelShape LEFT_LEG  = Block.box(6, 0, 4, 10, 12, 7);
        private static final VoxelShape RIGHT_LEG = Block.box(6, 0, 9, 10, 12, 12);
        private static final VoxelShape SHAPE = Shapes.or(LEFT_LEG, RIGHT_LEG);

        public ShrekLegsBlock(BlockBehaviour.Properties props) { super(props); }

        @Override
        public VoxelShape getShape(BlockState s, BlockGetter level, BlockPos pos, CollisionContext ctx) {
            return SHAPE;
        }
    }

    // ==== One-block statue (legs + body + arms + head) ====
    public static class ShrekStatue extends SimpleFacingBlock {
        public static final MapCodec<ShrekStatue> CODEC = simpleCodec(ShrekStatue::new);
        @Override public MapCodec<ShrekStatue> codec() { return CODEC; }

        // Outline roughly matches the one-block model
        private static final VoxelShape SHAPE = Shapes.or(
                // legs
                Block.box(5, 0, 6, 7, 6, 10),
                Block.box(9, 0, 6, 11, 6, 10),
                // body
                Block.box(4, 6, 4, 12, 12, 12),
                // arms
                Block.box(2, 6, 6, 4, 12, 10),
                Block.box(12, 6, 6, 14, 12, 10),
                // head
                Block.box(4, 12, 4, 12, 16, 12)
        );

        public ShrekStatue(BlockBehaviour.Properties props) {
            super(props); // SimpleFacingBlock handles FACING + default state
        }

        @Override
        public VoxelShape getShape(BlockState s, BlockGetter level, BlockPos pos, CollisionContext ctx) {
            return SHAPE;
        }
    }
}
