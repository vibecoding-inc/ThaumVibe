package com.vibecoding.thaumvibe.core.init;

import com.vibecoding.thaumvibe.ThaumVibe;
import com.vibecoding.thaumvibe.common.items.ThaumometerItem;
import com.vibecoding.thaumvibe.common.items.WandItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = 
        DeferredRegister.create(Registries.ITEM, ThaumVibe.MODID);
    
    // Basic Thaumcraft items
    public static final DeferredHolder<Item, Item> THAUMONOMICON = ITEMS.register("thaumonomicon",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> THAUMOMETER = ITEMS.register("thaumometer",
        () -> new ThaumometerItem(new Item.Properties().stacksTo(1)));
    
    public static final DeferredHolder<Item, Item> WAND = ITEMS.register("wand",
        () -> new WandItem(new Item.Properties().stacksTo(1).durability(100)));
    
    public static final DeferredHolder<Item, Item> SALIS_MUNDUS = ITEMS.register("salis_mundus",
        () -> new Item(new Item.Properties()));
    
    // Magical shards - EARLY GAME
    public static final DeferredHolder<Item, Item> AIR_SHARD = ITEMS.register("air_shard",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> FIRE_SHARD = ITEMS.register("fire_shard",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> WATER_SHARD = ITEMS.register("water_shard",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> EARTH_SHARD = ITEMS.register("earth_shard",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> ORDER_SHARD = ITEMS.register("order_shard",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> ENTROPY_SHARD = ITEMS.register("entropy_shard",
        () -> new Item(new Item.Properties()));
    
    // MIDDLE GAME - Vis crystals and essences
    public static final DeferredHolder<Item, Item> VIS_CRYSTAL = ITEMS.register("vis_crystal",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> ALCHEMICAL_BRASS = ITEMS.register("alchemical_brass",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> THAUMIUM_INGOT = ITEMS.register("thaumium_ingot",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> NITOR = ITEMS.register("nitor",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> QUICKSILVER = ITEMS.register("quicksilver",
        () -> new Item(new Item.Properties()));
    
    // LATE GAME - Advanced items
    public static final DeferredHolder<Item, Item> VOID_METAL_INGOT = ITEMS.register("void_metal_ingot",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> PRIMORDIAL_PEARL = ITEMS.register("primordial_pearl",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> ELDRITCH_EYE = ITEMS.register("eldritch_eye",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> SANITY_CHECKER = ITEMS.register("sanity_checker",
        () -> new Item(new Item.Properties()));
    
    // ========== INDUSTRIALCRAFT ITEMS ==========
    
    // IC2 Ingots and Materials
    public static final DeferredHolder<Item, Item> COPPER_INGOT = ITEMS.register("copper_ingot",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> TIN_INGOT = ITEMS.register("tin_ingot",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> BRONZE_INGOT = ITEMS.register("bronze_ingot",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> STEEL_INGOT = ITEMS.register("steel_ingot",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> URANIUM_INGOT = ITEMS.register("uranium_ingot",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> REFINED_IRON = ITEMS.register("refined_iron",
        () -> new Item(new Item.Properties()));
    
    // IC2 Components
    public static final DeferredHolder<Item, Item> COPPER_CABLE = ITEMS.register("copper_cable",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> GOLD_CABLE = ITEMS.register("gold_cable",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> GLASS_FIBER_CABLE = ITEMS.register("glass_fiber_cable",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> ELECTRONIC_CIRCUIT = ITEMS.register("electronic_circuit",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> ADVANCED_CIRCUIT = ITEMS.register("advanced_circuit",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> BATTERY = ITEMS.register("battery",
        () -> new Item(new Item.Properties().stacksTo(1)));
    
    public static final DeferredHolder<Item, Item> ADVANCED_BATTERY = ITEMS.register("advanced_battery",
        () -> new Item(new Item.Properties().stacksTo(1)));
    
    public static final DeferredHolder<Item, Item> ENERGY_CRYSTAL = ITEMS.register("energy_crystal",
        () -> new Item(new Item.Properties().stacksTo(1)));
    
    public static final DeferredHolder<Item, Item> LAPOTRON_CRYSTAL = ITEMS.register("lapotron_crystal",
        () -> new Item(new Item.Properties().stacksTo(1)));
    
    // IC2 Dusts (for macerator output)
    public static final DeferredHolder<Item, Item> IRON_DUST = ITEMS.register("iron_dust",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> GOLD_DUST = ITEMS.register("gold_dust",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> COPPER_DUST = ITEMS.register("copper_dust",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> TIN_DUST = ITEMS.register("tin_dust",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> BRONZE_DUST = ITEMS.register("bronze_dust",
        () -> new Item(new Item.Properties()));
    
    // ========== BUILDCRAFT ITEMS ==========
    
    // BC Gears
    public static final DeferredHolder<Item, Item> WOODEN_GEAR = ITEMS.register("wooden_gear",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> STONE_GEAR = ITEMS.register("stone_gear",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> IRON_GEAR = ITEMS.register("iron_gear",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> GOLD_GEAR = ITEMS.register("gold_gear",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> DIAMOND_GEAR = ITEMS.register("diamond_gear",
        () -> new Item(new Item.Properties()));
    
    // BC Pipes (items for placing)
    public static final DeferredHolder<Item, Item> WOODEN_TRANSPORT_PIPE = ITEMS.register("wooden_transport_pipe",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> COBBLESTONE_TRANSPORT_PIPE = ITEMS.register("cobblestone_transport_pipe",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> STONE_TRANSPORT_PIPE = ITEMS.register("stone_transport_pipe",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> IRON_TRANSPORT_PIPE = ITEMS.register("iron_transport_pipe",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> GOLD_TRANSPORT_PIPE = ITEMS.register("gold_transport_pipe",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> DIAMOND_TRANSPORT_PIPE = ITEMS.register("diamond_transport_pipe",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> WOODEN_FLUID_PIPE = ITEMS.register("wooden_fluid_pipe",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> COBBLESTONE_FLUID_PIPE = ITEMS.register("cobblestone_fluid_pipe",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> STONE_FLUID_PIPE = ITEMS.register("stone_fluid_pipe",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> WOODEN_POWER_PIPE = ITEMS.register("wooden_power_pipe",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> COBBLESTONE_POWER_PIPE = ITEMS.register("cobblestone_power_pipe",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> STONE_POWER_PIPE = ITEMS.register("stone_power_pipe",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> GOLD_POWER_PIPE = ITEMS.register("gold_power_pipe",
        () -> new Item(new Item.Properties()));
    
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
