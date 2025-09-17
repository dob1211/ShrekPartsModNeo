package com.dobrynia.shrekparts;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MOD_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ShrekPartsMod.MOD_ID);

    public static final Supplier<CreativeModeTab> SHREK_PARTS_TAB = CREATIVE_MOD_TAB.register("shrek_parts_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SHREK_HEAD.get()))
                    .title(Component.translatable("creativetab.shrekparts.shrek_parts"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.SHREK_STATUE.get());
                        output.accept(ModItems.SHREK_HEAD.get());
                        output.accept(ModItems.SHREK_BODY.get());
                        output.accept(ModItems.SHREK_LEGS.get());
                        output.accept(ModItems.SHREK_LEFT_ARM.get());
                        output.accept(ModItems.SHREK_RIGHT_ARM.get());
                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MOD_TAB.register(eventBus);
    }
}
