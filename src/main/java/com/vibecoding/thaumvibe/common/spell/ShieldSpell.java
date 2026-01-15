package com.vibecoding.thaumvibe.common.spell;

import com.vibecoding.thaumvibe.api.spell.Spell;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * Shield spell - grants temporary protection
 */
public class ShieldSpell implements Spell {
    @Override
    public String getId() {
        return "shield";
    }
    
    @Override
    public String getName() {
        return "Shield";
    }
    
    @Override
    public int getVisCost() {
        return 20;
    }
    
    @Override
    public int getCooldown() {
        return 80; // 4 seconds
    }
    
    @Override
    public boolean cast(Level level, Player player) {
        if (!level.isClientSide) {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 1)); // 5 seconds of Resistance II
            player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 0)); // 5 seconds of Absorption I
            
            level.playSound(null, player.getX(), player.getY(), player.getZ(), 
                SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
        }
        return true;
    }
    
    @Override
    public String getDescription() {
        return "Grants temporary damage resistance and absorption";
    }
}
