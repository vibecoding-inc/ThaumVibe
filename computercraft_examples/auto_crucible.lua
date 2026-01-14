-- Automated Crucible Manager
-- This script automatically processes items in a crucible and extracts the results.

local crucible = peripheral.find("crucible")
local chest = peripheral.find("minecraft:chest")

if not crucible then
    print("Error: No crucible found!")
    print("Place computer next to a crucible")
    return
end

function processItems(itemName, count, targetAspect)
    print("Adding " .. count .. " " .. itemName .. " to crucible")
    crucible.addToCrucible(itemName, count)
    
    -- Wait for processing
    sleep(5)
    
    -- Check essentia
    local essentia = crucible.getCrucibleEssentia()
    if essentia[targetAspect] and essentia[targetAspect] > 0 then
        print("Extracting " .. targetAspect)
        crucible.extractFromCrucible(targetAspect)
        return true
    end
    return false
end

-- Process iron for metallum essentia
print("=== Automated Crucible Manager ===")
print("Starting processing...")
processItems("minecraft:iron_ingot", 5, "metallum")
print("Processing complete!")
