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
 * Teleport spell - teleports the player forward
 */
public class TeleportSpell implements Spell {
    @Override
    public String getId() {
        return "teleport";
    }
    
    @Override
    public String getName() {
        return "Blink";
    }
    
    @Override
    public int getVisCost() {
        return 25;
    }
    
    @Override
    public int getCooldown() {
        return 40; // 2 seconds
    }
    
    @Override
    public boolean cast(Level level, Player player) {
        if (!level.isClientSide) {
            Vec3 lookVec = player.getLookAngle();
            Vec3 teleportPos = player.position().add(lookVec.scale(10.0));
            
            // Ensure the player doesn't teleport into blocks
            teleportPos = new Vec3(teleportPos.x, Math.max(teleportPos.y, player.getY()), teleportPos.z);
            
            player.teleportTo(teleportPos.x, teleportPos.y, teleportPos.z);
            player.fallDistance = 0.0F; // Prevent fall damage
            
            level.playSound(null, player.getX(), player.getY(), player.getZ(), 
                SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
            
            // Brief nausea effect for flavor
            player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 20, 0));
        }
        return true;
    }
    
    @Override
    public String getDescription() {
        return "Instantly teleports you forward 10 blocks";
    }
}
