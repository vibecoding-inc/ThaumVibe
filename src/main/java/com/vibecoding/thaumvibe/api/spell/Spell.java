package com.vibecoding.thaumvibe.api.spell;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * Base interface for all spells that can be cast with wands.
 * Each spell has a name, Vis cost, cooldown, and casting logic.
 */
public interface Spell {
    /**
     * Get the unique identifier for this spell
     */
    String getId();
    
    /**
     * Get the display name of this spell
     */
    String getName();
    
    /**
     * Get the Vis cost to cast this spell
     */
    int getVisCost();
    
    /**
     * Get the cooldown in ticks (20 ticks = 1 second)
     */
    int getCooldown();
    
    /**
     * Cast the spell
     * @param level The level/world
     * @param player The player casting the spell
     * @return true if the spell was successfully cast
     */
    boolean cast(Level level, Player player);
    
    /**
     * Get a description of what this spell does
     */
    String getDescription();
}
