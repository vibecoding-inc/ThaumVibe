package com.vibecoding.thaumvibe.api.spell;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

/**
 * Registry for all available spells in ThaumVibe.
 * Spells must be registered here to be usable with wands.
 */
public class SpellRegistry {
    private static final Map<String, Spell> SPELLS = new HashMap<>();
    
    /**
     * Register a spell
     */
    public static void register(Spell spell) {
        SPELLS.put(spell.getId(), spell);
    }
    
    /**
     * Get a spell by its ID
     */
    public static Spell getSpell(String id) {
        return SPELLS.get(id);
    }
    
    /**
     * Get all registered spells
     */
    public static Collection<Spell> getAllSpells() {
        return SPELLS.values();
    }
    
    /**
     * Check if a spell is registered
     */
    public static boolean isRegistered(String id) {
        return SPELLS.containsKey(id);
    }
}
