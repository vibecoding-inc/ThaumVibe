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
    
    // TEKKIT-INSPIRED MAGITECH - Automation and technology integration
    public static final DeferredHolder<Item, Item> ESSENTIA_TUBE = ITEMS.register("essentia_tube",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> VIS_CONDUIT = ITEMS.register("vis_conduit",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> ARCANE_LEVITATOR = ITEMS.register("arcane_levitator",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> VIS_BATTERY = ITEMS.register("vis_battery",
        () -> new Item(new Item.Properties()));
    
    public static final DeferredHolder<Item, Item> GOLEM_ANIMATION_CORE = ITEMS.register("golem_animation_core",
        () -> new Item(new Item.Properties()));
    
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
