package com.vibecoding.thaumvibe.api.energy;

/**
 * MJ (Minecraft Joules) storage for BuildCraft-style power system.
 * Handles storage, extraction, and reception of mechanical energy.
 */
public class MJStorage extends EnergyStorage {
    
    public MJStorage(int capacity) {
        super(capacity);
    }
    
    public MJStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }
    
    public MJStorage(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }
}
