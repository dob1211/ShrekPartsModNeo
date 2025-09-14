package com.dobrynia.shrekparts;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModBlocks {
    private ModBlocks() {}

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(Registries.BLOCK, ShrekPartsMod.MOD_ID);

    // Block id is also "shrek_head.json.json.json"
    public static final DeferredHolder<Block, ShrekHeadBlock> SHREK_HEAD = BLOCKS.register(
            "shrek_head",
            () -> new ShrekHeadBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_GREEN)
                    .strength(0.5F)
                    .noOcclusion()
                    .sound(SoundType.SLIME_BLOCK))
    );

    /** Small, horizontally-facing decorative head block. */
    public static class ShrekHeadBlock extends HorizontalDirectionalBlock {
        public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
        public static final MapCodec<ShrekHeadBlock> CODEC = simpleCodec(ShrekHeadBlock::new);
        @Override public MapCodec<ShrekHeadBlock> codec() { return CODEC; }

        // 12×12×12 head so it doesn’t fill the whole block
        private static final VoxelShape SHAPE = Block.box(2, 0, 2, 14, 12, 14);

        public ShrekHeadBlock(BlockBehaviour.Properties props) {
            super(props);
            registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
        }

        @Override
        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
            b.add(FACING);
        }

        // Face the player on placement (front faces the player)
        @Override
        public BlockState getStateForPlacement(BlockPlaceContext ctx) {
            return defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
        }

        @Override
        public VoxelShape getShape(BlockState state, net.minecraft.world.level.BlockGetter level,
                                   net.minecraft.core.BlockPos pos, CollisionContext ctx) {
            return SHAPE;
        }

        @Override
        public BlockState rotate(BlockState s, net.minecraft.world.level.block.Rotation r) {
            return s.setValue(FACING, r.rotate(s.getValue(FACING)));
        }

        @Override
        public BlockState mirror(BlockState s, net.minecraft.world.level.block.Mirror m) {
            return s.rotate(m.getRotation(s.getValue(FACING)));
        }
    }
}
