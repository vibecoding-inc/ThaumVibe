package com.vibecoding.thaumvibe.common.items;

import com.vibecoding.thaumvibe.api.aspects.Aspect;
import com.vibecoding.thaumvibe.api.aspects.AspectList;
import com.vibecoding.thaumvibe.api.aspects.AspectRegistry;
import com.vibecoding.thaumvibe.api.research.PlayerResearch;
import com.vibecoding.thaumvibe.api.research.ResearchManager;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * The Thaumometer - a device for scanning objects and discovering their aspects.
 */
public class ThaumometerItem extends Item {
    private static final double SCAN_RANGE = 5.0;
    
    public ThaumometerItem(Properties properties) {
        super(properties);
    }
    
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        
        if (player != null && !level.isClientSide) {
            Block block = level.getBlockState(pos).getBlock();
            AspectList aspects = AspectRegistry.getAspectsForBlock(block);
            
            if (!aspects.isEmpty()) {
                scanAspects(player, aspects, "Block: " + block.getName().getString());
                level.playSound(null, pos, SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 0.5f, 1.2f);
                return InteractionResult.SUCCESS;
            } else {
                player.displayClientMessage(Component.literal("§eNo aspects detected in this block."), true);
                return InteractionResult.CONSUME;
            }
        }
        
        return InteractionResult.SUCCESS;
    }
    
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        
        if (!level.isClientSide) {
            // Try to scan nearby entities
            // Note: Entity scanning is performed on-demand for accuracy, as entity positions change frequently
            Vec3 eyePos = player.getEyePosition(1.0f);
            Vec3 lookVec = player.getLookAngle();
            Vec3 endPos = eyePos.add(lookVec.scale(SCAN_RANGE));
            
            AABB scanBox = new AABB(eyePos, endPos).inflate(1.0);
            List<Entity> entities = level.getEntities(player, scanBox, entity -> entity instanceof LivingEntity);
            
            if (!entities.isEmpty()) {
                Entity target = entities.get(0);
                scanEntity(player, target);
                level.playSound(null, player.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 0.5f, 1.2f);
                return InteractionResultHolder.success(itemStack);
            }
            
            // Try to scan held item
            ItemStack offhandItem = player.getOffhandItem();
            if (!offhandItem.isEmpty() && hand == InteractionHand.MAIN_HAND) {
                AspectList aspects = AspectRegistry.getAspectsForItem(offhandItem);
                if (!aspects.isEmpty()) {
                    scanAspects(player, aspects, "Item: " + offhandItem.getHoverName().getString());
                    level.playSound(null, player.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 0.5f, 1.2f);
                    return InteractionResultHolder.success(itemStack);
                }
            }
            
            player.displayClientMessage(Component.literal("§ePoint at a block, entity, or hold an item in your offhand to scan!"), true);
        }
        
        return InteractionResultHolder.consume(itemStack);
    }
    
    private void scanAspects(Player player, AspectList aspects, String targetName) {
        PlayerResearch research = ResearchManager.getPlayerResearch(player.getUUID());
        
        player.displayClientMessage(Component.literal("§5§l=== Thaumometer Scan ==="), false);
        player.displayClientMessage(Component.literal("§7" + targetName), false);
        player.displayClientMessage(Component.literal("§9Aspects Detected:"), false);
        
        int newAspectsCount = 0;
        for (Aspect aspect : aspects.getAspects()) {
            int amount = aspects.getAmount(aspect);
            boolean isNew = !research.knowsAspect(aspect.getTag());
            
            if (isNew) {
                research.discoverAspect(aspect.getTag());
                newAspectsCount++;
            }
            
            String color = isNew ? "§a" : "§7";
            String newMarker = isNew ? " §e[NEW!]" : "";
            player.displayClientMessage(
                Component.literal(color + "  " + aspect.getName() + " (" + aspect.getTag() + "): " + amount + newMarker),
                false
            );
        }
        
        if (newAspectsCount > 0) {
            player.displayClientMessage(
                Component.literal("§6✨ Discovered " + newAspectsCount + " new aspect" + (newAspectsCount > 1 ? "s" : "") + "!"),
                false
            );
        }
    }
    
    private void scanEntity(Player player, Entity entity) {
        // Default aspects for living entities
        AspectList aspects = new AspectList();
        
        if (entity instanceof LivingEntity) {
            aspects.add(Aspect.VICTUS, 3);  // Life
            aspects.add(Aspect.MOTUS, 2);   // Motion
        }
        
        if (entity.isOnFire()) {
            aspects.add(Aspect.IGNIS, 2);   // Fire
        }
        
        scanAspects(player, aspects, "Entity: " + entity.getName().getString());
    }
    
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("§7Scan blocks, items, and entities"));
        tooltipComponents.add(Component.literal("§7to discover their magical aspects"));
        tooltipComponents.add(Component.literal("§8Right-click blocks to scan"));
        tooltipComponents.add(Component.literal("§8Hold item in offhand and right-click to scan"));
        super.appendHoverText(stack, level, tooltipComponents, tooltipFlag);
    }
}
