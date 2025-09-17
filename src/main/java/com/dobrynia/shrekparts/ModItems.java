package com.dobrynia.shrekparts;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.bus.api.IEventBus;

public final class ModItems {
    private ModItems() {}

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, ShrekPartsMod.MOD_ID);

    public static final DeferredHolder<Item, BlockItem> SHREK_HEAD =
            ITEMS.register("shrek_head", () -> new BlockItem(ModBlocks.SHREK_HEAD.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> SHREK_LEFT_ARM =
            ITEMS.register("shrek_left_arm", () -> new BlockItem(ModBlocks.SHREK_LEFT_ARM.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> SHREK_RIGHT_ARM =
            ITEMS.register("shrek_right_arm", () -> new BlockItem(ModBlocks.SHREK_RIGHT_ARM.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> SHREK_BODY =
            ITEMS.register("shrek_body", () -> new BlockItem(ModBlocks.SHREK_BODY.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> SHREK_LEGS =
            ITEMS.register("shrek_legs", () -> new BlockItem(ModBlocks.SHREK_LEGS.get(), new Item.Properties()));

    // The statue block id is "shrek", so the item id should also be "shrek"
    public static final DeferredHolder<Item, BlockItem> SHREK_STATUE =
            ITEMS.register("shrek_statue", () -> new BlockItem(ModBlocks.SHREK_STATUE.get(), new Item.Properties()));

    public static void register(IEventBus modBus) {
        ITEMS.register(modBus);
    }

}
