package com.vibecoding.thaumvibe.common.items;

import com.vibecoding.thaumvibe.api.aspects.Aspect;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.core.particles.ParticleTypes;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Aspect Infusion Crystals - consumable items that temporarily grant aspect-themed buffs.
 * When consumed, they create particle effects matching the aspect's color and provide
 * themed magical enhancements to the player.
 */
public class AspectInfusionItem extends Item {
    private final Aspect aspect;
    private static final int USE_DURATION = 32; // Time to consume (1.6 seconds)
    private static final int EFFECT_DURATION = 1200; // 60 seconds
    
    public AspectInfusionItem(Aspect aspect, Properties properties) {
        super(properties);
        this.aspect = aspect;
    }
    
    public Aspect getAspect() {
        return aspect;
    }
    
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(itemStack);
    }
    
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (entity instanceof Player player) {
            if (!level.isClientSide) {
                // Apply aspect-specific effects
                applyAspectEffects(player, level);
                
                // Play sound
                level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 0.7F, 1.4F);
                
                // Show message
                player.displayClientMessage(
                    Component.literal("§5✦ Infused with " + aspect.getName() + " ✦")
                        .withStyle(ChatFormatting.LIGHT_PURPLE),
                    true
                );
                
                // Create particle effects
                spawnInfusionParticles((ServerLevel) level, player);
            }
            
            // Consume the item
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }
        
        return stack;
    }
    
    /**
     * Apply aspect-specific magical effects to the player
     */
    private void applyAspectEffects(Player player, Level level) {
        int duration = EFFECT_DURATION;
        
        switch (aspect.getTag()) {
            // Primal Aspects
            case "aer": // Air - Speed and Jump Boost
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, duration, 1));
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, duration, 1));
                player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, duration, 0));
                break;
                
            case "terra": // Earth - Resistance and Strength
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, duration, 1));
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, duration, 0));
                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, duration, 1));
                break;
                
            case "ignis": // Fire - Fire Resistance and Strength
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, duration, 0));
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, duration, 1));
                player.setRemainingFireTicks(0);
                break;
                
            case "aqua": // Water - Water Breathing and Regeneration
                player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, duration, 0));
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, duration, 0));
                player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, duration, 0));
                break;
                
            case "ordo": // Order - Luck and Resistance
                player.addEffect(new MobEffectInstance(MobEffects.LUCK, duration, 1));
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, duration, 0));
                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, duration, 0));
                break;
                
            case "perditio": // Entropy - Wither and Hunger (negative but with strength)
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, duration, 2));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, duration, 1));
                player.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 0)); // Short wither
                break;
                
            // Compound Aspects
            case "vacuos": // Void - Invisibility and Night Vision
                player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, duration, 0));
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, duration, 0));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, duration, 0));
                break;
                
            case "lux": // Light - Night Vision and Glowing
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, duration, 0));
                player.addEffect(new MobEffectInstance(MobEffects.GLOWING, duration, 0));
                player.addEffect(new MobEffectInstance(MobEffects.LUCK, duration, 0));
                break;
                
            case "motus": // Motion - Speed and Haste
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, duration, 2));
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, duration, 1));
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, duration, 2));
                break;
                
            case "gelum": // Ice - Frost Walker effect (simulated with resistance)
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, duration, 0));
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, duration, 1));
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, duration, 0));
                break;
                
            case "vitreus": // Crystal - Absorption and Resistance
                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, duration, 2));
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, duration, 1));
                break;
                
            case "metallum": // Metal - Resistance and Strength
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, duration, 2));
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, duration, 1));
                break;
                
            case "victus": // Life - Regeneration and Health Boost
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, duration, 1));
                player.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, duration, 1));
                player.addEffect(new MobEffectInstance(MobEffects.SATURATION, 200, 0));
                break;
                
            case "mortuus": // Death - Wither Resistance and Strength
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, duration, 2));
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, duration, 0));
                player.removeEffect(MobEffects.WITHER);
                break;
                
            case "potentia": // Energy - Haste and Speed
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, duration, 2));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, duration, 1));
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, duration, 0));
                break;
                
            case "praecantatio": // Magic - All-around magical enhancement
                player.addEffect(new MobEffectInstance(MobEffects.LUCK, duration, 2));
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, duration, 0));
                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, duration, 1));
                player.addEffect(new MobEffectInstance(MobEffects.GLOWING, duration, 0));
                break;
        }
    }
    
    /**
     * Spawn colorful particles around the player based on aspect color
     */
    private void spawnInfusionParticles(ServerLevel level, Player player) {
        double x = player.getX();
        double y = player.getY() + 1.0;
        double z = player.getZ();
        
        // Create a spiral of particles
        for (int i = 0; i < 50; i++) {
            double angle = (i / 50.0) * Math.PI * 4; // 2 full rotations
            double radius = 0.5 + (i / 50.0) * 0.5;
            double height = (i / 50.0) * 2.0;
            
            double offsetX = Math.cos(angle) * radius;
            double offsetZ = Math.sin(angle) * radius;
            double offsetY = height;
            
            // Choose particle type based on aspect theme
            var particleType = getParticleForAspect();
            
            level.sendParticles(particleType,
                x + offsetX, y + offsetY, z + offsetZ,
                1, 0.0, 0.0, 0.0, 0.0);
        }
        
        // Additional burst of particles at player location
        level.sendParticles(ParticleTypes.ENCHANT,
            x, y, z,
            30, 0.5, 1.0, 0.5, 0.5);
    }
    
    /**
     * Get appropriate particle type for the aspect
     */
    private net.minecraft.core.particles.SimpleParticleType getParticleForAspect() {
        return switch (aspect.getTag()) {
            case "aer" -> ParticleTypes.CLOUD;
            case "terra" -> ParticleTypes.HAPPY_VILLAGER;
            case "ignis" -> ParticleTypes.FLAME;
            case "aqua" -> ParticleTypes.FALLING_WATER;
            case "ordo" -> ParticleTypes.ENCHANT;
            case "perditio" -> ParticleTypes.SMOKE;
            case "vacuos" -> ParticleTypes.PORTAL;
            case "lux" -> ParticleTypes.END_ROD;
            case "motus" -> ParticleTypes.SWEEP_ATTACK;
            case "gelum" -> ParticleTypes.SNOWFLAKE;
            case "vitreus" -> ParticleTypes.GLOW;
            case "metallum" -> ParticleTypes.CRIT;
            case "victus" -> ParticleTypes.HEART;
            case "mortuus" -> ParticleTypes.SOUL;
            case "potentia" -> ParticleTypes.ELECTRIC_SPARK;
            case "praecantatio" -> ParticleTypes.WITCH;
            default -> ParticleTypes.ENCHANT;
        };
    }
    
    @Override
    public int getUseDuration(ItemStack stack) {
        return USE_DURATION;
    }
    
    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.EAT;
    }
    
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("§7A crystallized aspect infusion")
            .withStyle(ChatFormatting.GRAY));
        tooltipComponents.add(Component.literal("§9Aspect: §d" + aspect.getName())
            .withStyle(ChatFormatting.BLUE));
        tooltipComponents.add(Component.literal("§7Right-click to consume and gain")
            .withStyle(ChatFormatting.GRAY));
        tooltipComponents.add(Component.literal("§7aspect-themed magical effects")
            .withStyle(ChatFormatting.GRAY));
        tooltipComponents.add(Component.literal("§eDuration: §660 seconds")
            .withStyle(ChatFormatting.YELLOW));
        
        // Add specific effects for this aspect
        tooltipComponents.add(Component.literal(""));
        tooltipComponents.add(Component.literal("§5Effects:")
            .withStyle(ChatFormatting.DARK_PURPLE));
        addAspectEffectTooltip(tooltipComponents);
        
        super.appendHoverText(stack, level, tooltipComponents, tooltipFlag);
    }
    
    /**
     * Add aspect-specific effect descriptions to tooltip
     */
    private void addAspectEffectTooltip(List<Component> tooltipComponents) {
        switch (aspect.getTag()) {
            case "aer":
                tooltipComponents.add(Component.literal("  §7• Speed II, Jump Boost II, Slow Falling"));
                break;
            case "terra":
                tooltipComponents.add(Component.literal("  §7• Resistance II, Strength I, Absorption II"));
                break;
            case "ignis":
                tooltipComponents.add(Component.literal("  §7• Fire Resistance, Strength II"));
                break;
            case "aqua":
                tooltipComponents.add(Component.literal("  §7• Water Breathing, Regeneration, Dolphin's Grace"));
                break;
            case "ordo":
                tooltipComponents.add(Component.literal("  §7• Luck II, Resistance, Absorption"));
                break;
            case "perditio":
                tooltipComponents.add(Component.literal("  §7• Strength III, Speed II §c(Minor Wither)"));
                break;
            case "vacuos":
                tooltipComponents.add(Component.literal("  §7• Invisibility, Night Vision, Speed"));
                break;
            case "lux":
                tooltipComponents.add(Component.literal("  §7• Night Vision, Glowing, Luck"));
                break;
            case "motus":
                tooltipComponents.add(Component.literal("  §7• Speed III, Haste II, Jump Boost III"));
                break;
            case "gelum":
                tooltipComponents.add(Component.literal("  §7• Speed, Resistance II, Fire Resistance"));
                break;
            case "vitreus":
                tooltipComponents.add(Component.literal("  §7• Absorption III, Resistance II"));
                break;
            case "metallum":
                tooltipComponents.add(Component.literal("  §7• Resistance III, Strength II"));
                break;
            case "victus":
                tooltipComponents.add(Component.literal("  §7• Regeneration II, Health Boost II, Saturation"));
                break;
            case "mortuus":
                tooltipComponents.add(Component.literal("  §7• Strength III, Night Vision, Wither Immunity"));
                break;
            case "potentia":
                tooltipComponents.add(Component.literal("  §7• Haste III, Speed II, Regeneration"));
                break;
            case "praecantatio":
                tooltipComponents.add(Component.literal("  §7• Luck III, Regeneration, Absorption II, Glowing"));
                break;
        }
    }
    
    @Override
    public boolean isFoil(ItemStack stack) {
        // Make infusion crystals have enchantment glint
        return true;
    }
}
