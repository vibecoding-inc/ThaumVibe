package com.vibecoding.thaumvibe.common.spell;

import com.vibecoding.thaumvibe.api.spell.Spell;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * Leap spell - launches the player into the air
 */
public class LeapSpell implements Spell {
    @Override
    public String getId() {
        return "leap";
    }
    
    @Override
    public String getName() {
        return "Leap";
    }
    
    @Override
    public int getVisCost() {
        return 15;
    }
    
    @Override
    public int getCooldown() {
        return 30; // 1.5 seconds
    }
    
    @Override
    public boolean cast(Level level, Player player) {
        if (!level.isClientSide) {
            Vec3 lookVec = player.getLookAngle();
            Vec3 motion = lookVec.scale(1.5).add(0, 1.0, 0); // Launch forward and up
            
            player.setDeltaMovement(motion);
            player.hurtMarked = true; // Force velocity update
            
            // Temporary slow falling to prevent fall damage
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 60, 0));
            
            level.playSound(null, player.getX(), player.getY(), player.getZ(), 
                SoundEvents.ENDER_DRAGON_FLAP, SoundSource.PLAYERS, 0.5F, 1.5F);
        }
        return true;
    }
    
    @Override
    public String getDescription() {
        return "Launches you into the air with magical force";
    }
}
