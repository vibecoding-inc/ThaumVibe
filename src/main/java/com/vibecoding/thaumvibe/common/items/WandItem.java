package com.vibecoding.thaumvibe.common.items;

import com.vibecoding.thaumvibe.api.vis.VisStorage;
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
 * The Wand - a magical tool for channeling Vis energy.
 * Core tool in Thaumcraft used for crafting and casting.
 */
public class WandItem extends Item {
    private static final int MAX_VIS = 100;
    private static final int RECHARGE_RATE = 1;
    
    public WandItem(Properties properties) {
        super(properties);
    }
    
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        
        if (!level.isClientSide) {
            // Simulate Vis usage
            VisStorage visStorage = new VisStorage(MAX_VIS, RECHARGE_RATE);
            int visUsed = visStorage.extractVis(10, false);
            
            if (visUsed > 0) {
                player.displayClientMessage(
                    Component.literal("Wand discharged " + visUsed + " Vis! Remaining: " + 
                    (MAX_VIS - visUsed) + "/" + MAX_VIS), 
                    true
                );
            } else {
                player.displayClientMessage(Component.literal("Wand is out of Vis!"), true);
            }
        }
        
        return InteractionResultHolder.success(itemStack);
    }
    
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("A tool for channeling magical Vis"));
        tooltipComponents.add(Component.literal("§9Vis: " + MAX_VIS + "/" + MAX_VIS));
        tooltipComponents.add(Component.literal("§7Used in Arcane Workbench for crafting"));
        super.appendHoverText(stack, level, tooltipComponents, tooltipFlag);
    }
    
    @Override
    public boolean isBarVisible(ItemStack stack) {
        return stack.getDamageValue() > 0;
    }
    
    @Override
    public boolean isFoil(ItemStack stack) {
        // Make wands have enchantment glint
        return true;
    }
}

