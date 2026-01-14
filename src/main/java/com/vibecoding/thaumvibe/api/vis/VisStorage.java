package com.vibecoding.thaumvibe.api.vis;

/**
 * Vis is the magical energy used to power Thaumcraft's devices and spells.
 * This class manages Vis for wands and other magical items.
 */
public class VisStorage {
    private int currentVis;
    private final int maxVis;
    private final int rechargeRate;
    
    public VisStorage(int maxVis, int rechargeRate) {
        this.maxVis = maxVis;
        this.currentVis = maxVis;
        this.rechargeRate = rechargeRate;
    }
    
    /**
     * Attempt to extract Vis from this storage
     * @param amount Amount of Vis to extract
     * @param simulate If true, only simulate the extraction
     * @return The amount of Vis actually extracted
     */
    public int extractVis(int amount, boolean simulate) {
        int extracted = Math.min(amount, currentVis);
        if (!simulate) {
            currentVis -= extracted;
        }
        return extracted;
    }
    
    /**
     * Add Vis to this storage
     * @param amount Amount of Vis to add
     * @param simulate If true, only simulate the addition
     * @return The amount of Vis actually added
     */
    public int receiveVis(int amount, boolean simulate) {
        int received = Math.min(amount, maxVis - currentVis);
        if (!simulate) {
            currentVis += received;
        }
        return received;
    }
    
    /**
     * Recharge Vis over time
     */
    public void recharge() {
        currentVis = Math.min(currentVis + rechargeRate, maxVis);
    }
    
    public int getCurrentVis() {
        return currentVis;
    }
    
    public int getMaxVis() {
        return maxVis;
    }
    
    public boolean hasVis(int amount) {
        return currentVis >= amount;
    }
}
