package lol.j0.quicksnad;

import lol.j0.quicksnad.block.QuicksnadBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Quicksnad implements ModInitializer {

    public static final String MOD_ID = "quicksnad";
    public static final Block QUICKSNAD = new QuicksnadBlock(FabricBlockSettings.copyOf(Blocks.SAND), 13416596);

    @Override
    public void onInitialize() {

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "quicksnad"), QUICKSNAD);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "quicksnad"), new BlockItem(QUICKSNAD, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
    }
}
