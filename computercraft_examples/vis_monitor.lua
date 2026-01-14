-- Vis Level Monitor
-- This script monitors Vis levels and alerts when it's low.

local workbench = peripheral.find("arcane_workbench")
local monitor = peripheral.find("monitor")

if not workbench then
    print("Error: No arcane workbench found!")
    return
end

function updateDisplay()
    local vis = workbench.getVis()
    local maxVis = workbench.getMaxVis()
    local percent = (vis / maxVis) * 100
    
    if monitor then
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
    else
        term.clear()
        term.setCursorPos(1, 1)
        print("=== Vis Level Monitor ===")
        print("Current: " .. vis .. "/" .. maxVis)
        print("Percent: " .. math.floor(percent) .. "%")
        
        if percent < 20 then
            print("\nWARNING: Low Vis!")
        end
    end
end

print("Starting Vis Monitor...")
print("Press Ctrl+T to stop")

while true do
    updateDisplay()
    sleep(1)
end
