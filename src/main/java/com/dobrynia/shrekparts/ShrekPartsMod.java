package com.dobrynia.shrekparts;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(ShrekPartsMod.MOD_ID)
public final class ShrekPartsMod {
    public static final String MOD_ID = "shrekparts";

    public ShrekPartsMod(IEventBus modBus) {
        ModBlocks.BLOCKS.register(modBus);
        ModItems.ITEMS.register(modBus);

        // Make sure the item shows up in creative tabs
        modBus.addListener(ModItems::onBuildCreativeTab);
    }
}
