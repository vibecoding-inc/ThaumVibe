-- Research Progress Checker
-- This script checks which research you have completed.

local peripheral_device = peripheral.wrap("right")

if not peripheral_device then
    print("Error: No peripheral found!")
    return
end

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

print("=== Research Progress Report ===")
print("================================")
print("")

for _, research in ipairs(researches) do
    local completed = peripheral_device.hasResearch(research)
    local status = completed and "[COMPLETE]" or "[INCOMPLETE]"
    print(status .. " " .. research)
end

print("")
print("Report complete!")
