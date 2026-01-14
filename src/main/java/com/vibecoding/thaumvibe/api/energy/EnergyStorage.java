package com.vibecoding.thaumvibe.api.energy;

/**
 * Base energy storage class for both EU and MJ power systems.
 * Handles storage, extraction, and reception of energy.
 */
public abstract class EnergyStorage {
    private int energy;
    private final int capacity;
    private final int maxReceive;
    private final int maxExtract;
    
    public EnergyStorage(int capacity) {
        this(capacity, capacity, capacity);
    }
    
    public EnergyStorage(int capacity, int maxTransfer) {
        this(capacity, maxTransfer, maxTransfer);
    }
    
    public EnergyStorage(int capacity, int maxReceive, int maxExtract) {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.energy = 0;
    }
    
    /**
     * Add energy to the storage
     * @param amount Amount to add
     * @param simulate If true, just simulate the action
     * @return Amount actually added
     */
    public int receiveEnergy(int amount, boolean simulate) {
        int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, amount));
        if (!simulate) {
            energy += energyReceived;
        }
        return energyReceived;
    }
    
    /**
     * Remove energy from the storage
     * @param amount Amount to remove
     * @param simulate If true, just simulate the action
     * @return Amount actually removed
     */
    public int extractEnergy(int amount, boolean simulate) {
        int energyExtracted = Math.min(energy, Math.min(this.maxExtract, amount));
        if (!simulate) {
            energy -= energyExtracted;
        }
        return energyExtracted;
    }
    
    public int getEnergyStored() {
        return energy;
    }
    
    public int getMaxEnergyStored() {
        return capacity;
    }
    
    public boolean canExtract() {
        return maxExtract > 0;
    }
    
    public boolean canReceive() {
        return maxReceive > 0;
    }
    
    public void setEnergy(int energy) {
        this.energy = Math.max(0, Math.min(capacity, energy));
    }
}
