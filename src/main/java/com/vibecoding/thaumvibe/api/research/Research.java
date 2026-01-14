package com.vibecoding.thaumvibe.api.research;

import com.vibecoding.thaumvibe.api.aspects.Aspect;
import com.vibecoding.thaumvibe.api.aspects.AspectList;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a research entry in the Thaumonomicon.
 * Players must complete research to unlock new items, blocks, and abilities.
 */
public class Research {
    private final String key;
    private final String name;
    private final String description;
    private final ResearchCategory category;
    private final AspectList requiredAspects;
    private final List<String> prerequisites;
    private final List<String> unlocks;
    
    private Research(Builder builder) {
        this.key = builder.key;
        this.name = builder.name;
        this.description = builder.description;
        this.category = builder.category;
        this.requiredAspects = builder.requiredAspects;
        this.prerequisites = builder.prerequisites;
        this.unlocks = builder.unlocks;
    }
    
    public String getKey() {
        return key;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public ResearchCategory getCategory() {
        return category;
    }
    
    public AspectList getRequiredAspects() {
        return requiredAspects;
    }
    
    public List<String> getPrerequisites() {
        return prerequisites;
    }
    
    public List<String> getUnlocks() {
        return unlocks;
    }
    
    public static class Builder {
        private final String key;
        private String name;
        private String description;
        private ResearchCategory category;
        private AspectList requiredAspects = new AspectList();
        private List<String> prerequisites = new ArrayList<>();
        private List<String> unlocks = new ArrayList<>();
        
        public Builder(String key) {
            this.key = key;
        }
        
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public Builder description(String description) {
            this.description = description;
            return this;
        }
        
        public Builder category(ResearchCategory category) {
            this.category = category;
            return this;
        }
        
        public Builder requiredAspect(Aspect aspect, int amount) {
            this.requiredAspects.add(aspect, amount);
            return this;
        }
        
        public Builder prerequisite(String researchKey) {
            this.prerequisites.add(researchKey);
            return this;
        }
        
        public Builder unlock(String unlockKey) {
            this.unlocks.add(unlockKey);
            return this;
        }
        
        public Research build() {
            return new Research(this);
        }
    }
}
