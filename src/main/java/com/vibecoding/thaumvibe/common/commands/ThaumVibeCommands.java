package com.vibecoding.thaumvibe.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.vibecoding.thaumvibe.common.items.WandItem;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/**
 * Commands for ThaumVibe mod - mainly for creative mode features
 */
public class ThaumVibeCommands {
    
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            Commands.literal("thaumvibe")
                .then(Commands.literal("creative")
                    .then(Commands.literal("infinitevis")
                        .requires(source -> source.hasPermission(2))
                        .executes(ThaumVibeCommands::toggleInfiniteVis))
                    .then(Commands.literal("nocooldown")
                        .requires(source -> source.hasPermission(2))
                        .executes(ThaumVibeCommands::toggleNoCooldown))
                    .then(Commands.literal("all")
                        .requires(source -> source.hasPermission(2))
                        .executes(ThaumVibeCommands::toggleAll)))
        );
    }
    
    /**
     * Validate that the player is holding a wand
     * @return The held wand ItemStack, or null if validation fails
     */
    private static ItemStack validateWandInHand(CommandContext<CommandSourceStack> context) {
        Player player = context.getSource().getPlayer();
        if (player == null) {
            context.getSource().sendFailure(Component.literal("This command can only be used by players"));
            return null;
        }
        
        ItemStack heldItem = player.getMainHandItem();
        if (!(heldItem.getItem() instanceof WandItem)) {
            context.getSource().sendFailure(Component.literal("§cYou must be holding a wand to use this command!"));
            return null;
        }
        
        return heldItem;
    }
    
    private static int toggleInfiniteVis(CommandContext<CommandSourceStack> context) {
        ItemStack heldItem = validateWandInHand(context);
        if (heldItem == null) {
            return 0;
        }
        
        CompoundTag tag = heldItem.getOrCreateTag();
        boolean currentState = tag.getBoolean("InfiniteVis");
        tag.putBoolean("InfiniteVis", !currentState);
        
        if (!currentState) {
            context.getSource().sendSuccess(() -> Component.literal("§aInfinite Vis enabled for this wand!"), true);
        } else {
            context.getSource().sendSuccess(() -> Component.literal("§eInfinite Vis disabled for this wand."), true);
        }
        
        return 1;
    }
    
    private static int toggleNoCooldown(CommandContext<CommandSourceStack> context) {
        ItemStack heldItem = validateWandInHand(context);
        if (heldItem == null) {
            return 0;
        }
        
        CompoundTag tag = heldItem.getOrCreateTag();
        boolean currentState = tag.getBoolean("NoCooldown");
        tag.putBoolean("NoCooldown", !currentState);
        
        if (!currentState) {
            context.getSource().sendSuccess(() -> Component.literal("§aCooldowns disabled for this wand!"), true);
        } else {
            context.getSource().sendSuccess(() -> Component.literal("§eCooldowns enabled for this wand."), true);
        }
        
        return 1;
    }
    
    private static int toggleAll(CommandContext<CommandSourceStack> context) {
        ItemStack heldItem = validateWandInHand(context);
        if (heldItem == null) {
            return 0;
        }
        
        CompoundTag tag = heldItem.getOrCreateTag();
        boolean currentInfiniteVis = tag.getBoolean("InfiniteVis");
        boolean currentNoCooldown = tag.getBoolean("NoCooldown");
        
        // If either is disabled, enable both. If both are enabled, disable both.
        boolean newState = !currentInfiniteVis || !currentNoCooldown;
        
        tag.putBoolean("InfiniteVis", newState);
        tag.putBoolean("NoCooldown", newState);
        
        if (newState) {
            context.getSource().sendSuccess(() -> Component.literal("§aCreative mode enabled! Infinite Vis and No Cooldowns."), true);
        } else {
            context.getSource().sendSuccess(() -> Component.literal("§eCreative mode disabled."), true);
        }
        
        return 1;
    }
}
