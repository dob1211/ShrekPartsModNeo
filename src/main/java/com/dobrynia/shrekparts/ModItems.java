package com.dobrynia.shrekparts;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems {
    private ModItems() {}

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, ShrekPartsMod.MOD_ID);

    // SINGLE id: "shrek_head.json.json.json" — this places the block
    public static final DeferredHolder<Item, BlockItem>SHREK_HEAD =
            ITEMS.register("shrek_head",
                    () -> new BlockItem(ModBlocks.SHREK_HEAD.get(), new Item.Properties()));

    public static void onBuildCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(SHREK_HEAD.get());
        }
    }
}
