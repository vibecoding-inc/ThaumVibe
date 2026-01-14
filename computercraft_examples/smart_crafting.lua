-- Smart Arcane Crafting System
-- This script automatically crafts items when materials are available.

local workbench = peripheral.find("arcane_workbench")

if not workbench then
    print("Error: No arcane workbench found!")
    return
end

function craftIfPossible(recipe, visRequired)
    visRequired = visRequired or 25  -- Default Vis requirement
    
    local recipes = workbench.getAvailableRecipes()
    
    -- Check if recipe is available
    local available = false
    for _, r in ipairs(recipes) do
        if r == recipe then
            available = true
            break
        end
    end
    
    if not available then
        print("Recipe not available: " .. recipe)
        return false
    end
    
    -- Check Vis
    if not workbench.hasEnoughVis(visRequired) then
        print("Not enough Vis (need " .. visRequired .. ", have " .. workbench.getVis() .. ")")
        return false
    end
    
    -- Craft
    print("Crafting " .. recipe .. "...")
    if workbench.craftArcane(recipe) then
        print("Success!")
        return true
    else
        print("Failed to craft!")
        return false
    end
end

print("=== Smart Arcane Crafting System ===")

-- Try to craft a wand
craftIfPossible("thaumvibe:wand", 50)

-- Wait a bit
sleep(2)

-- Try to craft a thaumometer
craftIfPossible("thaumvibe:thaumometer", 30)
