package com.vibecoding.thaumvibe.core.init;

import com.vibecoding.thaumvibe.ThaumVibe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = 
        DeferredRegister.create(Registries.BLOCK, ThaumVibe.MODID);
    
    // Thaumcraft blocks
    public static final DeferredHolder<Block, Block> CRUCIBLE = BLOCKS.register("crucible",
        () -> new Block(BlockBehaviour.Properties.of()
            .strength(2.0f)
            .sound(SoundType.METAL)
            .noOcclusion()));
    
    public static final DeferredHolder<Block, Block> RESEARCH_TABLE = BLOCKS.register("research_table",
        () -> new Block(BlockBehaviour.Properties.of()
            .strength(2.0f)
            .sound(SoundType.WOOD)
            .noOcclusion()));
    
    public static final DeferredHolder<Block, Block> ARCANE_WORKBENCH = BLOCKS.register("arcane_workbench",
        () -> new Block(BlockBehaviour.Properties.of()
            .strength(2.5f)
            .sound(SoundType.WOOD)
            .noOcclusion()));
    
    public static final DeferredHolder<Block, Block> INFUSION_ALTAR = BLOCKS.register("infusion_altar",
        () -> new Block(BlockBehaviour.Properties.of()
            .strength(3.0f)
            .sound(SoundType.STONE)
            .noOcclusion()));
    
    // Magical ores
    public static final DeferredHolder<Block, Block> CINNABAR_ORE = BLOCKS.register("cinnabar_ore",
        () -> new Block(BlockBehaviour.Properties.of()
            .strength(3.0f)
            .requiresCorrectToolForDrops()));
    
    public static final DeferredHolder<Block, Block> AMBER_ORE = BLOCKS.register("amber_ore",
        () -> new Block(BlockBehaviour.Properties.of()
            .strength(3.0f)
            .requiresCorrectToolForDrops()));
    
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        
        // Register block items for each block
        BLOCKS.getEntries().forEach(block -> {
            ModItems.ITEMS.register(block.getId().getPath(), 
                () -> new BlockItem(block.get(), new Item.Properties()));
        });
    }
}
