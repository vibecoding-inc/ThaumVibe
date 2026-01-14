# ThaumVibe MCP Server - Example Usage

This document provides example interactions with the ThaumVibe MCP Server through an AI assistant.

## Setup

After installing and configuring the MCP server (see [MCP_SERVER.md](MCP_SERVER.md)), you can ask natural language questions to your AI assistant, and it will use the MCP tools to provide accurate information about ThaumVibe.

## Example Conversations

### Getting Started with ThaumVibe

**You:** "I'm new to ThaumVibe. What are the basic aspects I should know about?"

**AI Assistant** (uses `list_aspects` with type="primal"):
"ThaumVibe has 6 primal aspects that form the foundation of all magic:

1. **Aer (Air)** - Represents air, flight, and the sky
2. **Terra (Earth)** - Represents earth, stone, and the ground
3. **Ignis (Fire)** - Represents fire, heat, and destruction
4. **Aqua (Water)** - Represents water, fluidity, and life
5. **Ordo (Order)** - Represents structure, organization, and law
6. **Perditio (Entropy)** - Represents chaos, destruction, and decay

These primal aspects combine to form compound aspects like Magic (Praecantatio) and Void (Vacuos)."

---

### Understanding Specific Items

**You:** "Tell me about the Thaumometer. What is it and how do I use it?"

**AI Assistant** (uses `get_item_info` with item="thaumometer"):
"The **Thaumometer** is an essential early-game item in ThaumVibe. Here's what you need to know:

- **Description**: Scan objects to discover aspects and gain research points
- **Tier**: Early game
- **Uses**:
  - Right-click to scan blocks, items, and entities
  - Discover new aspects
  - Gain research progress

It's one of the first tools you'll want to create, as scanning everything is crucial for progressing through the mod!"

---

### Learning About Complex Aspects

**You:** "What is Praecantatio and how is it made?"

**AI Assistant** (uses `get_aspect_info` with aspect="praecantatio"):
"**Praecantatio (Magic)** is a compound aspect representing pure magical energy.

- **Color**: #CF00FF (purple)
- **Components**: It's made from combining:
  - Vacuos (Void) 
  - Potentia (Energy)
  
This makes it a second-tier compound aspect, since Vacuos itself is made from Aer + Perditio, and Potentia is made from Ordo + Ignis. Praecantatio is essential for many magical crafting recipes!"

---

### Progression Guidance

**You:** "I just started ThaumVibe. What should I do first?"

**AI Assistant** (uses `get_progression_guide` with stage="early"):
"Here's your early game progression path for ThaumVibe:

**Early Game - Discovery Phase**
*Objective*: Learn magic exists and gather basic materials

**Steps to follow:**
1. Find Crystal Shards in caves (Air, Fire, Water, Earth, Order, Entropy)
2. Build a Crucible (cauldron for alchemy)
3. Create your first Thaumometer
4. Scan everything to discover aspects
5. Build a Research Table
6. Craft Salis Mundus (magical crafting catalyst)

**What this unlocks:**
- Basic understanding of aspects
- Ability to scan and research

Then move on to the Establishment phase where you'll build your Arcane Workbench and craft your first Wand!"

---

### Finding Specific Blocks

**You:** "I need to set up an advanced crafting station. What are my options in the middle game?"

**AI Assistant** (uses `list_blocks` with tier="middle"):
"For middle game advanced crafting, ThaumVibe offers these blocks:

1. **Infusion Altar** - Multi-block structure for complex infusions (most powerful crafting method)
2. **Alchemical Furnace** - Advanced smelting with essentia extraction
3. **Essentia Smeltery** - Break down items into pure essentia
4. **Greatwood Log** - Better wood for wands with higher Vis capacity
5. **Silverwood Log** - Best wood for wands, repels magical pollution

The Infusion Altar is your main goal here - it requires pedestals around it and allows for the most powerful magical crafting!"

---

### Research System Inquiry

**You:** "How does research work in ThaumVibe? What categories are there?"

**AI Assistant** (uses `get_research_info` with category="all"):
"ThaumVibe's research system has 5 main categories:

1. **Basics** - Basic Thaumaturgy and getting started with magic
   - Learn to use the Thaumometer, create your first wand, and understand the fundamentals of aspects

2. **Alchemy** - Alchemical transmutation and essentia manipulation
   - Master the Crucible, Alchemical Furnace, and Essentia Smeltery to transmute materials

3. **Artifice** - Magical item creation, golems, and automation
   - Create magical items, build golems, and automate magical processes

4. **Thaumaturgy** - Advanced magical knowledge and complex spells
   - Unlock the Infusion Altar and access powerful magical crafting recipes

5. **Eldritch** - Forbidden knowledge (dangerous but powerful)
   - Research forbidden knowledge to unlock void metal, eldritch obelisks, and primordial crafting
   - ⚠️ WARNING: May have consequences

Research is done at the Research Table and tracked in your Thaumonomicon book!"

---

### Advanced Item Information

**You:** "What's the deal with Void Metal? Is it worth pursuing?"

**AI Assistant** (uses `get_item_info` with item="void_metal_ingot"):
"**Void Metal Ingot** is a late-game material and one of the most powerful in ThaumVibe:

- **Tier**: Late game
- **Description**: Metal from the void dimension
- **Uses**:
  - Craft the most powerful tools
  - Create void armor
  - Endgame material

It's definitely worth pursuing if you've mastered the basics and are ready for endgame content. However, obtaining Void Metal requires delving into Eldritch research, which can have consequences for your character (sanity effects). Make sure you have a Sanity Checker and are prepared for the challenges!"

---

### Building a Workshop

**You:** "I'm setting up my magical workshop. What blocks should I prioritize?"

**AI Assistant** (uses `list_blocks` with tier="early" and then provides advice):
"For your early-game magical workshop, prioritize these essential blocks:

**Must-have stations:**
1. **Crucible** - Your alchemical cauldron for transmutation (place over fire)
2. **Research Table** - Complete research to unlock new recipes
3. **Arcane Workbench** - Craft magical items using Vis from your wand

**Resource blocks to find:**
4. **Cinnabar Ore** - Mine this for Quicksilver
5. **Amber Ore** - Contains preserved magical essence

**Recommended setup order:**
1. Start with the Crucible for basic alchemy
2. Build the Research Table to begin unlocking recipes
3. Create an Arcane Workbench (requires Salis Mundus)
4. Mine ores as you explore

This setup will serve you well through the early game until you're ready for the Infusion Altar!"

---

### Comparing Items

**You:** "Should I use Thaumium or regular diamond tools?"

**AI Assistant** (uses `get_item_info` with item="thaumium_ingot"):
"**Thaumium tools** are a middle-game upgrade that have some interesting properties:

Thaumium is a magically-infused metal that's stronger than iron and has advantages over diamond in certain aspects:
- Better for magical enchantments
- Can be repaired more easily with magic
- Required for many advanced magical recipes

While diamond tools might have higher durability, Thaumium tools integrate better with ThaumVibe's magical systems and are easier to repair using magical means. Many experienced Thaumaturges prefer Thaumium for their everyday tools!"

---

## Tips for Using the MCP Server

1. **Be specific**: Instead of "tell me about magic," ask "what are the primal aspects?"
2. **Ask follow-up questions**: The AI can drill down into specific items or blocks
3. **Request progression help**: Ask about specific game stages for targeted advice
4. **Explore connections**: Ask how different aspects combine or what items are needed for specific goals

## Common Questions

### "What's the first thing I should craft?"
Ask: "What should I do first in ThaumVibe?" or "Show me the early game progression"

### "How do I make a specific item?"
Ask: "Tell me about [item name]" or "What is [item name] used for?"

### "I'm stuck at [stage], what's next?"
Ask: "What's the middle game progression?" or "Show me late game items"

### "What aspects do I need for [purpose]?"
Ask: "What is [aspect name] made of?" or "List all compound aspects"

## Advanced Usage

The AI assistant can combine multiple tool calls to answer complex questions:

**You:** "I want to understand the complete path from early to late game magic"

**AI Assistant** (uses multiple `get_progression_guide` calls and synthesizes):
[Provides comprehensive progression guide across all tiers with specific recommendations]

---

For technical details about the MCP server tools and setup, see [MCP_SERVER.md](MCP_SERVER.md).
