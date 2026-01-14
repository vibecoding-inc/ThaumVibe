package com.vibecoding.thaumvibe.api.research;

/**
 * Categories for organizing research in the Thaumonomicon
 */
public enum ResearchCategory {
    BASICS("Basics", "Basic Thaumaturgy"),
    ALCHEMY("Alchemy", "Alchemical Studies"),
    ARTIFICE("Artifice", "Magical Crafting"),
    THAUMATURGY("Thaumaturgy", "Advanced Magic"),
    ELDRITCH("Eldritch", "Forbidden Knowledge");
    
    private final String key;
    private final String displayName;
    
    ResearchCategory(String key, String displayName) {
        this.key = key;
        this.displayName = displayName;
    }
    
    public String getKey() {
        return key;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
