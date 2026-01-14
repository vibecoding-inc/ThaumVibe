-- Aspect Scanner
-- This script scans nearby blocks and reports their aspects.

local peripheral_device = peripheral.wrap("right")

if not peripheral_device then
    print("Error: No peripheral found on right side!")
    return
end

function scanArea(radius)
    print("Scanning " .. radius .. " block radius...")
    local results = {}
    
    for x = -radius, radius do
        for y = -radius, radius do
            for z = -radius, radius do
                local aspects = peripheral_device.scan(x, y, z)
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

print("=== Aspect Scanner ===")
local scanResults = scanArea(3)
displayResults(scanResults)
print("\nScan complete!")
