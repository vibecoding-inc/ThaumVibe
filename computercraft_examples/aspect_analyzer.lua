-- Aspect Composition Analyzer
-- This script analyzes and reports aspect compositions.

local peripheral_device = peripheral.wrap("right")

if not peripheral_device then
    print("Error: No peripheral found!")
    return
end

function analyzeAspect(aspectName)
    print("\nAnalyzing: " .. aspectName)
    
    local components = peripheral_device.getAspectComponents(aspectName)
    
    if #components == 0 then
        print("  Type: Primal Aspect")
        print("  Cannot be broken down further")
    else
        print("  Type: Compound Aspect")
        print("  Components:")
        for _, component in ipairs(components) do
            print("    - " .. component)
        end
    end
end

print("=== Aspect Composition Analyzer ===")
print("")

-- Analyze primal aspects
print("PRIMAL ASPECTS:")
local primals = peripheral_device.getPrimalAspects()
for _, aspect in ipairs(primals) do
    print("  - " .. aspect)
end

print("\nCOMPOUND ASPECTS:")

-- Analyze all compound aspects
local compounds = peripheral_device.getCompoundAspects()
for _, aspect in ipairs(compounds) do
    analyzeAspect(aspect)
end

print("\nAnalysis complete!")
