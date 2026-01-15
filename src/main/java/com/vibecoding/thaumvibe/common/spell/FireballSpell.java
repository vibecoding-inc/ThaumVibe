package com.vibecoding.thaumvibe.common.spell;

import com.vibecoding.thaumvibe.api.spell.Spell;
import com.vibecoding.thaumvibe.common.entity.FireballSpellEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * Fireball spell - launches an explosive projectile
 */
public class FireballSpell implements Spell {
    @Override
    public String getId() {
        return "fireball";
    }
    
    @Override
    public String getName() {
        return "Fireball";
    }
    
    @Override
    public int getVisCost() {
        return 20;
    }
    
    @Override
    public int getCooldown() {
        return 40; // 2 seconds
    }
    
    @Override
    public boolean cast(Level level, Player player) {
        if (!level.isClientSide) {
            FireballSpellEntity fireball = new FireballSpellEntity(level, player);
            fireball.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
            fireball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(fireball);
            
            level.playSound(null, player.getX(), player.getY(), player.getZ(), 
                SoundEvents.BLAZE_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F);
        }
        return true;
    }
    
    @Override
    public String getDescription() {
        return "Launches a blazing fireball that explodes on impact";
    }
}
