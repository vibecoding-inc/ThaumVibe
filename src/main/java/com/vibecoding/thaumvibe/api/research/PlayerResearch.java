package com.vibecoding.thaumvibe.api.research;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Tracks research progress for a player
 */
public class PlayerResearch {
    private final UUID playerId;
    private final Set<String> completedResearch;
    private final Set<String> knownAspects;
    
    public PlayerResearch(UUID playerId) {
        this.playerId = playerId;
        this.completedResearch = new HashSet<>();
        this.knownAspects = new HashSet<>();
    }
    
    public void completeResearch(String researchKey) {
        completedResearch.add(researchKey);
    }
    
    public boolean hasResearch(String researchKey) {
        return completedResearch.contains(researchKey);
    }
    
    public void discoverAspect(String aspectTag) {
        knownAspects.add(aspectTag);
    }
    
    public boolean knowsAspect(String aspectTag) {
        return knownAspects.contains(aspectTag);
    }
    
    public Set<String> getCompletedResearch() {
        return new HashSet<>(completedResearch);
    }
    
    public Set<String> getKnownAspects() {
        return new HashSet<>(knownAspects);
    }
}
