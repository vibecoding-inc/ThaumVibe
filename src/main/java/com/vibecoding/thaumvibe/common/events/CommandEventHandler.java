package com.vibecoding.thaumvibe.common.events;

import com.vibecoding.thaumvibe.common.commands.ThaumVibeCommands;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "thaumvibe")
public class CommandEventHandler {
    
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        ThaumVibeCommands.register(event.getDispatcher());
    }
}
