package lol.j0.quicksnad.block;

import com.shnupbups.quicksand.Quicksand;
import com.shnupbups.quicksand.block.QuicksandBlock;
import com.shnupbups.quicksand.registry.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class QuicksnadBlock extends QuicksandBlock {

    public QuicksnadBlock(Settings settings, int color) {
        super(settings, color, null);
    }

    @Override
    public ItemStack tryDrainFluid(WorldAccess world, BlockPos pos, BlockState state) {
        return null;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity.getBlockStateAtPos().isIn(ModTags.QUICKSAND)) {
            entity.slowMovement(state, new Vec3d(0.6, 0.4, 0.6));
        }

        if (!entity.isSpectator() && (this.hasEntityMoved(entity) || (double)world.getRandom().nextFloat() < 0.2)) {
            if (!entity.getType().isIn(ModTags.SURVIVES_IN_QUICKSAND) && entity instanceof LivingEntity living) {
                if (world.getBlockState(new BlockPos(entity.getBlockX(), entity.getEyeY() - 0.11, entity.getBlockZ())).isIn(ModTags.QUICKSAND)) {
                    living.damage(Quicksand.QUICKSAND_DAMAGE, 1.0F);
                }
            }

            if (world.getRandom().nextBoolean()) {
                spawnParticles(world, state, new Vec3d(entity.getX(), pos.getY(), entity.getZ()));
            }
        }

    }
}
