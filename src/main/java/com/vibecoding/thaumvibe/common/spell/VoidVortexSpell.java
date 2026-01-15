package com.vibecoding.thaumvibe.common.spell;

import com.vibecoding.thaumvibe.api.spell.Spell;
import net.minecraft.core.BlockPos;
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
 * Void Vortex spell - pulls nearby entities towards a point and damages them
 */
public class VoidVortexSpell implements Spell {
    @Override
    public String getId() {
        return "void_vortex";
    }
    
    @Override
    public String getName() {
        return "Void Vortex";
    }
    
    @Override
    public int getVisCost() {
        return 35;
    }
    
    @Override
    public int getCooldown() {
        return 80; // 4 seconds
    }
    
    @Override
    public boolean cast(Level level, Player player) {
        if (!level.isClientSide) {
            Vec3 lookVec = player.getLookAngle();
            Vec3 centerPos = player.position().add(lookVec.scale(5.0));
            
            // Find all entities in a 10-block radius
            AABB searchBox = new AABB(centerPos.subtract(10, 10, 10), centerPos.add(10, 10, 10));
            List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, searchBox, 
                entity -> entity != player && entity.distanceToSqr(centerPos) < 100);
            
            for (LivingEntity entity : entities) {
                // Pull entity towards vortex center
                Vec3 pullVec = centerPos.subtract(entity.position()).normalize().scale(0.8);
                entity.setDeltaMovement(entity.getDeltaMovement().add(pullVec));
                entity.hurtMarked = true;
                
                // Damage and apply effects
                entity.hurt(level.damageSources().magic(), 4.0F);
                entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1));
                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 2));
            }
            
            level.playSound(null, centerPos.x, centerPos.y, centerPos.z, 
                SoundEvents.WITHER_SHOOT, SoundSource.PLAYERS, 1.0F, 0.5F);
        }
        return true;
    }
    
    @Override
    public String getDescription() {
        return "Creates a void vortex that pulls and damages enemies";
    }
}
