package lol.j0.quicksandsugarcane.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SugarCaneBlock.class)
public class SugarCaneBlockMixin {
    @Inject(at = @At("HEAD"), method = "canPlaceAt()Z ")
    private void canPlaceAt(BlockState state, WorldView world, BlockPos pos, CallbackInfo info) {
        System.out.println("This line is printed by an example mod mixin!");
    }
}