package com.vibecoding.thaumvibe.common.events;

import com.vibecoding.thaumvibe.common.items.WandItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "thaumvibe")
public class WandEventHandler {
    private static final int MAX_VIS = 100;
    private static final int RECHARGE_RATE = 1;
    private static final int RECHARGE_INTERVAL = 20; // Recharge every second (20 ticks)
    
    /**
     * Handle shift+right-click to cycle spells
     */
    @SubscribeEvent
    public static void onPlayerRightClick(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();
        
        if (stack.getItem() instanceof WandItem wandItem && player.isCrouching()) {
            wandItem.cycleSpell(stack);
            
            if (!player.level().isClientSide) {
                CompoundTag tag = stack.getOrCreateTag();
                String spellName = tag.getString("SelectedSpell");
                player.displayClientMessage(
                    Component.literal("§5Switched to spell: §d" + spellName), 
                    true
                );
            }
            
            event.setCanceled(true);
        }
    }
    
    /**
     * Recharge Vis over time
     */
    @SubscribeEvent
    public static void onPlayerTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.level().getGameTime() % RECHARGE_INTERVAL == 0) {
                // Check all items in inventory for wands
                for (ItemStack stack : player.getInventory().items) {
                    if (stack.getItem() instanceof WandItem) {
                        rechargeWand(stack);
                    }
                }
            }
        }
    }
    
    private static void rechargeWand(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        int currentVis = tag.getInt("Vis");
        
        if (currentVis < MAX_VIS) {
            int newVis = Math.min(currentVis + RECHARGE_RATE, MAX_VIS);
            tag.putInt("Vis", newVis);
            
            // Update durability bar
            stack.setDamageValue(MAX_VIS - newVis);
        }
    }
}
