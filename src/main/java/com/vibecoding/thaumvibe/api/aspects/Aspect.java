package com.vibecoding.thaumvibe.api.aspects;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a primal or compound aspect in the Thaumcraft magical system.
 * Aspects are fundamental magical elements that make up all things.
 */
public class Aspect {
    private static final Map<String, Aspect> ASPECTS = new HashMap<>();
    
    private final String tag;
    private final int color;
    private final String name;
    private final Aspect[] components;
    
    // Primal aspects - the six fundamental elements
    public static final Aspect AER = new Aspect("aer", 0xFFFFE5, "Air");
    public static final Aspect TERRA = new Aspect("terra", 0x00FF00, "Earth");
    public static final Aspect IGNIS = new Aspect("ignis", 0xFF5555, "Fire");
    public static final Aspect AQUA = new Aspect("aqua", 0x5555FF, "Water");
    public static final Aspect ORDO = new Aspect("ordo", 0xAAAAFF, "Order");
    public static final Aspect PERDITIO = new Aspect("perditio", 0x404040, "Entropy");
    
    // Compound aspects - combinations of primal aspects
    public static final Aspect VACUOS = new Aspect("vacuos", 0x888888, "Void", AER, PERDITIO);
    public static final Aspect LUX = new Aspect("lux", 0xFFFFC0, "Light", AER, IGNIS);
    public static final Aspect MOTUS = new Aspect("motus", 0xCDCDFF, "Motion", AER, ORDO);
    public static final Aspect GELUM = new Aspect("gelum", 0xE0FFFF, "Ice", IGNIS, PERDITIO);
    public static final Aspect VITREUS = new Aspect("vitreus", 0x80FFFF, "Crystal", TERRA, ORDO);
    public static final Aspect METALLUM = new Aspect("metallum", 0xB5B5CD, "Metal", TERRA, ORDO);
    public static final Aspect VICTUS = new Aspect("victus", 0xDE0005, "Life", AQUA, TERRA);
    public static final Aspect MORTUUS = new Aspect("mortuus", 0x6A0005, "Death", AQUA, PERDITIO);
    public static final Aspect POTENTIA = new Aspect("potentia", 0xC0FFFF, "Energy", ORDO, IGNIS);
    public static final Aspect PRAECANTATIO = new Aspect("praecantatio", 0xCF00FF, "Magic", VACUOS, POTENTIA);
    
    // Tekkit-inspired aspects for magitech automation
    public static final Aspect MACHINA = new Aspect("machina", 0x8080A0, "Machine", MOTUS, METALLUM);
    public static final Aspect INSTRUMENTUM = new Aspect("instrumentum", 0xA0A0C0, "Tool", METALLUM, ORDO);
    
    private Aspect(String tag, int color, String name, Aspect... components) {
        this.tag = tag;
        this.color = color;
        this.name = name;
        this.components = components;
        ASPECTS.put(tag, this);
    }
    
    public String getTag() {
        return tag;
    }
    
    public int getColor() {
        return color;
    }
    
    public String getName() {
        return name;
    }
    
    public Aspect[] getComponents() {
        return components;
    }
    
    public boolean isPrimal() {
        return components.length == 0;
    }
    
    public static Aspect getAspect(String tag) {
        return ASPECTS.get(tag);
    }
}
