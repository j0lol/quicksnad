package lol.j0.quicksandsugarcane;

import lol.j0.quicksandsugarcane.block.Quicksand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import javax.swing.*;

public class QuicksandSugarcane implements ModInitializer {

    public static final String MOD_ID = "quicksnad";
    public static final Block QUICKSNAD = new Quicksand(14406560, AbstractBlock.Settings.of(Material.AGGREGATE, MapColor.PALE_YELLOW).strength(0.5f).sounds(BlockSoundGroup.SAND));

    @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "quicksnad"), QUICKSNAD);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "quicksnad"), new BlockItem(QUICKSNAD, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
    }
}
