package com.vibecoding.thaumvibe.core.init;

import com.vibecoding.thaumvibe.ThaumVibe;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = 
        DeferredRegister.create(ForgeRegistries.BLOCKS, ThaumVibe.MODID);
    
    // EARLY GAME - Thaumcraft blocks
    public static final RegistryObject<Block> CRUCIBLE = BLOCKS.register("crucible",
        () -> new Block(BlockBehaviour.Properties.of()
            .strength(2.0f)
            .sound(SoundType.METAL)
            .noOcclusion()));
    
    public static final RegistryObject<Block> RESEARCH_TABLE = BLOCKS.register("research_table",
        () -> new Block(BlockBehaviour.Properties.of()
            .strength(2.0f)
            .sound(SoundType.WOOD)
            .noOcclusion()));
    
    public static final RegistryObject<Block> ARCANE_WORKBENCH = BLOCKS.register("arcane_workbench",
        () -> new Block(BlockBehaviour.Properties.of()
            .strength(2.5f)
            .sound(SoundType.WOOD)
            .noOcclusion()));
    
    // MIDDLE GAME - Advanced crafting
    public static final RegistryObject<Block> INFUSION_ALTAR = BLOCKS.register("infusion_altar",
        () -> new Block(BlockBehaviour.Properties.of()
            .strength(3.0f)
            .sound(SoundType.STONE)
            .noOcclusion()));
    
    public static final RegistryObject<Block> ALCHEMICAL_FURNACE = BLOCKS.register("alchemical_furnace",
        () -> new Block(BlockBehaviour.Properties.of()
            .strength(3.5f)
            .sound(SoundType.STONE)
            .lightLevel(state -> 13)
            .noOcclusion()));
    
    public static final RegistryObject<Block> ESSENTIA_SMELTERY = BLOCKS.register("essentia_smeltery",
        () -> new Block(BlockBehaviour.Properties.of()
            .strength(3.0f)
            .sound(SoundType.METAL)
            .noOcclusion()));
    
    // LATE GAME - Eldritch and advanced blocks
    public static final RegistryObject<Block> ELDRITCH_OBELISK = BLOCKS.register("eldritch_obelisk",
        () -> new Block(BlockBehaviour.Properties.of()
            .strength(50.0f)
            .sound(SoundType.STONE)
            .noOcclusion()));
    
    public static final RegistryObject<Block> FLUX_SCRUBBER = BLOCKS.register("flux_scrubber",
        () -> new Block(BlockBehaviour.Properties.of()
            .strength(4.0f)
            .sound(SoundType.METAL)
            .noOcclusion()));
    
    // Magical ores - EARLY GAME
    public static final RegistryObject<Block> CINNABAR_ORE = BLOCKS.register("cinnabar_ore",
        () -> new Block(BlockBehaviour.Properties.of()
            .strength(3.0f)
            .requiresCorrectToolForDrops()));
    
    public static final RegistryObject<Block> AMBER_ORE = BLOCKS.register("amber_ore",
        () -> new Block(BlockBehaviour.Properties.of()
            .strength(3.0f)
            .requiresCorrectToolForDrops()));
    
    // MIDDLE GAME ores
    public static final RegistryObject<Block> SHIMMERLEAF = BLOCKS.register("shimmerleaf",
        () -> new Block(BlockBehaviour.Properties.of()
            .strength(0.0f)
            .sound(SoundType.GRASS)
            .lightLevel(state -> 7)
            .noCollission()));
    
    public static final RegistryObject<Block> GREATWOOD_LOG = BLOCKS.register("greatwood_log",
        () -> new Block(BlockBehaviour.Properties.of()
            .strength(2.0f)
            .sound(SoundType.WOOD)));
    
    public static final RegistryObject<Block> SILVERWOOD_LOG = BLOCKS.register("silverwood_log",
        () -> new Block(BlockBehaviour.Properties.of()
            .strength(2.0f)
            .sound(SoundType.WOOD)));
    
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        
        // Register block items for each block
        BLOCKS.getEntries().forEach(block -> {
            ModItems.ITEMS.register(block.getId().getPath(), 
                () -> new BlockItem(block.get(), new Item.Properties()));
        });
    }
}
