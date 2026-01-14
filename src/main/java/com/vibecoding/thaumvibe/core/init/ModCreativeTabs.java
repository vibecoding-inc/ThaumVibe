package com.vibecoding.thaumvibe.core.init;

import com.vibecoding.thaumvibe.ThaumVibe;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = 
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ThaumVibe.MODID);
    
    public static final RegistryObject<CreativeModeTab> THAUMVIBE_TAB = 
        CREATIVE_MODE_TABS.register("thaumvibe_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.thaumvibe"))
            .icon(() -> new ItemStack(ModItems.THAUMONOMICON.get()))
            .displayItems((parameters, output) -> {
                // EARLY GAME - Basic items
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
                
                // MIDDLE GAME - Advanced materials
                output.accept(ModItems.VIS_CRYSTAL.get());
                output.accept(ModItems.ALCHEMICAL_BRASS.get());
                output.accept(ModItems.THAUMIUM_INGOT.get());
                output.accept(ModItems.NITOR.get());
                output.accept(ModItems.QUICKSILVER.get());
                
                // LATE GAME - Eldritch items
                output.accept(ModItems.VOID_METAL_INGOT.get());
                output.accept(ModItems.PRIMORDIAL_PEARL.get());
                output.accept(ModItems.ELDRITCH_EYE.get());
                output.accept(ModItems.SANITY_CHECKER.get());
                
                // EARLY GAME - Blocks
                output.accept(ModBlocks.CRUCIBLE.get());
                output.accept(ModBlocks.RESEARCH_TABLE.get());
                output.accept(ModBlocks.ARCANE_WORKBENCH.get());
                output.accept(ModBlocks.CINNABAR_ORE.get());
                output.accept(ModBlocks.AMBER_ORE.get());
                
                // MIDDLE GAME - Blocks
                output.accept(ModBlocks.INFUSION_ALTAR.get());
                output.accept(ModBlocks.ALCHEMICAL_FURNACE.get());
                output.accept(ModBlocks.ESSENTIA_SMELTERY.get());
                output.accept(ModBlocks.SHIMMERLEAF.get());
                output.accept(ModBlocks.GREATWOOD_LOG.get());
                output.accept(ModBlocks.SILVERWOOD_LOG.get());
                
                // LATE GAME - Blocks
                output.accept(ModBlocks.ELDRITCH_OBELISK.get());
                output.accept(ModBlocks.FLUX_SCRUBBER.get());
            })
            .build());
    
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
