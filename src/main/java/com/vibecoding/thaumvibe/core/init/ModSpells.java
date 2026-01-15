package com.vibecoding.thaumvibe.core.init;

import com.vibecoding.thaumvibe.api.spell.SpellRegistry;
import com.vibecoding.thaumvibe.common.spell.*;

/**
 * Initializes and registers all spells
 */
public class ModSpells {
    
    public static void registerSpells() {
        // Register all spells
        SpellRegistry.register(new FireballSpell());
        SpellRegistry.register(new ZapSpell());
        SpellRegistry.register(new IceShardSpell());
        SpellRegistry.register(new HealSpell());
        SpellRegistry.register(new ShieldSpell());
    }
}
