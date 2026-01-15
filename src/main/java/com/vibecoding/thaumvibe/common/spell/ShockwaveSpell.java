package com.vibecoding.thaumvibe.common.spell;

import com.vibecoding.thaumvibe.api.spell.Spell;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

/**
 * Shockwave spell - knocks back nearby enemies
 */
public class ShockwaveSpell implements Spell {
    @Override
    public String getId() {
        return "shockwave";
    }
    
    @Override
    public String getName() {
        return "Shockwave";
    }
    
    @Override
    public int getVisCost() {
        return 30;
    }
    
    @Override
    public int getCooldown() {
        return 60; // 3 seconds
    }
    
    @Override
    public boolean cast(Level level, Player player) {
        if (!level.isClientSide) {
            Vec3 playerPos = player.position();
            
            // Find all entities in a 8-block radius
            AABB searchBox = new AABB(playerPos.subtract(8, 4, 8), playerPos.add(8, 4, 8));
            List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, searchBox, 
                entity -> entity != player && entity.distanceToSqr(playerPos) < 64);
            
            for (LivingEntity entity : entities) {
                // Knock entity away from player
                Vec3 knockbackVec = entity.position().subtract(playerPos).normalize().scale(2.5).add(0, 0.8, 0);
                entity.setDeltaMovement(knockbackVec);
                entity.hurtMarked = true;
                
                // Damage and stun
                entity.hurt(level.damageSources().magic(), 5.0F);
                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 3));
            }
            
            level.playSound(null, player.getX(), player.getY(), player.getZ(), 
                SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.0F, 0.8F);
        }
        return true;
    }
    
    @Override
    public String getDescription() {
        return "Unleashes a shockwave that knocks back enemies";
    }
}
