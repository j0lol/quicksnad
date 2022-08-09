package lol.j0.quicksandsugarcane.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static lol.j0.quicksandsugarcane.QuicksandSugarcane.QUICKSNAD;

@Mixin(SugarCaneBlock.class)
public class SugarCaneBlockMixin {
    @Inject(at = @At("HEAD"), method = "canPlaceAt(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;)Z", cancellable = true)
    private void canPlaceAt(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (world.getBlockState(pos.down()).isOf(QUICKSNAD)) {
            BlockPos blockPos = pos.down();
            for (Direction direction : Direction.Type.HORIZONTAL) {
                BlockState blockState2 = world.getBlockState(blockPos.offset(direction));
                FluidState fluidState = world.getFluidState(blockPos.offset(direction));
                if (!fluidState.isIn(FluidTags.WATER) && !blockState2.isOf(Blocks.FROSTED_ICE)) continue;
                cir.setReturnValue(true);
            }
        }
    }

}