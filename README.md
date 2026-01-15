# ThaumVibe
Vibecrafted Thaumcraft for MC 1.20.1

## About
ThaumVibe is a recreation of the classic Thaumcraft mod for modern Minecraft (1.20.1) using Forge. This mod brings back the magical experience of Thaumaturgy with aspects, research, and mystical crafting.

## ✨ NEW: Wand Spell System!

ThaumVibe now features an exciting combat spell system! Cast powerful spells using your wand to devastate enemies or support yourself. Choose from **10 unique spells** including:

- **Offensive:** Fireball, Zap, Ice Shard, Explosion, Void Vortex, Shockwave
- **Defensive:** Shield, Heal
- **Mobility:** Blink (Teleport), Leap

**See [SPELL_SYSTEM.md](SPELL_SYSTEM.md) for complete spell documentation and combat strategies!**

### Creative Mode Commands

ThaumVibe includes commands for creative mode or testing that allow you to bypass wand limitations:

- `/thaumvibe creative infinitevis` - Toggle infinite Vis for the held wand (no Vis consumption)
- `/thaumvibe creative nocooldown` - Toggle cooldown bypass for the held wand (cast spells instantly)
- `/thaumvibe creative all` - Toggle both infinite Vis and no cooldown modes at once

These commands require operator permissions (level 2) and only work on wands held in your main hand. The creative mode status is shown in the wand's tooltip.

## Core Thaumcraft Concepts

### Aspects System
Aspects are fundamental magical elements that make up all things. ThaumVibe includes:
- **Primal Aspects**: Aer (Air), Terra (Earth), Ignis (Fire), Aqua (Water), Ordo (Order), Perditio (Entropy)
- **Compound Aspects**: Combinations of primal aspects like Vacuos (Void), Lux (Light), Praecantatio (Magic), and more

### Vis (Magical Energy)
Vis is the magical energy that powers Thaumcraft devices and wands. It can be stored, channeled, and recharged.

### Research System
Players must research magical discoveries in the Thaumonomicon to unlock new items, blocks, and abilities.

## Game Progression

### Early Game
**Goal**: Discover magic and learn the basics of Thaumaturgy

**Key Items**:
- **Thaumonomicon**: Your magical research book
- **Thaumometer**: Scan objects to discover aspects
- **Wand**: Channel Vis energy for magical tasks
- **Salis Mundus**: Magical crafting component
- **Crystal Shards**: Air, Fire, Water, Earth, Order, Entropy - basic magical crystals found in the world

**Key Blocks**:
- **Crucible**: For alchemical transmutation
- **Research Table**: Research new discoveries
- **Arcane Workbench**: Craft magical items using Vis
- **Cinnabar Ore** & **Amber Ore**: Magical ores

**Activities**:
1. Find crystal shards in the world
2. Build a Crucible for basic alchemy
3. Construct a Research Table to start researching
4. Use the Thaumometer to scan everything and discover aspects
5. Craft your first Wand at the Arcane Workbench

### Middle Game
**Goal**: Master alchemy and infusion, create advanced magical items

**Key Items**:
- **Vis Crystal**: Concentrated magical energy
- **Alchemical Brass**: Advanced crafting component
- **Thaumium Ingot**: Magically-infused metal
- **Nitor**: Eternal magical flame
- **Quicksilver**: Liquid magic

**Key Blocks**:
- **Infusion Altar**: For complex magical infusions
- **Alchemical Furnace**: Advanced smelting with essentia
- **Essentia Smeltery**: Break down items into pure essentia
- **Shimmerleaf**: Magical plant that glows
- **Greatwood Log** & **Silverwood Log**: Magical trees

**Activities**:
1. Set up an Infusion Altar with pedestals
2. Smelt materials in the Alchemical Furnace
3. Extract essentia from items
4. Infuse items with magical properties
5. Find and cultivate magical trees

### Late Game
**Goal**: Delve into forbidden knowledge and master advanced Thaumaturgy

**Key Items**:
- **Void Metal Ingot**: Metal from the void
- **Primordial Pearl**: Condensed primal magic
- **Eldritch Eye**: Peer into forbidden realms
- **Sanity Checker**: Monitor your descent into forbidden knowledge

**Key Blocks**:
- **Eldritch Obelisk**: Connection to eldritch dimensions
- **Flux Scrubber**: Clean up magical pollution

**Activities**:
1. Research forbidden knowledge
2. Create void metal tools and armor
3. Build Eldritch Obelisks
4. Manage flux and magical pollution with Flux Scrubbers
5. Master all aspects of Thaumaturgy

## ComputerCraft Integration

ThaumVibe includes **ThaumScript**, a comprehensive scripting API for ComputerCraft that allows you to automate and control all ThaumVibe magical devices programmatically.

### Features
- **Automate Alchemy**: Control crucibles and essentia processing
- **Manage Vis**: Monitor and optimize magical energy usage
- **Craft Items**: Automate arcane workbench operations
- **Control Infusions**: Manage complex infusion rituals
- **Scan Blocks**: Discover aspects programmatically
- **Track Research**: Monitor research progress

### Quick Example

```lua
-- Connect to an Arcane Workbench
local workbench = peripheral.find("arcane_workbench")

-- Check Vis level
local vis = workbench.getVis()
print("Current Vis: " .. vis)

-- Craft a wand
if workbench.hasEnoughVis(50) then
    workbench.craftArcane("thaumvibe:wand")
end
```

### Documentation

- **[ComputerCraft API Reference](COMPUTERCRAFT_API.md)** - Complete API documentation
- **[ThaumScript Language Guide](THAUMSCRIPT_GUIDE.md)** - Language syntax and patterns
- **[Integration Guide](INTEGRATION_GUIDE.md)** - Tutorials and examples

## Building
This mod uses Forge for Minecraft 1.20.1

```bash
./gradlew build
```

## Installation
1. Install Minecraft 1.20.1
2. Install Forge 47.3.0+
3. Place the built JAR in your `mods` folder
4. (Optional) Install ComputerCraft for scripting support

## MCP Server

ThaumVibe includes a Model Context Protocol (MCP) server that allows AI assistants to access comprehensive information about the mod's mechanics, items, blocks, and progression.

**Features:**
- Query aspects, items, and blocks
- Get progression guides
- Understand research categories
- Learn crafting paths

**Quick Start:**
```bash
npm install
npm run build
npm start
```

For detailed setup and usage instructions, see:
- [MCP_SERVER.md](MCP_SERVER.md) - Installation and configuration
- [MCP_EXAMPLES.md](MCP_EXAMPLES.md) - Example usage and queries

## License
MIT
