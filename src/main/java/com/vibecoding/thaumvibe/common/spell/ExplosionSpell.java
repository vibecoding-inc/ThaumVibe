package com.vibecoding.thaumvibe.common.spell;

import com.vibecoding.thaumvibe.api.spell.Spell;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * Explosion spell - creates a massive explosion at the target location
 */
public class ExplosionSpell implements Spell {
    @Override
    public String getId() {
        return "explosion";
    }
    
    @Override
    public String getName() {
        return "Explosion";
    }
    
    @Override
    public int getVisCost() {
        return 40;
    }
    
    @Override
    public int getCooldown() {
        return 100; // 5 seconds
    }
    
    @Override
    public boolean cast(Level level, Player player) {
        if (!level.isClientSide) {
            Vec3 lookVec = player.getLookAngle();
            Vec3 spawnPos = player.position().add(lookVec.scale(3.0));
            
            // Create multiple TNT entities for a dramatic effect
            for (int i = 0; i < 3; i++) {
                PrimedTnt tnt = new PrimedTnt(level, spawnPos.x, spawnPos.y + i * 0.5, spawnPos.z, player);
                tnt.setFuse(10 + i * 5); // Staggered explosions
                level.addFreshEntity(tnt);
            }
            
            level.playSound(null, player.getX(), player.getY(), player.getZ(), 
                SoundEvents.TNT_PRIMED, SoundSource.PLAYERS, 1.0F, 1.0F);
        }
        return true;
    }
    
    @Override
    public String getDescription() {
        return "Creates a massive explosion ahead of you";
    }
}
