package com.dobrynia.shrekparts;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(ShrekPartsMod.MOD_ID)
public final class ShrekPartsMod {
    public static final String MOD_ID = "shrekparts";

    // NeoForge 1.21+: the mod event bus is passed into the constructor
    public ShrekPartsMod(IEventBus modEventBus, ModContainer modContainer) {

        CreativeModTabs.register(modEventBus);

        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
    }
}
