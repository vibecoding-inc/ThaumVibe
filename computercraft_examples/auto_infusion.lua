-- Automated Infusion Manager
-- This script automates the infusion process with status monitoring.

local altar = peripheral.find("infusion_altar")
local monitor = peripheral.find("monitor")

if not altar then
    print("Error: No infusion altar found!")
    return
end

function displayStatus(status, recipe)
    if monitor then
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
    print("Status: " .. status)
end

function performInfusion(recipe)
    print("Starting infusion: " .. recipe)
    displayStatus("Starting", recipe)
    
    if not altar.startInfusion(recipe) then
        print("Failed to start infusion!")
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

print("=== Automated Infusion Manager ===")
-- Perform infusion
performInfusion("thaumvibe:thaumium_ingot")
