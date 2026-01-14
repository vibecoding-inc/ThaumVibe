# ThaumVibe ComputerCraft Integration Guide

## Table of Contents

1. [Overview](#overview)
2. [Installation](#installation)
3. [Quick Start](#quick-start)
4. [Integration Architecture](#integration-architecture)
5. [Supported Blocks](#supported-blocks)
6. [Tutorial: First Automation](#tutorial-first-automation)
7. [Advanced Examples](#advanced-examples)
8. [Troubleshooting](#troubleshooting)
9. [FAQ](#faq)

## Overview

ThaumVibe provides native ComputerCraft integration through a peripheral system called **ThaumScript**. This allows you to programmatically control all major ThaumVibe blocks including Crucibles, Arcane Workbenches, Infusion Altars, and more.

### What You Can Do

- **Automate Alchemy**: Control crucibles and extract essentia automatically
- **Manage Vis**: Monitor and optimize magical energy usage
- **Craft Items**: Automate arcane workbench crafting
- **Run Infusions**: Control complex infusion processes
- **Scan Blocks**: Discover aspects in your world
- **Track Research**: Monitor research progress programmatically

### Prerequisites

- Minecraft 1.21.1+
- NeoForge 21.1.73+
- ThaumVibe mod installed
- ComputerCraft mod installed
- Basic Lua programming knowledge (helpful but not required)

## Installation

### Step 1: Install Required Mods

1. Install NeoForge for Minecraft 1.21.1
2. Download and install ThaumVibe mod
3. Download and install ComputerCraft mod
4. Place both JARs in your `mods` folder
5. Launch Minecraft

### Step 2: Verify Installation

In-game, check that both mods are loaded:

1. Go to the Mods menu
2. Verify "ThaumVibe" is listed
3. Verify "ComputerCraft" is listed

### Step 3: Test Integration

1. Craft a ComputerCraft computer
2. Craft a ThaumVibe Crucible
3. Place the computer adjacent to the Crucible
4. Turn on the computer
5. Type: `lua`
6. Type: `peripheral.find("crucible")`
7. You should see output indicating a peripheral was found

## Quick Start

### 5-Minute Tutorial

Create your first ThaumScript program in 5 minutes:

```lua
-- File: monitor_vis.lua
-- Monitor Vis levels in an Arcane Workbench

-- Connect to the workbench
local workbench = peripheral.find("arcane_workbench")

if not workbench then
    print("No arcane workbench found!")
    print("Place computer next to workbench")
    return
end

-- Main loop
while true do
    -- Get Vis levels
    local current = workbench.getVis()
    local maximum = workbench.getMaxVis()
    local percent = math.floor((current / maximum) * 100)
    
    -- Clear screen and display
    term.clear()
    term.setCursorPos(1, 1)
    print("=== Vis Monitor ===")
    print("")
    print("Current: " .. current)
    print("Maximum: " .. maximum)
    print("Percent: " .. percent .. "%")
    print("")
    
    -- Visual bar
    local barLength = 20
    local filled = math.floor(barLength * current / maximum)
    local bar = string.rep("#", filled) .. string.rep("-", barLength - filled)
    print("[" .. bar .. "]")
    
    -- Warning if low
    if percent < 25 then
        print("")
        print("WARNING: Low Vis!")
    end
    
    -- Update every second
    sleep(1)
end
```

To run:
1. Place computer next to Arcane Workbench
2. Turn on computer
3. Type: `edit monitor_vis`
4. Paste the code above
5. Press Ctrl, select "Save and Exit"
6. Type: `monitor_vis`

## Integration Architecture

### How It Works

```
┌─────────────────┐
│ ComputerCraft   │
│    Computer     │
└────────┬────────┘
         │
         │ Peripheral API
         │
┌────────▼────────┐
│  ThaumScript    │
│   Peripheral    │
└────────┬────────┘
         │
         │ Native Methods
         │
┌────────▼────────┐
│   ThaumVibe     │
│     Block       │
└─────────────────┘
```

### Peripheral Detection

ThaumVibe blocks automatically expose themselves as ComputerCraft peripherals when:
1. A computer is placed adjacent (any side)
2. A wired modem connects the computer to the block
3. The block is a supported ThaumVibe block type

### Method Invocation

When you call a method on a peripheral:
1. ComputerCraft sends the call to ThaumVibe
2. ThaumVibe validates the operation
3. ThaumVibe executes the operation
4. Results are returned to ComputerCraft
5. Your script receives the result

## Supported Blocks

### Crucible
**Peripheral Type:** `crucible`

**Features:**
- Add items for processing
- Monitor essentia levels
- Extract results

**Common Methods:**
- `addToCrucible(item, count)`
- `getCrucibleEssentia()`
- `extractFromCrucible(aspect)`

### Arcane Workbench
**Peripheral Type:** `arcane_workbench`

**Features:**
- Craft magical items
- Monitor Vis levels
- Check available recipes

**Common Methods:**
- `craftArcane(recipe)`
- `getVis()`
- `getAvailableRecipes()`

### Research Table
**Peripheral Type:** `research_table`

**Features:**
- Check research status
- Monitor discoveries
- Track aspect knowledge

**Common Methods:**
- `hasResearch(key)`
- `getAspects()`

### Infusion Altar
**Peripheral Type:** `infusion_altar`

**Features:**
- Start infusion recipes
- Monitor infusion progress
- Manage complex rituals

**Common Methods:**
- `startInfusion(recipe)`
- `getInfusionStatus()`

### Alchemical Furnace
**Peripheral Type:** `alchemical_furnace`

**Features:**
- Process items with essentia
- Monitor fuel and progress

**Common Methods:**
- `getVis()`
- `getAspects()`

### Essentia Smeltery
**Peripheral Type:** `essentia_smeltery`

**Features:**
- Break down items into aspects
- Extract pure essentia

**Common Methods:**
- `getAspects()`
- `scan(x, y, z)`

## Tutorial: First Automation

### Project: Automated Crucible System

Let's build a complete automation system that:
1. Accepts items from a chest
2. Processes them in a crucible
3. Extracts results to another chest

#### Step 1: Setup

Place blocks in this arrangement:
```
[Input Chest] - [Computer] - [Crucible] - [Output Chest]
```

#### Step 2: Write the Program

```lua
-- File: auto_crucible.lua
-- Automated Crucible Processing System

-- Configuration
local INPUT_SIDE = "left"
local OUTPUT_SIDE = "right"
local CRUCIBLE = "crucible"

-- Connect to peripherals
local crucible = peripheral.find(CRUCIBLE)
local inputChest = peripheral.wrap(INPUT_SIDE)
local outputChest = peripheral.wrap(OUTPUT_SIDE)

-- Validate connections
if not crucible then
    error("No crucible found!")
end
if not inputChest then
    error("No input chest found!")
end
if not outputChest then
    error("No output chest found!")
end

print("=== Auto Crucible System ===")
print("Crucible: Connected")
print("Input: Connected")
print("Output: Connected")
print("")

-- Processing recipes
local recipes = {
    ["minecraft:iron_ingot"] = {
        count = 5,
        aspect = "metallum",
        minAmount = 10
    },
    ["minecraft:gold_ingot"] = {
        count = 3,
        aspect = "metallum",
        minAmount = 8
    }
}

-- Main processing function
function processItem(itemName, recipe)
    print("Processing: " .. itemName)
    
    -- Add to crucible
    if not crucible.addToCrucible(itemName, recipe.count) then
        print("  Failed to add items")
        return false
    end
    print("  Added " .. recipe.count .. " items")
    
    -- Wait for processing
    local maxWait = 30
    local waited = 0
    
    while waited < maxWait do
        local essentia = crucible.getCrucibleEssentia()
        
        if essentia[recipe.aspect] and 
           essentia[recipe.aspect] >= recipe.minAmount then
            print("  Processing complete")
            
            -- Extract result
            if crucible.extractFromCrucible(recipe.aspect) then
                print("  Extracted " .. recipe.aspect)
                return true
            else
                print("  Failed to extract")
                return false
            end
        end
        
        sleep(1)
        waited = waited + 1
    end
    
    print("  Timeout waiting for processing")
    return false
end

-- Main loop
print("Starting main loop...")
while true do
    -- Check input chest
    local items = inputChest.list()
    
    for slot, item in pairs(items) do
        local itemName = item.name
        
        if recipes[itemName] then
            -- Pull from input
            inputChest.pushItems(peripheral.getName(crucible), slot, 
                               recipes[itemName].count)
            
            -- Process
            processItem(itemName, recipes[itemName])
            
            -- Brief pause
            sleep(2)
        end
    end
    
    -- Check every 5 seconds
    sleep(5)
end
```

#### Step 3: Run the System

1. Place computer between chests and crucible
2. Turn on computer
3. Type: `edit auto_crucible`
4. Paste the code
5. Save and exit
6. Type: `auto_crucible`
7. Put iron or gold ingots in the input chest
8. Watch the magic happen!

## Advanced Examples

### Multi-Device Controller

Control multiple crucibles simultaneously:

```lua
-- Find all crucibles
local crucibles = {peripheral.find("crucible")}

print("Found " .. #crucibles .. " crucibles")

-- Process jobs in parallel
local jobs = {
    {item = "minecraft:iron_ingot", count = 5},
    {item = "minecraft:gold_ingot", count = 3},
    {item = "minecraft:copper_ingot", count = 4}
}

-- Distribute jobs
for i, job in ipairs(jobs) do
    if crucibles[i] then
        print("Crucible " .. i .. ": " .. job.item)
        crucibles[i].addToCrucible(job.item, job.count)
    end
end
```

### Smart Crafting Queue

Maintain a crafting queue with priority:

```lua
local workbench = peripheral.find("arcane_workbench")

local queue = {
    {recipe = "thaumvibe:wand", priority = 10, vis = 50},
    {recipe = "thaumvibe:thaumometer", priority = 5, vis = 30},
    {recipe = "thaumvibe:goggles", priority = 8, vis = 40}
}

-- Sort by priority
table.sort(queue, function(a, b) 
    return a.priority > b.priority 
end)

-- Process queue
for _, job in ipairs(queue) do
    -- Wait for enough Vis
    while workbench.getVis() < job.vis do
        print("Waiting for Vis: " .. workbench.getVis() .. 
              "/" .. job.vis)
        sleep(5)
    end
    
    -- Craft
    print("Crafting: " .. job.recipe)
    if workbench.craftArcane(job.recipe) then
        print("  Success!")
    else
        print("  Failed!")
    end
    
    sleep(2)
end
```

### Infusion Automation

Fully automated infusion with monitoring:

```lua
local altar = peripheral.find("infusion_altar")
local monitor = peripheral.find("monitor")

function displayStatus(status, recipe)
    monitor.clear()
    monitor.setCursorPos(1, 1)
    monitor.write("Infusion Altar")
    monitor.setCursorPos(1, 2)
    monitor.write("================")
    monitor.setCursorPos(1, 4)
    monitor.write("Recipe: " .. recipe)
    monitor.setCursorPos(1, 5)
    monitor.write("Status: " .. status)
end

function runInfusion(recipe)
    print("Starting infusion: " .. recipe)
    displayStatus("Starting", recipe)
    
    if not altar.startInfusion(recipe) then
        print("Failed to start!")
        displayStatus("Failed", recipe)
        return false
    end
    
    -- Monitor progress
    while true do
        local status = altar.getInfusionStatus()
        displayStatus(status, recipe)
        
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

-- Run infusion
runInfusion("thaumvibe:thaumium_ingot")
```

### Aspect Database

Build a database of scanned aspects:

```lua
local scanner = peripheral.find("crucible")
local database = {}

function scanAndStore(x, y, z, name)
    local aspects = scanner.scan(x, y, z)
    database[name] = aspects
    
    print("Scanned: " .. name)
    for aspect, amount in pairs(aspects) do
        print("  " .. aspect .. ": " .. amount)
    end
end

-- Scan common items
scanAndStore(0, 1, 0, "dirt")
scanAndStore(1, 0, 0, "stone")
scanAndStore(0, 0, 1, "wood")

-- Save database to file
local file = fs.open("aspects.db", "w")
file.write(textutils.serialize(database))
file.close()

print("Database saved!")

-- Load database
local file = fs.open("aspects.db", "r")
local loaded = textutils.unserialize(file.readAll())
file.close()

print("Database loaded!")
```

## Troubleshooting

### Problem: Peripheral Not Found

**Symptoms:**
- `peripheral.find()` returns nil
- "No peripheral" error messages

**Solutions:**
1. Check computer is adjacent to block
2. Verify ThaumVibe mod is installed
3. Ensure block is a supported type
4. Try wrapping by side: `peripheral.wrap("right")`
5. Check wired modem connections

### Problem: Method Not Working

**Symptoms:**
- Method returns false
- No effect on block

**Solutions:**
1. Check Vis levels (use `getVis()`)
2. Verify research completed (use `hasResearch()`)
3. Ensure materials available
4. Check method parameters are correct
5. Add error handling to see specific failures

### Problem: Slow Performance

**Symptoms:**
- Scripts run slowly
- Long delays between operations

**Solutions:**
1. Reduce polling frequency
2. Use appropriate `sleep()` intervals
3. Don't scan unnecessarily
4. Cache peripheral references
5. Batch operations together

### Problem: Unexpected Results

**Symptoms:**
- Wrong aspects extracted
- Incorrect crafting results

**Solutions:**
1. Verify recipe names are correct
2. Check aspect spelling
3. Ensure proper item counts
4. Review operation order
5. Add debug logging

## FAQ

### Q: Does this require ComputerCraft: Tweaked?

A: ThaumVibe works with both ComputerCraft and ComputerCraft: Tweaked.

### Q: Can I control blocks remotely?

A: Yes, use wired modems to connect blocks over longer distances.

### Q: Do operations consume Vis?

A: Yes, crafting and infusion consume Vis from the blocks.

### Q: Can I query aspect compositions?

A: Yes, use `getAspectComponents(aspectName)` to get components.

### Q: Are there rate limits?

A: No explicit rate limits, but operations take realistic time.

### Q: Can multiple computers control one block?

A: Yes, but operations may conflict. Use coordination logic.

### Q: Does this work in multiplayer?

A: Yes, ThaumScript works in both single and multiplayer.

### Q: Can I backup/restore crucible state?

A: Not directly, but you can read and recreate the state programmatically.

### Q: What happens if Vis runs out?

A: Operations requiring Vis will fail. Vis regenerates at 1/tick.

### Q: Can I detect when infusion completes?

A: Yes, poll `getInfusionStatus()` until it returns "complete".

## Next Steps

### Learn More

- Read the [ComputerCraft API Documentation](COMPUTERCRAFT_API.md)
- Study the [ThaumScript Language Guide](THAUMSCRIPT_GUIDE.md)
- Browse the example scripts
- Join the community forums

### Build Projects

Try these project ideas:
1. **Vis Dashboard** - Monitor all nearby magical blocks
2. **Auto-Researcher** - Automatically complete research
3. **Essentia Manager** - Balance essentia across devices
4. **Recipe Calculator** - Compute aspect requirements
5. **Alert System** - Notify when processes complete

### Contribute

Share your scripts with the community:
- Submit to the ThaumVibe GitHub
- Post on the forums
- Create tutorials
- Report bugs or suggest features

## Resources

- **ThaumVibe GitHub**: https://github.com/vibecoding-inc/ThaumVibe
- **ComputerCraft Wiki**: https://computercraft.info/wiki/
- **Lua Documentation**: https://www.lua.org/manual/5.1/

## Support

Having issues? Get help:
- GitHub Issues for bug reports
- Discord server for questions
- Forums for discussions
- Wiki for documentation

---

Happy scripting, Thaumaturge! May your automation be ever efficient and your Vis forever abundant.
