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
 * The Thaumometer - a device for scanning objects and discovering their aspects.
 */
public class ThaumometerItem extends Item {
    
    public ThaumometerItem(Properties properties) {
        super(properties);
    }
    
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        
        if (!level.isClientSide) {
            player.displayClientMessage(Component.literal("Thaumometer activated - Scan objects to discover their aspects!"), true);
        }
        
        return InteractionResultHolder.success(itemStack);
    }
    
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("Use to scan objects and discover aspects"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
