package com.vibecoding.thaumvibe.core.init;

import com.vibecoding.thaumvibe.ThaumVibe;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = 
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ThaumVibe.MODID);
    
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> THAUMVIBE_TAB = 
        CREATIVE_MODE_TABS.register("thaumvibe_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.thaumvibe"))
            .icon(() -> new ItemStack(ModItems.THAUMONOMICON.get()))
            .displayItems((parameters, output) -> {
                // Add all mod items to the creative tab
                output.accept(ModItems.THAUMONOMICON.get());
                output.accept(ModItems.THAUMOMETER.get());
                output.accept(ModItems.WAND.get());
                output.accept(ModItems.SALIS_MUNDUS.get());
                
                // Shards
                output.accept(ModItems.AIR_SHARD.get());
                output.accept(ModItems.FIRE_SHARD.get());
                output.accept(ModItems.WATER_SHARD.get());
                output.accept(ModItems.EARTH_SHARD.get());
                output.accept(ModItems.ORDER_SHARD.get());
                output.accept(ModItems.ENTROPY_SHARD.get());
                
                // Blocks
                output.accept(ModBlocks.CRUCIBLE.get());
                output.accept(ModBlocks.RESEARCH_TABLE.get());
                output.accept(ModBlocks.ARCANE_WORKBENCH.get());
                output.accept(ModBlocks.INFUSION_ALTAR.get());
                output.accept(ModBlocks.CINNABAR_ORE.get());
                output.accept(ModBlocks.AMBER_ORE.get());
            })
            .build());
    
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
