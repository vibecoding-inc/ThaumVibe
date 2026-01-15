package com.vibecoding.thaumvibe.common.spell;

import com.vibecoding.thaumvibe.api.spell.Spell;
import com.vibecoding.thaumvibe.common.entity.IceShardEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * Ice Shard spell - launches a freezing projectile
 */
public class IceShardSpell implements Spell {
    @Override
    public String getId() {
        return "ice_shard";
    }
    
    @Override
    public String getName() {
        return "Ice Shard";
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
            IceShardEntity iceShard = new IceShardEntity(level, player);
            iceShard.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
            iceShard.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.0F, 1.0F);
            level.addFreshEntity(iceShard);
            
            level.playSound(null, player.getX(), player.getY(), player.getZ(), 
                SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, 0.5F, 1.5F);
        }
        return true;
    }
    
    @Override
    public String getDescription() {
        return "Fires an ice shard that freezes and damages enemies";
    }
}
