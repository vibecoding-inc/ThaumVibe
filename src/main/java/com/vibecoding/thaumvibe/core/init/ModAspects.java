package com.vibecoding.thaumvibe.core.init;

import com.vibecoding.thaumvibe.api.aspects.Aspect;
import com.vibecoding.thaumvibe.api.aspects.AspectList;
import com.vibecoding.thaumvibe.api.aspects.AspectRegistry;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

/**
 * Initializes aspect mappings for vanilla items and blocks.
 */
public class ModAspects {
    
    public static void registerAspects() {
        registerVanillaItems();
        registerVanillaBlocks();
        registerModItems();
        registerModBlocks();
    }
    
    private static void registerVanillaItems() {
        // Basic materials
        AspectRegistry.registerItemAspects(Items.DIRT, 
            new AspectList().add(Aspect.TERRA, 1));
        
        AspectRegistry.registerItemAspects(Items.STONE, 
            new AspectList().add(Aspect.TERRA, 2));
        
        AspectRegistry.registerItemAspects(Items.IRON_INGOT, 
            new AspectList().add(Aspect.METALLUM, 3).add(Aspect.TERRA, 1));
        
        AspectRegistry.registerItemAspects(Items.GOLD_INGOT, 
            new AspectList().add(Aspect.METALLUM, 4).add(Aspect.POTENTIA, 2));
        
        AspectRegistry.registerItemAspects(Items.DIAMOND, 
            new AspectList().add(Aspect.VITREUS, 5).add(Aspect.POTENTIA, 3));
        
        AspectRegistry.registerItemAspects(Items.EMERALD, 
            new AspectList().add(Aspect.VITREUS, 4).add(Aspect.POTENTIA, 2));
        
        // Wood and plants
        AspectRegistry.registerItemAspects(Items.OAK_LOG, 
            new AspectList().add(Aspect.TERRA, 2).add(Aspect.VICTUS, 1));
        
        AspectRegistry.registerItemAspects(Items.WHEAT, 
            new AspectList().add(Aspect.VICTUS, 1).add(Aspect.TERRA, 1));
        
        // Fire and water
        AspectRegistry.registerItemAspects(Items.LAVA_BUCKET, 
            new AspectList().add(Aspect.IGNIS, 5).add(Aspect.PERDITIO, 2));
        
        AspectRegistry.registerItemAspects(Items.WATER_BUCKET, 
            new AspectList().add(Aspect.AQUA, 5));
        
        AspectRegistry.registerItemAspects(Items.ICE, 
            new AspectList().add(Aspect.GELUM, 3).add(Aspect.AQUA, 2));
        
        AspectRegistry.registerItemAspects(Items.SNOW_BLOCK, 
            new AspectList().add(Aspect.GELUM, 2).add(Aspect.AQUA, 1));
        
        // Glass
        AspectRegistry.registerItemAspects(Items.GLASS, 
            new AspectList().add(Aspect.VITREUS, 2));
        
        // Redstone and magic-like items
        AspectRegistry.registerItemAspects(Items.REDSTONE, 
            new AspectList().add(Aspect.POTENTIA, 3).add(Aspect.MOTUS, 2));
        
        AspectRegistry.registerItemAspects(Items.GLOWSTONE_DUST, 
            new AspectList().add(Aspect.LUX, 3).add(Aspect.POTENTIA, 1));
        
        AspectRegistry.registerItemAspects(Items.ENDER_PEARL, 
            new AspectList().add(Aspect.MOTUS, 4).add(Aspect.VACUOS, 3));
        
        AspectRegistry.registerItemAspects(Items.BLAZE_ROD, 
            new AspectList().add(Aspect.IGNIS, 4).add(Aspect.POTENTIA, 2));
        
        // Mob drops
        AspectRegistry.registerItemAspects(Items.BONE, 
            new AspectList().add(Aspect.MORTUUS, 2).add(Aspect.TERRA, 1));
        
        AspectRegistry.registerItemAspects(Items.ROTTEN_FLESH, 
            new AspectList().add(Aspect.MORTUUS, 3).add(Aspect.PERDITIO, 1));
        
        AspectRegistry.registerItemAspects(Items.SPIDER_EYE, 
            new AspectList().add(Aspect.MORTUUS, 1).add(Aspect.PERDITIO, 2));
        
        // Food
        AspectRegistry.registerItemAspects(Items.APPLE, 
            new AspectList().add(Aspect.VICTUS, 2));
        
        AspectRegistry.registerItemAspects(Items.COOKED_BEEF, 
            new AspectList().add(Aspect.VICTUS, 3).add(Aspect.IGNIS, 1));
        
        // Air-related
        AspectRegistry.registerItemAspects(Items.FEATHER, 
            new AspectList().add(Aspect.AER, 2).add(Aspect.MOTUS, 1));
    }
    
    private static void registerVanillaBlocks() {
        // Basic blocks
        AspectRegistry.registerBlockAspects(Blocks.DIRT, 
            new AspectList().add(Aspect.TERRA, 1));
        
        AspectRegistry.registerBlockAspects(Blocks.STONE, 
            new AspectList().add(Aspect.TERRA, 2));
        
        AspectRegistry.registerBlockAspects(Blocks.IRON_ORE, 
            new AspectList().add(Aspect.METALLUM, 3).add(Aspect.TERRA, 2));
        
        AspectRegistry.registerBlockAspects(Blocks.GOLD_ORE, 
            new AspectList().add(Aspect.METALLUM, 4).add(Aspect.POTENTIA, 2).add(Aspect.TERRA, 2));
        
        AspectRegistry.registerBlockAspects(Blocks.DIAMOND_ORE, 
            new AspectList().add(Aspect.VITREUS, 5).add(Aspect.POTENTIA, 3).add(Aspect.TERRA, 2));
        
        AspectRegistry.registerBlockAspects(Blocks.COAL_ORE, 
            new AspectList().add(Aspect.IGNIS, 2).add(Aspect.POTENTIA, 1).add(Aspect.TERRA, 2));
        
        // Wood
        AspectRegistry.registerBlockAspects(Blocks.OAK_LOG, 
            new AspectList().add(Aspect.TERRA, 2).add(Aspect.VICTUS, 1));
        
        // Water and ice
        AspectRegistry.registerBlockAspects(Blocks.WATER, 
            new AspectList().add(Aspect.AQUA, 3));
        
        AspectRegistry.registerBlockAspects(Blocks.ICE, 
            new AspectList().add(Aspect.GELUM, 3).add(Aspect.AQUA, 2));
        
        AspectRegistry.registerBlockAspects(Blocks.SNOW_BLOCK, 
            new AspectList().add(Aspect.GELUM, 2).add(Aspect.AQUA, 1));
        
        // Fire
        AspectRegistry.registerBlockAspects(Blocks.LAVA, 
            new AspectList().add(Aspect.IGNIS, 5).add(Aspect.PERDITIO, 2));
        
        AspectRegistry.registerBlockAspects(Blocks.FIRE, 
            new AspectList().add(Aspect.IGNIS, 3).add(Aspect.PERDITIO, 1));
        
        // Glass
        AspectRegistry.registerBlockAspects(Blocks.GLASS, 
            new AspectList().add(Aspect.VITREUS, 2));
        
        // Plants
        AspectRegistry.registerBlockAspects(Blocks.GRASS_BLOCK, 
            new AspectList().add(Aspect.TERRA, 1).add(Aspect.VICTUS, 1));
        
        AspectRegistry.registerBlockAspects(Blocks.OAK_LEAVES, 
            new AspectList().add(Aspect.VICTUS, 1).add(Aspect.AER, 1));
    }
    
    private static void registerModItems() {
        // Crystal Shards - primal aspects
        AspectRegistry.registerItemAspects(ModItems.AIR_SHARD.get(), 
            new AspectList().add(Aspect.AER, 5));
        
        AspectRegistry.registerItemAspects(ModItems.FIRE_SHARD.get(), 
            new AspectList().add(Aspect.IGNIS, 5));
        
        AspectRegistry.registerItemAspects(ModItems.WATER_SHARD.get(), 
            new AspectList().add(Aspect.AQUA, 5));
        
        AspectRegistry.registerItemAspects(ModItems.EARTH_SHARD.get(), 
            new AspectList().add(Aspect.TERRA, 5));
        
        AspectRegistry.registerItemAspects(ModItems.ORDER_SHARD.get(), 
            new AspectList().add(Aspect.ORDO, 5));
        
        AspectRegistry.registerItemAspects(ModItems.ENTROPY_SHARD.get(), 
            new AspectList().add(Aspect.PERDITIO, 5));
        
        // Advanced items
        AspectRegistry.registerItemAspects(ModItems.VIS_CRYSTAL.get(), 
            new AspectList().add(Aspect.POTENTIA, 5).add(Aspect.PRAECANTATIO, 3));
        
        AspectRegistry.registerItemAspects(ModItems.ALCHEMICAL_BRASS.get(), 
            new AspectList().add(Aspect.METALLUM, 3).add(Aspect.PRAECANTATIO, 2));
        
        AspectRegistry.registerItemAspects(ModItems.THAUMIUM_INGOT.get(), 
            new AspectList().add(Aspect.METALLUM, 4).add(Aspect.PRAECANTATIO, 3));
        
        AspectRegistry.registerItemAspects(ModItems.NITOR.get(), 
            new AspectList().add(Aspect.LUX, 5).add(Aspect.IGNIS, 3).add(Aspect.POTENTIA, 2));
        
        AspectRegistry.registerItemAspects(ModItems.QUICKSILVER.get(), 
            new AspectList().add(Aspect.METALLUM, 2).add(Aspect.AQUA, 2).add(Aspect.PRAECANTATIO, 1));
        
        AspectRegistry.registerItemAspects(ModItems.VOID_METAL_INGOT.get(), 
            new AspectList().add(Aspect.METALLUM, 5).add(Aspect.VACUOS, 4).add(Aspect.PRAECANTATIO, 3));
        
        AspectRegistry.registerItemAspects(ModItems.PRIMORDIAL_PEARL.get(), 
            new AspectList()
                .add(Aspect.PRAECANTATIO, 10)
                .add(Aspect.POTENTIA, 8)
                .add(Aspect.AER, 2)
                .add(Aspect.TERRA, 2)
                .add(Aspect.IGNIS, 2)
                .add(Aspect.AQUA, 2)
                .add(Aspect.ORDO, 2)
                .add(Aspect.PERDITIO, 2));
        
        AspectRegistry.registerItemAspects(ModItems.ELDRITCH_EYE.get(), 
            new AspectList().add(Aspect.VACUOS, 5).add(Aspect.PRAECANTATIO, 4).add(Aspect.MORTUUS, 2));
        
        AspectRegistry.registerItemAspects(ModItems.SANITY_CHECKER.get(), 
            new AspectList().add(Aspect.ORDO, 3).add(Aspect.VICTUS, 2).add(Aspect.PRAECANTATIO, 2));
        
        AspectRegistry.registerItemAspects(ModItems.SALIS_MUNDUS.get(), 
            new AspectList().add(Aspect.PRAECANTATIO, 3).add(Aspect.ORDO, 2));
        
        // Basic tools
        AspectRegistry.registerItemAspects(ModItems.WAND.get(), 
            new AspectList().add(Aspect.PRAECANTATIO, 5).add(Aspect.POTENTIA, 3).add(Aspect.ORDO, 2));
        
        AspectRegistry.registerItemAspects(ModItems.THAUMOMETER.get(), 
            new AspectList().add(Aspect.PRAECANTATIO, 2).add(Aspect.VITREUS, 2).add(Aspect.AER, 1));
        
        AspectRegistry.registerItemAspects(ModItems.THAUMONOMICON.get(), 
            new AspectList().add(Aspect.PRAECANTATIO, 4).add(Aspect.VICTUS, 1));
    }
    
    private static void registerModBlocks() {
        // Magical blocks
        AspectRegistry.registerBlockAspects(ModBlocks.CRUCIBLE.get(), 
            new AspectList().add(Aspect.AQUA, 3).add(Aspect.PRAECANTATIO, 2).add(Aspect.METALLUM, 2));
        
        AspectRegistry.registerBlockAspects(ModBlocks.RESEARCH_TABLE.get(), 
            new AspectList().add(Aspect.PRAECANTATIO, 4).add(Aspect.VICTUS, 2).add(Aspect.ORDO, 2));
        
        AspectRegistry.registerBlockAspects(ModBlocks.ARCANE_WORKBENCH.get(), 
            new AspectList().add(Aspect.PRAECANTATIO, 3).add(Aspect.ORDO, 2).add(Aspect.TERRA, 2));
        
        AspectRegistry.registerBlockAspects(ModBlocks.INFUSION_ALTAR.get(), 
            new AspectList().add(Aspect.PRAECANTATIO, 6).add(Aspect.POTENTIA, 4).add(Aspect.ORDO, 3));
        
        AspectRegistry.registerBlockAspects(ModBlocks.ALCHEMICAL_FURNACE.get(), 
            new AspectList().add(Aspect.IGNIS, 4).add(Aspect.PRAECANTATIO, 3).add(Aspect.METALLUM, 2));
        
        AspectRegistry.registerBlockAspects(ModBlocks.ESSENTIA_SMELTERY.get(), 
            new AspectList().add(Aspect.IGNIS, 3).add(Aspect.PRAECANTATIO, 4).add(Aspect.VACUOS, 2));
        
        AspectRegistry.registerBlockAspects(ModBlocks.ELDRITCH_OBELISK.get(), 
            new AspectList().add(Aspect.VACUOS, 8).add(Aspect.PRAECANTATIO, 6).add(Aspect.PERDITIO, 4));
        
        AspectRegistry.registerBlockAspects(ModBlocks.FLUX_SCRUBBER.get(), 
            new AspectList().add(Aspect.ORDO, 5).add(Aspect.PRAECANTATIO, 4).add(Aspect.AER, 3));
        
        // Ores
        AspectRegistry.registerBlockAspects(ModBlocks.CINNABAR_ORE.get(), 
            new AspectList().add(Aspect.METALLUM, 3).add(Aspect.AQUA, 2).add(Aspect.TERRA, 2));
        
        AspectRegistry.registerBlockAspects(ModBlocks.AMBER_ORE.get(), 
            new AspectList().add(Aspect.VITREUS, 3).add(Aspect.VICTUS, 2).add(Aspect.TERRA, 2));
        
        // Plants and trees
        AspectRegistry.registerBlockAspects(ModBlocks.SHIMMERLEAF.get(), 
            new AspectList().add(Aspect.VICTUS, 2).add(Aspect.LUX, 3).add(Aspect.PRAECANTATIO, 1));
        
        AspectRegistry.registerBlockAspects(ModBlocks.GREATWOOD_LOG.get(), 
            new AspectList().add(Aspect.TERRA, 3).add(Aspect.VICTUS, 2).add(Aspect.PRAECANTATIO, 2));
        
        AspectRegistry.registerBlockAspects(ModBlocks.SILVERWOOD_LOG.get(), 
            new AspectList().add(Aspect.TERRA, 3).add(Aspect.VICTUS, 2).add(Aspect.PRAECANTATIO, 3).add(Aspect.ORDO, 2));
    }
}
