package com.vibecoding.thaumvibe.api.aspects;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

/**
 * Registry for aspect mappings of items and blocks.
 * Determines what aspects each item/block contains.
 */
public class AspectRegistry {
    private static final Map<Item, AspectList> ITEM_ASPECTS = new HashMap<>();
    private static final Map<Block, AspectList> BLOCK_ASPECTS = new HashMap<>();
    
    /**
     * Register aspects for an item
     */
    public static void registerItemAspects(Item item, AspectList aspects) {
        ITEM_ASPECTS.put(item, aspects);
    }
    
    /**
     * Register aspects for a block
     */
    public static void registerBlockAspects(Block block, AspectList aspects) {
        BLOCK_ASPECTS.put(block, aspects);
    }
    
    /**
     * Get aspects for an item stack
     */
    public static AspectList getAspectsForItem(ItemStack stack) {
        if (stack.isEmpty()) {
            return new AspectList();
        }
        
        AspectList aspects = ITEM_ASPECTS.get(stack.getItem());
        return aspects != null ? aspects.copy() : new AspectList();
    }
    
    /**
     * Get aspects for a block
     */
    public static AspectList getAspectsForBlock(Block block) {
        AspectList aspects = BLOCK_ASPECTS.get(block);
        return aspects != null ? aspects.copy() : new AspectList();
    }
    
    /**
     * Check if an item has registered aspects
     */
    public static boolean hasAspects(Item item) {
        return ITEM_ASPECTS.containsKey(item);
    }
    
    /**
     * Check if a block has registered aspects
     */
    public static boolean hasAspects(Block block) {
        return BLOCK_ASPECTS.containsKey(block);
    }
}
