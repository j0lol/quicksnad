package lol.j0.quicksnad.mixin;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

import static lol.j0.quicksnad.Quicksnad.QUICKSNAD;
import static net.minecraft.block.SugarCaneBlock.AGE;

@Mixin(CactusBlock.class)
public class CactusBlockMixin {
    @Inject(at = @At("HEAD"), method = "Lnet/minecraft/block/CactusBlock;randomTick(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Ljava/util/Random;)V", cancellable = true)
    private void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (world.isAir(pos.up())) {
            int i = 1;
            while (world.getBlockState(pos.down(i)).isOf(Blocks.CACTUS)) {
                ++i;
            }
            int j = state.get(AGE);

            if ( world.getBlockState(pos.down(i)).isOf(QUICKSNAD) ) {
                if (j >= 14) {
                    world.setBlockState(pos.up(), Blocks.CACTUS.getDefaultState());
                    world.setBlockState(pos, state.with(AGE, 0), Block.NO_REDRAW);
                    state.with(AGE, 0).neighborUpdate(world, pos.up(), Blocks.CACTUS, pos, false);

                } else {
                    world.setBlockState(pos, state.with(AGE, j + 2), Block.NO_REDRAW);
                }
            } else if ( i < 3 ) {
                if (j >= 15) {
                    world.setBlockState(pos.up(), Blocks.CACTUS.getDefaultState());
                    world.setBlockState(pos, state.with(AGE, 0), Block.NO_REDRAW);
                    state.with(AGE, 0).neighborUpdate(world, pos.up(), Blocks.CACTUS, pos, false);
                } else {
                    world.setBlockState(pos, state.with(AGE, j + 1), Block.NO_REDRAW);
                }
            }
        }

        ci.cancel();
    }

    @Inject(at = @At("TAIL"), method = "canPlaceAt(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;)Z", cancellable = true)
    private void canPlaceAt(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockState blockState2 = world.getBlockState(pos.down());
        cir.setReturnValue(
            (blockState2.isOf(Blocks.CACTUS) || blockState2.isIn(BlockTags.SAND) || blockState2.isOf(QUICKSNAD)) && !world.getBlockState(pos.up()).getMaterial().isLiquid()
        );
    }

}