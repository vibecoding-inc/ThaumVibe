package com.vibecoding.thaumvibe.api.research;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Global manager for player research data.
 * Stores and retrieves research progress for all players.
 */
public class ResearchManager {
    private static final Map<UUID, PlayerResearch> PLAYER_RESEARCH = new HashMap<>();
    
    /**
     * Get or create research data for a player
     */
    public static PlayerResearch getPlayerResearch(UUID playerId) {
        return PLAYER_RESEARCH.computeIfAbsent(playerId, PlayerResearch::new);
    }
    
    /**
     * Clear all research data (for cleanup/reset)
     */
    public static void clearAll() {
        PLAYER_RESEARCH.clear();
    }
}
