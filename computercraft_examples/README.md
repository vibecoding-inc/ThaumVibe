# ThaumScript Example Scripts

This directory contains example Lua scripts for ComputerCraft that demonstrate how to use the ThaumScript API to control ThaumVibe magical devices.

## Prerequisites

- ComputerCraft mod installed
- ThaumVibe mod installed
- ComputerCraft computer placed adjacent to ThaumVibe blocks

## Example Scripts

### 1. auto_crucible.lua
**Purpose:** Automated crucible processing

Automatically adds items to a crucible and extracts the essentia results.

**Usage:**
```
> auto_crucible
```

**Requirements:**
- Crucible adjacent to computer
- Items to process (e.g., iron ingots)

---

### 2. vis_monitor.lua
**Purpose:** Monitor Vis levels

Displays current Vis levels and warns when low. Can output to a monitor or terminal.

**Usage:**
```
> vis_monitor
```

**Requirements:**
- Arcane Workbench adjacent to computer
- (Optional) ComputerCraft monitor for enhanced display

**Controls:**
- Press Ctrl+T to stop

---

### 3. aspect_scanner.lua
**Purpose:** Scan nearby blocks for aspects

Scans a 3-block radius around the peripheral and reports all aspects found.

**Usage:**
```
> aspect_scanner
```

**Requirements:**
- Any ThaumVibe peripheral on right side of computer

---

### 4. auto_infusion.lua
**Purpose:** Automated infusion management

Starts an infusion recipe and monitors its progress until completion or failure.

**Usage:**
```
> auto_infusion
```

**Requirements:**
- Infusion Altar adjacent to computer
- (Optional) Monitor for status display
- Proper infusion setup with pedestals and items

---

### 5. research_checker.lua
**Purpose:** Check research progress

Lists all major research entries and shows which ones are completed.

**Usage:**
```
> research_checker
```

**Requirements:**
- Any ThaumVibe peripheral adjacent to computer

---

### 6. smart_crafting.lua
**Purpose:** Automated arcane crafting

Intelligently crafts items when materials and Vis are available.

**Usage:**
```
> smart_crafting
```

**Requirements:**
- Arcane Workbench adjacent to computer
- Materials for crafting in adjacent chest
- Sufficient Vis

---

### 7. aspect_analyzer.lua
**Purpose:** Analyze aspect compositions

Shows all primal and compound aspects and their component breakdown.

**Usage:**
```
> aspect_analyzer
```

**Requirements:**
- Any ThaumVibe peripheral on right side of computer

## Installation

### Method 1: Manual Copy
1. Open ComputerCraft computer
2. Type: `edit scriptname`
3. Copy and paste the script content
4. Press Ctrl, select "Save and Exit"
5. Run with: `scriptname`

### Method 2: Download (if available)
If you have internet access in ComputerCraft:
```lua
shell.run("wget", "https://your-server.com/path/to/script.lua", "scriptname")
```

## Customization

All scripts can be customized by editing the configuration sections at the top of each file:

```lua
-- Configuration
local INPUT_SIDE = "left"
local OUTPUT_SIDE = "right"
local CHECK_INTERVAL = 5
```

## Troubleshooting

### "No peripheral found" error
- Ensure ThaumVibe block is adjacent to computer
- Check computer is turned on
- Verify ThaumVibe mod is installed

### "Recipe not available" error
- Check materials are in adjacent inventory
- Verify research is completed
- Ensure proper block setup

### "Not enough Vis" error
- Wait for Vis to regenerate (1 per tick)
- Check max Vis capacity with `getMaxVis()`
- Consider using multiple wands or vis sources

## Advanced Usage

### Combining Scripts

You can combine multiple scripts into more complex automation:

```lua
-- Load and run multiple scripts
shell.run("aspect_scanner")
shell.run("vis_monitor")
shell.run("auto_crucible")
```

### Parallel Execution

Use ComputerCraft's parallel API for simultaneous operations:

```lua
parallel.waitForAny(
    function() shell.run("vis_monitor") end,
    function() shell.run("auto_crucible") end
)
```

### Custom Automation

Build on these examples to create your own automation:

1. Start with a simple example
2. Add error handling
3. Customize for your needs
4. Test thoroughly
5. Share with the community!

## Documentation

For complete API documentation, see:
- [COMPUTERCRAFT_API.md](../COMPUTERCRAFT_API.md) - Full API reference
- [THAUMSCRIPT_GUIDE.md](../THAUMSCRIPT_GUIDE.md) - Language guide and patterns
- [INTEGRATION_GUIDE.md](../INTEGRATION_GUIDE.md) - Integration tutorials

## Contributing

Have a cool script to share? Submit it to the ThaumVibe repository!

1. Create your script
2. Test it thoroughly
3. Add comments explaining how it works
4. Submit a pull request

## Support

Need help? Check:
- [Integration Guide](../INTEGRATION_GUIDE.md) for tutorials
- GitHub Issues for bug reports
- Community forums for discussions

## License

These example scripts are provided under the MIT license, same as ThaumVibe.
Feel free to use, modify, and distribute them as you wish!
