package com.vibecoding.thaumvibe.common.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

/**
 * Fireball projectile entity for the fireball spell
 */
public class FireballSpellEntity extends AbstractHurtingProjectile {
    
    public FireballSpellEntity(EntityType<? extends FireballSpellEntity> entityType, Level level) {
        super(entityType, level);
    }
    
    public FireballSpellEntity(Level level, LivingEntity shooter) {
        super(EntityType.SMALL_FIREBALL, shooter, 0, 0, 0, level);
    }
    
    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (!this.level().isClientSide) {
            if (result.getEntity() instanceof LivingEntity target) {
                target.hurt(this.damageSources().thrown(this, this.getOwner()), 6.0F);
                target.setSecondsOnFire(3);
            }
            this.explodeAndRemove();
        }
    }
    
    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            this.explodeAndRemove();
        }
    }
    
    private void explodeAndRemove() {
        // Create a small explosion
        this.level().explode(this, this.getX(), this.getY(), this.getZ(), 
            1.5F, Level.ExplosionInteraction.NONE);
        this.discard();
    }
    
    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            // Spawn flame particles
            this.level().addParticle(ParticleTypes.FLAME, 
                this.getX(), this.getY(), this.getZ(), 
                0.0D, 0.0D, 0.0D);
        }
    }
    
    @Override
    protected boolean shouldBurn() {
        return false;
    }
}
