package com.vibecoding.thaumvibe.common.items;

import com.vibecoding.thaumvibe.api.spell.Spell;
import com.vibecoding.thaumvibe.api.spell.SpellRegistry;
import com.vibecoding.thaumvibe.api.vis.VisStorage;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

/**
 * The Wand - a magical tool for channeling Vis energy.
 * Core tool in Thaumcraft used for crafting and casting spells.
 */
public class WandItem extends Item {
    private static final int MAX_VIS = 100;
    private static final int RECHARGE_RATE = 1;
    private static final String VIS_TAG = "Vis";
    private static final String SPELL_TAG = "SelectedSpell";
    private static final String COOLDOWN_TAG = "Cooldown";
    private static final String LAST_CAST_TAG = "LastCast";
    
    public WandItem(Properties properties) {
        super(properties);
    }
    
    /**
     * Initialize wand with default values
     */
    private void initWandData(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.contains(VIS_TAG)) {
            tag.putInt(VIS_TAG, MAX_VIS);
        }
        if (!tag.contains(SPELL_TAG)) {
            tag.putString(SPELL_TAG, "fireball"); // Default to fireball
        }
        if (!tag.contains(LAST_CAST_TAG)) {
            tag.putLong(LAST_CAST_TAG, 0);
        }
    }
    
    /**
     * Get current Vis stored in the wand
     */
    private int getCurrentVis(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        return tag.getInt(VIS_TAG);
    }
    
    /**
     * Set current Vis in the wand
     */
    private void setCurrentVis(ItemStack stack, int vis) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt(VIS_TAG, Math.min(vis, MAX_VIS));
    }
    
    /**
     * Get the currently selected spell
     */
    private String getSelectedSpell(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        return tag.getString(SPELL_TAG);
    }
    
    /**
     * Set the currently selected spell
     */
    private void setSelectedSpell(ItemStack stack, String spellId) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putString(SPELL_TAG, spellId);
    }
    
    /**
     * Check if the wand is on cooldown
     */
    private boolean isOnCooldown(ItemStack stack, Level level) {
        CompoundTag tag = stack.getOrCreateTag();
        long lastCast = tag.getLong(LAST_CAST_TAG);
        return (level.getGameTime() - lastCast) < tag.getInt(COOLDOWN_TAG);
    }
    
    /**
     * Set cooldown for the wand
     */
    private void setCooldown(ItemStack stack, Level level, int cooldownTicks) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putLong(LAST_CAST_TAG, level.getGameTime());
        tag.putInt(COOLDOWN_TAG, cooldownTicks);
    }
    
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        initWandData(itemStack);
        
        if (!level.isClientSide) {
            String spellId = getSelectedSpell(itemStack);
            Spell spell = SpellRegistry.getSpell(spellId);
            
            if (spell == null) {
                player.displayClientMessage(Component.literal("§cNo spell selected!"), true);
                return InteractionResultHolder.fail(itemStack);
            }
            
            // Check cooldown
            if (isOnCooldown(itemStack, level)) {
                player.displayClientMessage(Component.literal("§eSpell on cooldown!"), true);
                return InteractionResultHolder.fail(itemStack);
            }
            
            // Check Vis
            int currentVis = getCurrentVis(itemStack);
            int visCost = spell.getVisCost();
            
            if (currentVis < visCost) {
                player.displayClientMessage(
                    Component.literal("§cNot enough Vis! Need " + visCost + ", have " + currentVis), 
                    true
                );
                return InteractionResultHolder.fail(itemStack);
            }
            
            // Cast the spell
            if (spell.cast(level, player)) {
                // Consume Vis
                setCurrentVis(itemStack, currentVis - visCost);
                
                // Set cooldown
                setCooldown(itemStack, level, spell.getCooldown());
                
                // Update durability bar for visual feedback
                itemStack.setDamageValue(MAX_VIS - getCurrentVis(itemStack));
                
                player.displayClientMessage(
                    Component.literal("§5Cast " + spell.getName() + "! §9(" + getCurrentVis(itemStack) + "/" + MAX_VIS + " Vis)"), 
                    true
                );
                
                return InteractionResultHolder.success(itemStack);
            }
        }
        
        return InteractionResultHolder.fail(itemStack);
    }
    
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        initWandData(stack);
        
        int currentVis = getCurrentVis(stack);
        String spellId = getSelectedSpell(stack);
        Spell spell = SpellRegistry.getSpell(spellId);
        
        tooltipComponents.add(Component.literal("§7A magical wand for casting spells"));
        tooltipComponents.add(Component.literal("§9Vis: " + currentVis + "/" + MAX_VIS));
        
        if (spell != null) {
            tooltipComponents.add(Component.literal("§5Selected Spell: §d" + spell.getName()));
            tooltipComponents.add(Component.literal("§7  " + spell.getDescription()));
            tooltipComponents.add(Component.literal("§7  Cost: §9" + spell.getVisCost() + " Vis"));
            tooltipComponents.add(Component.literal("§7  Cooldown: §e" + (spell.getCooldown() / 20.0) + "s"));
        }
        
        tooltipComponents.add(Component.literal("§8Shift+Right-Click to cycle spells"));
        
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
    
    /**
     * Cycle to the next spell
     */
    public void cycleSpell(ItemStack stack) {
        initWandData(stack);
        String currentSpell = getSelectedSpell(stack);
        
        List<String> spellIds = new ArrayList<>();
        for (Spell spell : SpellRegistry.getAllSpells()) {
            spellIds.add(spell.getId());
        }
        
        if (spellIds.isEmpty()) {
            return;
        }
        
        int currentIndex = spellIds.indexOf(currentSpell);
        int nextIndex = (currentIndex + 1) % spellIds.size();
        setSelectedSpell(stack, spellIds.get(nextIndex));
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

