package com.vibecoding.thaumvibe.common.spell;

import com.vibecoding.thaumvibe.api.spell.Spell;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

/**
 * Zap spell - summons lightning at the target location
 */
public class ZapSpell implements Spell {
    @Override
    public String getId() {
        return "zap";
    }
    
    @Override
    public String getName() {
        return "Zap";
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
            // Raycast to find where the player is looking
            HitResult hitResult = player.pick(20.0D, 0.0F, false);
            Vec3 hitPos = hitResult.getLocation();
            BlockPos strikePos = new BlockPos((int)hitPos.x, (int)hitPos.y, (int)hitPos.z);
            
            // Summon lightning
            LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level);
            if (lightning != null) {
                lightning.moveTo(Vec3.atBottomCenterOf(strikePos));
                lightning.setCause(player instanceof net.minecraft.server.level.ServerPlayer ? (net.minecraft.server.level.ServerPlayer) player : null);
                level.addFreshEntity(lightning);
                
                level.playSound(null, strikePos, SoundEvents.LIGHTNING_BOLT_THUNDER, 
                    SoundSource.PLAYERS, 1.0F, 1.0F);
            }
        }
        return true;
    }
    
    @Override
    public String getDescription() {
        return "Strikes the target location with lightning";
    }
}
