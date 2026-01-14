package com.vibecoding.thaumvibe.api.aspects;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Represents a collection of aspects with their amounts.
 * Used for research requirements, item aspects, etc.
 */
public class AspectList {
    private final Map<Aspect, Integer> aspects;
    
    public AspectList() {
        this.aspects = new HashMap<>();
    }
    
    public AspectList add(Aspect aspect, int amount) {
        aspects.put(aspect, aspects.getOrDefault(aspect, 0) + amount);
        return this;
    }
    
    public AspectList remove(Aspect aspect, int amount) {
        int current = aspects.getOrDefault(aspect, 0);
        int newAmount = Math.max(0, current - amount);
        if (newAmount == 0) {
            aspects.remove(aspect);
        } else {
            aspects.put(aspect, newAmount);
        }
        return this;
    }
    
    public int getAmount(Aspect aspect) {
        return aspects.getOrDefault(aspect, 0);
    }
    
    public Set<Aspect> getAspects() {
        return aspects.keySet();
    }
    
    public boolean contains(Aspect aspect) {
        return aspects.containsKey(aspect);
    }
    
    public int size() {
        return aspects.size();
    }
    
    public boolean isEmpty() {
        return aspects.isEmpty();
    }
    
    public AspectList copy() {
        AspectList copy = new AspectList();
        copy.aspects.putAll(this.aspects);
        return copy;
    }
}
