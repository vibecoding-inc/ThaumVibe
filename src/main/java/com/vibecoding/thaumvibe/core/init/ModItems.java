package com.vibecoding.thaumvibe.core.init;

import com.vibecoding.thaumvibe.ThaumVibe;
import com.vibecoding.thaumvibe.api.aspects.Aspect;
import com.vibecoding.thaumvibe.common.items.AspectInfusionItem;
import com.vibecoding.thaumvibe.common.items.ThaumonomiconItem;
import com.vibecoding.thaumvibe.common.items.ThaumometerItem;
import com.vibecoding.thaumvibe.common.items.WandItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = 
        DeferredRegister.create(ForgeRegistries.ITEMS, ThaumVibe.MODID);
    
    // Basic Thaumcraft items
    public static final RegistryObject<Item> THAUMONOMICON = ITEMS.register("thaumonomicon",
        () -> new ThaumonomiconItem(new Item.Properties().stacksTo(1)));
    
    public static final RegistryObject<Item> THAUMOMETER = ITEMS.register("thaumometer",
        () -> new ThaumometerItem(new Item.Properties().stacksTo(1)));
    
    public static final RegistryObject<Item> WAND = ITEMS.register("wand",
        () -> new WandItem(new Item.Properties().stacksTo(1).durability(100)));
    
    public static final RegistryObject<Item> SALIS_MUNDUS = ITEMS.register("salis_mundus",
        () -> new Item(new Item.Properties()));
    
    // Magical shards - EARLY GAME
    public static final RegistryObject<Item> AIR_SHARD = ITEMS.register("air_shard",
        () -> new Item(new Item.Properties()));
    
    public static final RegistryObject<Item> FIRE_SHARD = ITEMS.register("fire_shard",
        () -> new Item(new Item.Properties()));
    
    public static final RegistryObject<Item> WATER_SHARD = ITEMS.register("water_shard",
        () -> new Item(new Item.Properties()));
    
    public static final RegistryObject<Item> EARTH_SHARD = ITEMS.register("earth_shard",
        () -> new Item(new Item.Properties()));
    
    public static final RegistryObject<Item> ORDER_SHARD = ITEMS.register("order_shard",
        () -> new Item(new Item.Properties()));
    
    public static final RegistryObject<Item> ENTROPY_SHARD = ITEMS.register("entropy_shard",
        () -> new Item(new Item.Properties()));
    
    // MIDDLE GAME - Vis crystals and essences
    public static final RegistryObject<Item> VIS_CRYSTAL = ITEMS.register("vis_crystal",
        () -> new Item(new Item.Properties()));
    
    public static final RegistryObject<Item> ALCHEMICAL_BRASS = ITEMS.register("alchemical_brass",
        () -> new Item(new Item.Properties()));
    
    public static final RegistryObject<Item> THAUMIUM_INGOT = ITEMS.register("thaumium_ingot",
        () -> new Item(new Item.Properties()));
    
    public static final RegistryObject<Item> NITOR = ITEMS.register("nitor",
        () -> new Item(new Item.Properties()));
    
    public static final RegistryObject<Item> QUICKSILVER = ITEMS.register("quicksilver",
        () -> new Item(new Item.Properties()));
    
    // LATE GAME - Advanced items
    public static final RegistryObject<Item> VOID_METAL_INGOT = ITEMS.register("void_metal_ingot",
        () -> new Item(new Item.Properties()));
    
    public static final RegistryObject<Item> PRIMORDIAL_PEARL = ITEMS.register("primordial_pearl",
        () -> new Item(new Item.Properties()));
    
    public static final RegistryObject<Item> ELDRITCH_EYE = ITEMS.register("eldritch_eye",
        () -> new Item(new Item.Properties()));
    
    public static final RegistryObject<Item> SANITY_CHECKER = ITEMS.register("sanity_checker",
        () -> new Item(new Item.Properties()));
    
    // ASPECT INFUSION CRYSTALS - Consumables that grant aspect-themed buffs
    // Primal Aspects
    public static final RegistryObject<Item> INFUSION_AER = ITEMS.register("infusion_aer",
        () -> new AspectInfusionItem(Aspect.AER, new Item.Properties()));
    
    public static final RegistryObject<Item> INFUSION_TERRA = ITEMS.register("infusion_terra",
        () -> new AspectInfusionItem(Aspect.TERRA, new Item.Properties()));
    
    public static final RegistryObject<Item> INFUSION_IGNIS = ITEMS.register("infusion_ignis",
        () -> new AspectInfusionItem(Aspect.IGNIS, new Item.Properties()));
    
    public static final RegistryObject<Item> INFUSION_AQUA = ITEMS.register("infusion_aqua",
        () -> new AspectInfusionItem(Aspect.AQUA, new Item.Properties()));
    
    public static final RegistryObject<Item> INFUSION_ORDO = ITEMS.register("infusion_ordo",
        () -> new AspectInfusionItem(Aspect.ORDO, new Item.Properties()));
    
    public static final RegistryObject<Item> INFUSION_PERDITIO = ITEMS.register("infusion_perditio",
        () -> new AspectInfusionItem(Aspect.PERDITIO, new Item.Properties()));
    
    // Compound Aspects
    public static final RegistryObject<Item> INFUSION_VACUOS = ITEMS.register("infusion_vacuos",
        () -> new AspectInfusionItem(Aspect.VACUOS, new Item.Properties()));
    
    public static final RegistryObject<Item> INFUSION_LUX = ITEMS.register("infusion_lux",
        () -> new AspectInfusionItem(Aspect.LUX, new Item.Properties()));
    
    public static final RegistryObject<Item> INFUSION_MOTUS = ITEMS.register("infusion_motus",
        () -> new AspectInfusionItem(Aspect.MOTUS, new Item.Properties()));
    
    public static final RegistryObject<Item> INFUSION_GELUM = ITEMS.register("infusion_gelum",
        () -> new AspectInfusionItem(Aspect.GELUM, new Item.Properties()));
    
    public static final RegistryObject<Item> INFUSION_VITREUS = ITEMS.register("infusion_vitreus",
        () -> new AspectInfusionItem(Aspect.VITREUS, new Item.Properties()));
    
    public static final RegistryObject<Item> INFUSION_METALLUM = ITEMS.register("infusion_metallum",
        () -> new AspectInfusionItem(Aspect.METALLUM, new Item.Properties()));
    
    public static final RegistryObject<Item> INFUSION_VICTUS = ITEMS.register("infusion_victus",
        () -> new AspectInfusionItem(Aspect.VICTUS, new Item.Properties()));
    
    public static final RegistryObject<Item> INFUSION_MORTUUS = ITEMS.register("infusion_mortuus",
        () -> new AspectInfusionItem(Aspect.MORTUUS, new Item.Properties()));
    
    public static final RegistryObject<Item> INFUSION_POTENTIA = ITEMS.register("infusion_potentia",
        () -> new AspectInfusionItem(Aspect.POTENTIA, new Item.Properties()));
    
    public static final RegistryObject<Item> INFUSION_PRAECANTATIO = ITEMS.register("infusion_praecantatio",
        () -> new AspectInfusionItem(Aspect.PRAECANTATIO, new Item.Properties()));
    
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
