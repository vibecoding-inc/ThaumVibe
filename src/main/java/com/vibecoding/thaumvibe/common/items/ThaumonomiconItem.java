package com.vibecoding.thaumvibe.common.items;

import com.vibecoding.thaumvibe.api.aspects.Aspect;
import com.vibecoding.thaumvibe.api.research.PlayerResearch;
import com.vibecoding.thaumvibe.api.research.ResearchCategory;
import com.vibecoding.thaumvibe.api.research.ResearchManager;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * The Thaumonomicon - a magical research book that tracks the player's progress.
 */
public class ThaumonomiconItem extends Item {
    
    public ThaumonomiconItem(Properties properties) {
        super(properties);
    }
    
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        
        if (!level.isClientSide) {
            displayResearchBook(player);
        }
        
        return InteractionResultHolder.success(itemStack);
    }
    
    private void displayResearchBook(Player player) {
        PlayerResearch research = ResearchManager.getPlayerResearch(player.getUUID());
        
        player.displayClientMessage(Component.literal("§5§l+================================+"), false);
        player.displayClientMessage(Component.literal("§5§l|      THAUMONOMICON      |"), false);
        player.displayClientMessage(Component.literal("§5§l|   Magical Research Compendium  |"), false);
        player.displayClientMessage(Component.literal("§5§l+================================+"), false);
        player.displayClientMessage(Component.literal(""), false);
        
        // Display discovered aspects
        player.displayClientMessage(Component.literal("§9§l✦ Discovered Aspects (" + research.getKnownAspects().size() + "/16):"), false);
        
        if (research.getKnownAspects().isEmpty()) {
            player.displayClientMessage(Component.literal("§7  Use a Thaumometer to scan objects"), false);
            player.displayClientMessage(Component.literal("§7  and discover their aspects!"), false);
        } else {
            displayPrimalAspects(player, research);
            displayCompoundAspects(player, research);
        }
        
        player.displayClientMessage(Component.literal(""), false);
        
        // Display research categories
        player.displayClientMessage(Component.literal("§d§l✦ Research Categories:"), false);
        for (ResearchCategory category : ResearchCategory.values()) {
            // For now, show total count (filtering by category would require Research objects to be registered)
            player.displayClientMessage(
                Component.literal("§7  " + category.getDisplayName() + ": §e0 completed"),
                false
            );
        }
        
        player.displayClientMessage(Component.literal(""), false);
        
        // Total progress
        int totalAspects = research.getKnownAspects().size();
        int totalResearch = research.getCompletedResearch().size();
        player.displayClientMessage(
            Component.literal("§6§l✦ Total Progress: §e" + totalAspects + " aspects, " + totalResearch + " research"),
            false
        );
        
        player.displayClientMessage(Component.literal("§5§l================================"), false);
    }
    
    private void displayPrimalAspects(Player player, PlayerResearch research) {
        player.displayClientMessage(Component.literal("§b  Primal Aspects:"), false);
        
        displayAspect(player, research, Aspect.AER);
        displayAspect(player, research, Aspect.TERRA);
        displayAspect(player, research, Aspect.IGNIS);
        displayAspect(player, research, Aspect.AQUA);
        displayAspect(player, research, Aspect.ORDO);
        displayAspect(player, research, Aspect.PERDITIO);
    }
    
    private void displayCompoundAspects(Player player, PlayerResearch research) {
        player.displayClientMessage(Component.literal("§b  Compound Aspects:"), false);
        
        displayAspect(player, research, Aspect.VACUOS);
        displayAspect(player, research, Aspect.LUX);
        displayAspect(player, research, Aspect.MOTUS);
        displayAspect(player, research, Aspect.GELUM);
        displayAspect(player, research, Aspect.VITREUS);
        displayAspect(player, research, Aspect.METALLUM);
        displayAspect(player, research, Aspect.VICTUS);
        displayAspect(player, research, Aspect.MORTUUS);
        displayAspect(player, research, Aspect.POTENTIA);
        displayAspect(player, research, Aspect.PRAECANTATIO);
    }
    
    private void displayAspect(Player player, PlayerResearch research, Aspect aspect) {
        if (research.knowsAspect(aspect.getTag())) {
            String components = "";
            if (!aspect.isPrimal()) {
                Aspect[] comps = aspect.getComponents();
                if (comps.length == 2) {
                    components = " §8[" + comps[0].getTag() + " + " + comps[1].getTag() + "]";
                }
            }
            player.displayClientMessage(
                Component.literal("§a    ✓ " + aspect.getName() + " §7(" + aspect.getTag() + ")" + components),
                false
            );
        }
    }
    
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("§7Your magical research compendium"));
        tooltipComponents.add(Component.literal("§7Tracks discovered aspects and research"));
        tooltipComponents.add(Component.literal("§8Right-click to open"));
        super.appendHoverText(stack, level, tooltipComponents, tooltipFlag);
    }
    
    @Override
    public boolean isFoil(ItemStack stack) {
        // Make thaumonomicon have enchantment glint
        return true;
    }
}
