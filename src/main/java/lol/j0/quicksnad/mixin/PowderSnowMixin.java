package lol.j0.quicksnad.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.PowderSnowBucketItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

// Credit: offbeatwitch for mixin witchery
@Mixin(PowderSnowBucketItem.class)
public abstract class PowderSnowMixin {
    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/BlockItem;<init>(Lnet/minecraft/block/Block;Lnet/minecraft/item/Item$Settings;)V"), index = 1)
    private static Item.Settings init(Item.Settings settings) {
        return settings.recipeRemainder(Items.BUCKET);
    }
}
