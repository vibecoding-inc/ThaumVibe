package com.vibecoding.thaumvibe.common.spell;

import com.vibecoding.thaumvibe.api.spell.Spell;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * Heal spell - restores player health
 */
public class HealSpell implements Spell {
    @Override
    public String getId() {
        return "heal";
    }
    
    @Override
    public String getName() {
        return "Heal";
    }
    
    @Override
    public int getVisCost() {
        return 25;
    }
    
    @Override
    public int getCooldown() {
        return 100; // 5 seconds
    }
    
    @Override
    public boolean cast(Level level, Player player) {
        if (!level.isClientSide) {
            player.heal(6.0F); // Heal 3 hearts
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 40, 1)); // 2 seconds of Regen II
            
            level.playSound(null, player.getX(), player.getY(), player.getZ(), 
                SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 0.5F, 1.5F);
        }
        return true;
    }
    
    @Override
    public String getDescription() {
        return "Restores health and grants brief regeneration";
    }
}
