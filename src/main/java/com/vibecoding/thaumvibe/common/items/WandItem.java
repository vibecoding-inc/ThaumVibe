package com.vibecoding.thaumvibe.common.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

/**
 * The Wand - a magical tool for channeling Vis energy.
 */
public class WandItem extends Item {
    
    public WandItem(Properties properties) {
        super(properties);
    }
    
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        
        if (!level.isClientSide) {
            player.displayClientMessage(Component.literal("Wand charged with magical energy!"), true);
        }
        
        return InteractionResultHolder.success(itemStack);
    }
    
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("A tool for channeling magical Vis"));
        tooltipComponents.add(Component.literal("Durability: " + (stack.getMaxDamage() - stack.getDamageValue()) + "/" + stack.getMaxDamage()));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
    
    @Override
    public boolean isBarVisible(ItemStack stack) {
        return stack.getDamageValue() > 0;
    }
}
