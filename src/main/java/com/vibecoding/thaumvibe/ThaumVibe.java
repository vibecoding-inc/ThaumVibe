package com.vibecoding.thaumvibe;

import com.vibecoding.thaumvibe.core.init.ModBlocks;
import com.vibecoding.thaumvibe.core.init.ModCreativeTabs;
import com.vibecoding.thaumvibe.core.init.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(ThaumVibe.MODID)
public class ThaumVibe {
    public static final String MODID = "thaumvibe";
    public static final Logger LOGGER = LoggerFactory.getLogger(ThaumVibe.class);

    public ThaumVibe(IEventBus modEventBus) {
        LOGGER.info("Initializing ThaumVibe - A recreation of Thaumcraft");
        
        // Register items, blocks, and other content
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
    }
}
