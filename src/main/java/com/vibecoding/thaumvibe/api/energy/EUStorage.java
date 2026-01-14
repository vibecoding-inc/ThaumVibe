package com.vibecoding.thaumvibe.api.energy;

/**
 * EU (Energy Units) storage for IndustrialCraft-style power system.
 * Handles storage, extraction, and reception of electrical energy.
 */
public class EUStorage extends EnergyStorage {
    
    public EUStorage(int capacity) {
        super(capacity);
    }
    
    public EUStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }
    
    public EUStorage(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }
}
