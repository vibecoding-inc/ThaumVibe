package com.vibecoding.thaumvibe.common.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

/**
 * Ice shard projectile entity for the ice shard spell
 */
public class IceShardEntity extends AbstractHurtingProjectile {
    
    public IceShardEntity(EntityType<? extends IceShardEntity> entityType, Level level) {
        super(entityType, level);
    }
    
    public IceShardEntity(Level level, LivingEntity shooter) {
        super(EntityType.SMALL_FIREBALL, shooter, 0, 0, 0, level);
    }
    
    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (!this.level().isClientSide) {
            if (result.getEntity() instanceof LivingEntity target) {
                target.hurt(this.damageSources().magic(), 5.0F);
                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 2)); // 3 seconds of Slowness III
                target.setTicksFrozen(target.getTicksFrozen() + 100);
            }
            this.discard();
        }
    }
    
    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            this.discard();
        }
    }
    
    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            // Spawn snowflake particles
            this.level().addParticle(ParticleTypes.SNOWFLAKE, 
                this.getX(), this.getY(), this.getZ(), 
                0.0D, 0.0D, 0.0D);
            this.level().addParticle(ParticleTypes.SPIT, 
                this.getX(), this.getY(), this.getZ(), 
                0.0D, 0.0D, 0.0D);
        }
    }
    
    @Override
    protected boolean shouldBurn() {
        return false;
    }
}
