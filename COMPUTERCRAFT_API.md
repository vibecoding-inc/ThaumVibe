# ThaumScript - ComputerCraft API for ThaumVibe

## Introduction

ThaumScript is a comprehensive scripting API for ComputerCraft that allows you to control and automate ThaumVibe magical devices. This API provides programmatic access to all major Thaumcraft systems including aspects, vis, research, alchemy, and infusion.

## Table of Contents

1. [Getting Started](#getting-started)
2. [Peripheral Types](#peripheral-types)
3. [Core API Methods](#core-api-methods)
4. [Aspect System](#aspect-system)
5. [Vis Management](#vis-management)
6. [Crucible Operations](#crucible-operations)
7. [Arcane Workbench](#arcane-workbench)
8. [Infusion Altar](#infusion-altar)
9. [Research System](#research-system)
10. [Example Scripts](#example-scripts)

## Getting Started

### Prerequisites
- ComputerCraft mod installed
- ThaumVibe mod installed
- A computer from ComputerCraft
- ThaumVibe blocks (Crucible, Arcane Workbench, Infusion Altar, etc.)

### Connecting to ThaumVibe Blocks

Place a ComputerCraft computer adjacent to any ThaumVibe block. The block will automatically be detected as a peripheral.

```lua
-- Find all connected peripherals
local peripherals = peripheral.getNames()

-- Connect to a specific ThaumVibe block
local crucible = peripheral.wrap("right")

-- Or find by type
local crucible = peripheral.find("crucible")
```

## Peripheral Types

ThaumVibe blocks appear as the following peripheral types:

- `crucible` - Alchemical Crucible
- `arcane_workbench` - Arcane Workbench
- `research_table` - Research Table
- `infusion_altar` - Infusion Altar
- `alchemical_furnace` - Alchemical Furnace
- `essentia_smeltery` - Essentia Smeltery

## Core API Methods

### getType()

Returns the type of the peripheral.

```lua
local peripheral = peripheral.wrap("right")
local type = peripheral.getType()
print("Peripheral type: " .. type)
-- Output: Peripheral type: crucible
```

**Returns:** `string` - The peripheral type

### scan(offsetX, offsetY, offsetZ)

Scans a block at the given offset from the peripheral and returns its aspects.

```lua
local aspects = peripheral.scan(0, 1, 0)  -- Scan block above
for aspect, amount in pairs(aspects) do
    print(aspect .. ": " .. amount)
end
```

**Parameters:**
- `offsetX` (number) - X coordinate offset
- `offsetY` (number) - Y coordinate offset
- `offsetZ` (number) - Z coordinate offset

**Returns:** `table` - Map of aspect names to amounts

### getAspects()

Returns the aspects contained in this block.

```lua
local aspects = peripheral.getAspects()
for aspect, amount in pairs(aspects) do
    print(aspect .. ": " .. amount)
end
```

**Returns:** `table` - Map of aspect names to amounts

## Aspect System

### getPrimalAspects()

Returns an array of all primal aspect names.

```lua
local primals = peripheral.getPrimalAspects()
-- Returns: {"aer", "terra", "ignis", "aqua", "ordo", "perditio"}
```

**Returns:** `table` - Array of primal aspect names

**Primal Aspects:**
- `aer` - Air
- `terra` - Earth
- `ignis` - Fire
- `aqua` - Water
- `ordo` - Order
- `perditio` - Entropy

### getCompoundAspects()

Returns an array of all compound aspect names.

```lua
local compounds = peripheral.getCompoundAspects()
-- Returns: {"vacuos", "lux", "motus", "gelum", "vitreus", 
--           "metallum", "victus", "mortuus", "potentia", "praecantatio"}
```

**Returns:** `table` - Array of compound aspect names

**Compound Aspects:**
- `vacuos` - Void (Aer + Perditio)
- `lux` - Light (Aer + Ignis)
- `motus` - Motion (Aer + Ordo)
- `gelum` - Ice (Ignis + Perditio)
- `vitreus` - Crystal (Terra + Ordo)
- `metallum` - Metal (Terra + Ordo)
- `victus` - Life (Aqua + Terra)
- `mortuus` - Death (Aqua + Perditio)
- `potentia` - Energy (Ordo + Ignis)
- `praecantatio` - Magic (Vacuos + Potentia)

### getAspectComponents(aspectName)

Returns the component aspects that make up a compound aspect.

```lua
local components = peripheral.getAspectComponents("praecantatio")
-- Returns: {"vacuos", "potentia"}
```

**Parameters:**
- `aspectName` (string) - The compound aspect name

**Returns:** `table` - Array of component aspect names

## Vis Management

### getVis()

Returns the current Vis level in the block.

```lua
local currentVis = peripheral.getVis()
print("Current Vis: " .. currentVis)
```

**Returns:** `number` - Current Vis amount

### getMaxVis()

Returns the maximum Vis capacity of the block.

```lua
local maxVis = peripheral.getMaxVis()
print("Max Vis: " .. maxVis)
```

**Returns:** `number` - Maximum Vis capacity

### hasEnoughVis(amount)

Checks if there's enough Vis to perform an action.

```lua
if peripheral.hasEnoughVis(50) then
    print("Enough Vis available")
else
    print("Not enough Vis")
end
```

**Parameters:**
- `amount` (number) - The amount of Vis required

**Returns:** `boolean` - True if enough Vis is available

## Crucible Operations

### addToCrucible(itemName, count)

Adds items to the crucible from adjacent inventories.

```lua
local success = crucible.addToCrucible("minecraft:iron_ingot", 3)
if success then
    print("Added 3 iron ingots to crucible")
end
```

**Parameters:**
- `itemName` (string) - The item identifier
- `count` (number) - How many to add

**Returns:** `boolean` - True if added successfully

### getCrucibleEssentia()

Returns the essentia currently in the crucible.

```lua
local essentia = crucible.getCrucibleEssentia()
for aspect, amount in pairs(essentia) do
    print(aspect .. ": " .. amount .. " essentia")
end
```

**Returns:** `table` - Map of aspect names to essentia amounts

### extractFromCrucible(aspectName)

Extracts a specific result from the crucible into adjacent inventories.

```lua
local success = crucible.extractFromCrucible("metallum")
if success then
    print("Extracted metallum essentia")
end
```

**Parameters:**
- `aspectName` (string) - The aspect to extract

**Returns:** `boolean` - True if extracted successfully

## Arcane Workbench

### craftArcane(recipeName)

Crafts an item at the Arcane Workbench using Vis.

```lua
local workbench = peripheral.find("arcane_workbench")
local success = workbench.craftArcane("thaumvibe:wand")
if success then
    print("Crafted a wand")
end
```

**Parameters:**
- `recipeName` (string) - The recipe identifier

**Returns:** `boolean` - True if crafted successfully

### getAvailableRecipes()

Returns an array of recipes that can currently be crafted.

```lua
local recipes = workbench.getAvailableRecipes()
for _, recipe in ipairs(recipes) do
    print("Available: " .. recipe)
end
```

**Returns:** `table` - Array of recipe names

## Infusion Altar

### startInfusion(recipeName)

Starts an infusion recipe at the altar.

```lua
local altar = peripheral.find("infusion_altar")
local success = altar.startInfusion("thaumvibe:thaumium_ingot")
if success then
    print("Infusion started")
end
```

**Parameters:**
- `recipeName` (string) - The recipe identifier

**Returns:** `boolean` - True if started successfully

### getInfusionStatus()

Returns the current status of the infusion.

```lua
local status = altar.getInfusionStatus()
print("Status: " .. status)
-- Possible values: "idle", "infusing", "complete", "failed"
```

**Returns:** `string` - Status: "idle", "infusing", "complete", or "failed"

## Research System

### hasResearch(researchKey)

Checks if a specific research has been completed.

```lua
local hasBasics = peripheral.hasResearch("BASICS")
if hasBasics then
    print("Basic Thaumaturgy research complete")
end
```

**Parameters:**
- `researchKey` (string) - The research identifier

**Returns:** `boolean` - True if research is completed

## Example Scripts

### Example 1: Automated Crucible Processing

This script automatically processes items in a crucible and extracts the results.

```lua
-- Automated Crucible Manager
local crucible = peripheral.find("crucible")
local chest = peripheral.find("minecraft:chest")

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
processItems("minecraft:iron_ingot", 5, "metallum")
```

### Example 2: Vis Monitor

This script monitors Vis levels and alerts when it's low.

```lua
-- Vis Level Monitor
local workbench = peripheral.find("arcane_workbench")
local monitor = peripheral.find("monitor")

function updateDisplay()
    local vis = workbench.getVis()
    local maxVis = workbench.getMaxVis()
    local percent = (vis / maxVis) * 100
    
    monitor.clear()
    monitor.setCursorPos(1, 1)
    monitor.write("Vis Level Monitor")
    monitor.setCursorPos(1, 2)
    monitor.write("Current: " .. vis .. "/" .. maxVis)
    monitor.setCursorPos(1, 3)
    monitor.write("Percent: " .. math.floor(percent) .. "%")
    
    if percent < 20 then
        monitor.setCursorPos(1, 5)
        monitor.write("WARNING: Low Vis!")
    end
end

while true do
    updateDisplay()
    sleep(1)
end
```

### Example 3: Aspect Scanner

This script scans nearby blocks and reports their aspects.

```lua
-- Aspect Scanner
local peripheral = peripheral.wrap("right")

function scanArea(radius)
    print("Scanning " .. radius .. " block radius...")
    local results = {}
    
    for x = -radius, radius do
        for y = -radius, radius do
            for z = -radius, radius do
                local aspects = peripheral.scan(x, y, z)
                if next(aspects) ~= nil then
                    local pos = x .. "," .. y .. "," .. z
                    results[pos] = aspects
                end
            end
        end
    end
    
    return results
end

function displayResults(results)
    for pos, aspects in pairs(results) do
        print("\nPosition " .. pos .. ":")
        for aspect, amount in pairs(aspects) do
            print("  " .. aspect .. ": " .. amount)
        end
    end
end

local scanResults = scanArea(3)
displayResults(scanResults)
```

### Example 4: Automated Infusion

This script automates the infusion process with status monitoring.

```lua
-- Automated Infusion Manager
local altar = peripheral.find("infusion_altar")

function performInfusion(recipe)
    print("Starting infusion: " .. recipe)
    
    if not altar.startInfusion(recipe) then
        print("Failed to start infusion")
        return false
    end
    
    -- Monitor progress
    while true do
        local status = altar.getInfusionStatus()
        print("Status: " .. status)
        
        if status == "complete" then
            print("Infusion complete!")
            return true
        elseif status == "failed" then
            print("Infusion failed!")
            return false
        end
        
        sleep(2)
    end
end

-- Perform infusion
performInfusion("thaumvibe:thaumium_ingot")
```

### Example 5: Research Checker

This script checks which research you have completed.

```lua
-- Research Progress Checker
local peripheral = peripheral.wrap("right")

local researches = {
    "BASICS",
    "CRUCIBLE",
    "THAUMOMETER",
    "ARCANE_WORKBENCH",
    "INFUSION",
    "ALCHEMY",
    "THAUMIUM",
    "ELDRITCH"
}

print("Research Progress Report")
print("=======================")

for _, research in ipairs(researches) do
    local completed = peripheral.hasResearch(research)
    local status = completed and "[COMPLETE]" or "[INCOMPLETE]"
    print(status .. " " .. research)
end
```

### Example 6: Smart Crafting System

This script automatically crafts items when materials are available.

```lua
-- Smart Arcane Crafting System
local workbench = peripheral.find("arcane_workbench")

function craftIfPossible(recipe)
    local recipes = workbench.getAvailableRecipes()
    
    for _, available in ipairs(recipes) do
        if available == recipe then
            if workbench.hasEnoughVis(25) then
                print("Crafting " .. recipe)
                return workbench.craftArcane(recipe)
            else
                print("Not enough Vis")
                return false
            end
        end
    end
    
    print("Recipe not available: " .. recipe)
    return false
end

-- Try to craft a wand
craftIfPossible("thaumvibe:wand")
```

### Example 7: Aspect Analyzer

This script analyzes and reports aspect compositions.

```lua
-- Aspect Composition Analyzer
local peripheral = peripheral.wrap("right")

function analyzeAspect(aspectName)
    print("\nAnalyzing: " .. aspectName)
    
    local components = peripheral.getAspectComponents(aspectName)
    
    if #components == 0 then
        print("  Type: Primal Aspect")
        print("  Cannot be broken down further")
    else
        print("  Type: Compound Aspect")
        print("  Components:")
        for _, component in ipairs(components) do
            print("    - " .. component)
        end
    end
end

-- Analyze all compound aspects
local compounds = peripheral.getCompoundAspects()
for _, aspect in ipairs(compounds) do
    analyzeAspect(aspect)
end
```

## Best Practices

1. **Error Handling**: Always check return values from API calls
   ```lua
   local success = crucible.addToCrucible("item", 1)
   if not success then
       print("Operation failed")
   end
   ```

2. **Vis Management**: Check Vis levels before performing operations
   ```lua
   if workbench.hasEnoughVis(50) then
       workbench.craftArcane("recipe")
   end
   ```

3. **Polling**: Use appropriate sleep intervals when monitoring status
   ```lua
   while altar.getInfusionStatus() == "infusing" do
       sleep(2)  -- Don't poll too frequently
   end
   ```

4. **Resource Cleanup**: Ensure all operations complete properly
   ```lua
   local status = altar.getInfusionStatus()
   if status == "failed" then
       -- Clean up and reset
   end
   ```

## Advanced Topics

### Aspect Calculations

Understanding aspect relationships is key to automation:

- **Primal Aspects** cannot be broken down
- **Compound Aspects** are made from two other aspects
- Some compounds use other compounds as components

### Vis Regeneration

- Vis regenerates at 1 per tick
- Maximum capacity is typically 100
- Plan operations around regeneration time

### Multi-Block Structures

Some operations require proper multi-block setup:
- Infusion Altar needs pedestals
- Research Table needs specific aspect sources
- Plan automation around these requirements

## Troubleshooting

### Peripheral Not Found
```lua
local device = peripheral.find("crucible")
if not device then
    print("No crucible found nearby")
    return
end
```

### Operations Failing
- Check Vis levels
- Verify research is completed
- Ensure proper item availability
- Confirm multi-block structure is complete

### Slow Performance
- Reduce polling frequency
- Use event-based waiting when possible
- Batch operations together

## API Reference Summary

| Method | Returns | Description |
|--------|---------|-------------|
| `getType()` | string | Get peripheral type |
| `scan(x,y,z)` | table | Scan block for aspects |
| `getAspects()` | table | Get block's aspects |
| `getVis()` | number | Get current Vis |
| `getMaxVis()` | number | Get max Vis capacity |
| `hasEnoughVis(amount)` | boolean | Check Vis availability |
| `getPrimalAspects()` | table | Get all primal aspects |
| `getCompoundAspects()` | table | Get all compound aspects |
| `getAspectComponents(name)` | table | Get aspect components |
| `addToCrucible(item,count)` | boolean | Add to crucible |
| `getCrucibleEssentia()` | table | Get crucible contents |
| `extractFromCrucible(aspect)` | boolean | Extract from crucible |
| `craftArcane(recipe)` | boolean | Craft at workbench |
| `getAvailableRecipes()` | table | Get craftable recipes |
| `startInfusion(recipe)` | boolean | Start infusion |
| `getInfusionStatus()` | string | Get infusion status |
| `hasResearch(key)` | boolean | Check research status |

## Conclusion

ThaumScript provides comprehensive control over ThaumVibe's magical systems through ComputerCraft. Use this API to create powerful automation systems, monitoring tools, and custom magical applications.

For more examples and community scripts, visit the ThaumVibe GitHub repository.
