# ThaumScript Language Guide

## Overview

ThaumScript is the scripting interface for controlling ThaumVibe magical devices through ComputerCraft. While it uses Lua as the underlying language (via ComputerCraft), ThaumScript provides a domain-specific API for magical automation.

## Language Philosophy

ThaumScript is designed around three core principles:

1. **Magical Abstraction** - Hide complexity behind intuitive magical concepts
2. **Safe Automation** - Prevent dangerous magical operations through validation
3. **Progressive Disclosure** - Simple tasks are simple, complex tasks are possible

## Syntax Fundamentals

### Basic Structure

ThaumScript programs follow standard Lua syntax with ThaumVibe-specific peripheral calls:

```lua
-- Connect to a device
local device = peripheral.find("peripheral_type")

-- Call methods on the device
local result = device.methodName(arguments)

-- Handle results
if result then
    -- Success path
else
    -- Error handling
end
```

### Naming Conventions

ThaumScript follows these naming conventions:

- **Peripheral Types**: `snake_case` (e.g., `arcane_workbench`, `infusion_altar`)
- **Method Names**: `camelCase` (e.g., `getVis`, `startInfusion`)
- **Aspect Names**: `lowercase` (e.g., `aer`, `terra`, `praecantatio`)
- **Constants**: `UPPER_CASE` (e.g., `MAX_VIS`, `PRIMAL_ASPECTS`)

### Data Types

ThaumScript uses Lua's dynamic typing with magical semantic meanings:

```lua
-- Numbers represent quantities
local visAmount = 50
local aspectCount = 10

-- Strings represent identifiers
local aspectName = "ignis"
local recipeName = "thaumvibe:wand"

-- Tables represent collections
local aspects = {aer = 5, terra = 3, ignis = 2}
local recipes = {"wand", "thaumometer", "goggles"}

-- Booleans represent states
local hasVis = true
local isComplete = false
```

## Core Language Constructs

### 1. Device Connection

Connect to ThaumVibe devices using peripheral methods:

```lua
-- Find a specific peripheral type
local crucible = peripheral.find("crucible")

-- Wrap a peripheral on a specific side
local altar = peripheral.wrap("right")

-- Get all peripheral names
local devices = peripheral.getNames()

-- Find all peripherals of a type
local furnaces = {peripheral.find("alchemical_furnace")}
```

### 2. Aspect Manipulation

Aspects are the fundamental building blocks:

```lua
-- Get aspects from a device
local aspects = device.getAspects()

-- Iterate over aspects
for aspect, amount in pairs(aspects) do
    print(aspect .. ": " .. amount)
end

-- Check for specific aspect
if aspects["ignis"] and aspects["ignis"] > 10 then
    print("Enough fire essence")
end

-- Get primal aspects
local primals = device.getPrimalAspects()
-- Returns: {"aer", "terra", "ignis", "aqua", "ordo", "perditio"}

-- Get compound aspects
local compounds = device.getCompoundAspects()
```

### 3. Vis Management

Vis is magical energy - manage it carefully:

```lua
-- Check current Vis level
local current = device.getVis()
local maximum = device.getMaxVis()

-- Calculate percentage
local percentage = (current / maximum) * 100

-- Wait for Vis to regenerate
function waitForVis(device, required)
    while device.getVis() < required do
        sleep(1)  -- Vis regenerates at 1 per tick
    end
end

-- Safe operation wrapper
function safeOperation(device, visRequired, operation)
    if device.hasEnoughVis(visRequired) then
        return operation()
    else
        print("Insufficient Vis")
        return false
    end
end
```

### 4. Research Checks

Operations may require research completion:

```lua
-- Check single research
if device.hasResearch("CRUCIBLE") then
    print("Can use crucible")
end

-- Check multiple prerequisites
function hasPrerequisites(device, researches)
    for _, research in ipairs(researches) do
        if not device.hasResearch(research) then
            return false
        end
    end
    return true
end

-- Use it
local required = {"BASICS", "ALCHEMY", "CRUCIBLE"}
if hasPrerequisites(device, required) then
    -- Perform operation
end
```

### 5. Crafting Operations

Crafting uses Vis and may require research:

```lua
-- Simple craft
workbench.craftArcane("thaumvibe:wand")

-- Safe crafting
function safeCraft(workbench, recipe, visRequired)
    -- Check research
    if not workbench.hasResearch(recipe) then
        return false, "Research not completed"
    end
    
    -- Check Vis
    if not workbench.hasEnoughVis(visRequired) then
        return false, "Insufficient Vis"
    end
    
    -- Check recipe availability
    local recipes = workbench.getAvailableRecipes()
    local available = false
    for _, r in ipairs(recipes) do
        if r == recipe then
            available = true
            break
        end
    end
    
    if not available then
        return false, "Materials not available"
    end
    
    -- Perform craft
    return workbench.craftArcane(recipe), "Success"
end
```

### 6. Crucible Processing

Crucibles transform items into essentia:

```lua
-- Add items to crucible
crucible.addToCrucible("minecraft:iron_ingot", 5)

-- Wait for processing
sleep(5)

-- Check results
local essentia = crucible.getCrucibleEssentia()

-- Extract specific aspect
if essentia["metallum"] and essentia["metallum"] >= 10 then
    crucible.extractFromCrucible("metallum")
end

-- Complete processing function
function processInCrucible(crucible, item, count, targetAspect, targetAmount)
    -- Add items
    if not crucible.addToCrucible(item, count) then
        return false, "Failed to add items"
    end
    
    -- Wait for processing
    local maxWait = 30
    local waited = 0
    
    while waited < maxWait do
        local essentia = crucible.getCrucibleEssentia()
        if essentia[targetAspect] and essentia[targetAspect] >= targetAmount then
            crucible.extractFromCrucible(targetAspect)
            return true, "Success"
        end
        sleep(1)
        waited = waited + 1
    end
    
    return false, "Timeout"
end
```

### 7. Infusion Management

Infusion is complex and requires monitoring:

```lua
-- Start infusion
altar.startInfusion("thaumvibe:thaumium_ingot")

-- Monitor status
function monitorInfusion(altar)
    while true do
        local status = altar.getInfusionStatus()
        
        if status == "complete" then
            return true, "Infusion complete"
        elseif status == "failed" then
            return false, "Infusion failed"
        elseif status == "infusing" then
            print("Infusing...")
        end
        
        sleep(2)
    end
end

-- Complete infusion function
function performInfusion(altar, recipe)
    if not altar.startInfusion(recipe) then
        return false, "Failed to start"
    end
    
    return monitorInfusion(altar)
end
```

### 8. Scanning and Discovery

Scan the world to discover aspects:

```lua
-- Scan single block
local aspects = device.scan(0, 1, 0)  -- Above

-- Scan area
function scanArea(device, radius)
    local results = {}
    
    for x = -radius, radius do
        for y = -radius, radius do
            for z = -radius, radius do
                local aspects = device.scan(x, y, z)
                if next(aspects) ~= nil then
                    local key = x .. "," .. y .. "," .. z
                    results[key] = aspects
                end
            end
        end
    end
    
    return results
end

-- Find blocks with specific aspect
function findAspect(device, radius, targetAspect, minAmount)
    local positions = {}
    
    for x = -radius, radius do
        for y = -radius, radius do
            for z = -radius, radius do
                local aspects = device.scan(x, y, z)
                if aspects[targetAspect] and aspects[targetAspect] >= minAmount then
                    table.insert(positions, {x=x, y=y, z=z})
                end
            end
        end
    end
    
    return positions
end
```

## Advanced Patterns

### 1. State Machine Pattern

For complex multi-step processes:

```lua
local State = {
    IDLE = "idle",
    PROCESSING = "processing",
    WAITING = "waiting",
    COMPLETE = "complete",
    ERROR = "error"
}

local machine = {
    state = State.IDLE,
    device = nil
}

function machine:setState(newState)
    print("State: " .. self.state .. " -> " .. newState)
    self.state = newState
end

function machine:process()
    if self.state == State.IDLE then
        -- Start processing
        self:setState(State.PROCESSING)
    elseif self.state == State.PROCESSING then
        -- Check completion
        if self:isComplete() then
            self:setState(State.COMPLETE)
        end
    end
end
```

### 2. Event-Driven Pattern

React to changes instead of polling:

```lua
function onVisChanged(device, threshold, callback)
    local lastVis = device.getVis()
    
    while true do
        local currentVis = device.getVis()
        
        if math.abs(currentVis - lastVis) >= threshold then
            callback(currentVis, lastVis)
            lastVis = currentVis
        end
        
        sleep(0.5)
    end
end

-- Use it
onVisChanged(workbench, 10, function(current, previous)
    print("Vis changed: " .. previous .. " -> " .. current)
end)
```

### 3. Pipeline Pattern

Chain operations together:

```lua
local Pipeline = {}

function Pipeline:new()
    local o = {steps = {}}
    setmetatable(o, self)
    self.__index = self
    return o
end

function Pipeline:add(step)
    table.insert(self.steps, step)
    return self
end

function Pipeline:execute(context)
    for i, step in ipairs(self.steps) do
        local success, result = step(context)
        if not success then
            return false, "Step " .. i .. " failed: " .. result
        end
        context = result
    end
    return true, context
end

-- Use it
local pipeline = Pipeline:new()
    :add(function(ctx) return true, ctx.device.addToCrucible("item", 5) end)
    :add(function(ctx) sleep(5); return true, ctx end)
    :add(function(ctx) return true, ctx.device.extractFromCrucible("aspect") end)

pipeline:execute({device = crucible})
```

### 4. Resource Pool Pattern

Manage multiple devices:

```lua
local ResourcePool = {}

function ResourcePool:new(deviceType)
    local o = {
        type = deviceType,
        devices = {peripheral.find(deviceType)},
        busy = {}
    }
    setmetatable(o, self)
    self.__index = self
    return o
end

function ResourcePool:acquire()
    for i, device in ipairs(self.devices) do
        if not self.busy[i] then
            self.busy[i] = true
            return device, i
        end
    end
    return nil, nil
end

function ResourcePool:release(index)
    self.busy[index] = false
end

-- Use it
local pool = ResourcePool:new("crucible")
local crucible, id = pool:acquire()
if crucible then
    -- Use crucible
    crucible.addToCrucible("item", 1)
    pool:release(id)
end
```

### 5. Retry Pattern

Handle transient failures:

```lua
function retry(operation, maxAttempts, delay)
    for attempt = 1, maxAttempts do
        local success, result = operation()
        
        if success then
            return true, result
        end
        
        if attempt < maxAttempts then
            print("Attempt " .. attempt .. " failed, retrying...")
            sleep(delay)
        end
    end
    
    return false, "Max retries exceeded"
end

-- Use it
local success = retry(function()
    return crucible.addToCrucible("item", 1)
end, 3, 2)
```

## Best Practices

### 1. Error Handling

Always handle errors gracefully:

```lua
-- Bad
device.craftArcane("recipe")

-- Good
local success = device.craftArcane("recipe")
if not success then
    print("Crafting failed")
    return
end

-- Better
local function safeCraft(device, recipe)
    local success, error = pcall(function()
        return device.craftArcane(recipe)
    end)
    
    if not success then
        print("Error: " .. tostring(error))
        return false
    end
    
    return success
end
```

### 2. Resource Management

Clean up resources properly:

```lua
local function withDevice(deviceType, operation)
    local device = peripheral.find(deviceType)
    
    if not device then
        return false, "Device not found"
    end
    
    local success, result = pcall(operation, device)
    
    -- Cleanup if needed
    
    return success, result
end
```

### 3. Configuration

Use configuration tables:

```lua
local config = {
    crucible = {
        maxWaitTime = 30,
        checkInterval = 1
    },
    workbench = {
        minVis = 25,
        craftDelay = 2
    }
}

function processWithConfig(device, cfg)
    -- Use cfg.maxWaitTime, etc.
end
```

### 4. Logging

Implement proper logging:

```lua
local LogLevel = {
    DEBUG = 1,
    INFO = 2,
    WARN = 3,
    ERROR = 4
}

local logger = {
    level = LogLevel.INFO
}

function logger:log(level, message)
    if level >= self.level then
        local prefix = {"DEBUG", "INFO", "WARN", "ERROR"}[level]
        print("[" .. prefix .. "] " .. message)
    end
end

-- Use it
logger:log(LogLevel.INFO, "Starting process")
logger:log(LogLevel.ERROR, "Operation failed")
```

### 5. Testing

Write testable code:

```lua
-- Mock for testing
local function createMockDevice()
    return {
        getVis = function() return 100 end,
        craftArcane = function() return true end
    }
end

-- Test function
function testCrafting()
    local mock = createMockDevice()
    local result = safeCraft(mock, "recipe")
    assert(result, "Crafting should succeed")
end
```

## Common Patterns

### Aspect Calculator

```lua
function calculateAspects(items)
    local total = {}
    
    for _, item in ipairs(items) do
        local aspects = item.aspects
        for aspect, amount in pairs(aspects) do
            total[aspect] = (total[aspect] or 0) + amount
        end
    end
    
    return total
end
```

### Vis Optimizer

```lua
function optimizeVisUsage(operations, availableVis)
    table.sort(operations, function(a, b)
        return a.priority > b.priority
    end)
    
    local scheduled = {}
    local remaining = availableVis
    
    for _, op in ipairs(operations) do
        if op.visRequired <= remaining then
            table.insert(scheduled, op)
            remaining = remaining - op.visRequired
        end
    end
    
    return scheduled
end
```

### Recipe Finder

```lua
function findCraftableRecipes(workbench, minVis)
    local available = workbench.getAvailableRecipes()
    local craftable = {}
    
    for _, recipe in ipairs(available) do
        if workbench.hasEnoughVis(minVis) then
            table.insert(craftable, recipe)
        end
    end
    
    return craftable
end
```

## Performance Tips

1. **Batch Operations**: Group multiple calls together
2. **Cache Results**: Store peripheral references
3. **Reduce Polling**: Use appropriate sleep intervals
4. **Lazy Evaluation**: Only compute when needed
5. **Async Where Possible**: Use parallel peripheral operations

## Debugging

### Debug Print Helper

```lua
function debugPrint(name, value)
    if type(value) == "table" then
        print(name .. ":")
        for k, v in pairs(value) do
            print("  " .. k .. ": " .. tostring(v))
        end
    else
        print(name .. ": " .. tostring(value))
    end
end
```

### State Inspector

```lua
function inspectDevice(device)
    print("Device Type: " .. device.getType())
    print("Vis: " .. device.getVis() .. "/" .. device.getMaxVis())
    debugPrint("Aspects", device.getAspects())
end
```

## Conclusion

ThaumScript provides a powerful yet accessible way to automate ThaumVibe's magical systems. Master these patterns and practices to create sophisticated magical automation systems.
