# Conversion from NeoForge to Forge API

## Summary

This document describes the conversion of ThaumVibe from NeoForge to the traditional MinecraftForge API.

## Key Changes Made

### 1. Minecraft Version Downgrade
- **From**: Minecraft 1.21.1 with NeoForge 21.1.73
- **To**: Minecraft 1.20.1 with Forge 47.3.0
- **Reason**: MinecraftForge only supports up to Minecraft 1.20.x. For 1.21+, NeoForge is the official successor.

### 2. Build Configuration Changes

#### build.gradle
- Replaced `net.neoforged.moddev` plugin with `net.minecraftforge.gradle` (ForgeGradle)
- Updated run configurations to use Forge-style setup
- Changed dependency from `neoforge` to `forge`
- Updated mappings to use Parchment for 1.20.1

#### gradle.properties
- Changed `neo_version` to `forge_version`
- Updated all version ranges for Forge compatibility
- Updated Parchment mappings version

#### settings.gradle
- Changed maven repository from `maven.neoforged.net` to `maven.minecraftforge.net`

### 3. Metadata Changes

#### META-INF/mods.toml (formerly neoforge.mods.toml)
- Renamed from `neoforge.mods.toml` to `mods.toml` (Forge standard)
- Changed dependency from `neoforge` to `forge`
- Updated version ranges for Forge

### 4. Code Changes

#### Main Mod Class (ThaumVibe.java)
```java
// Before (NeoForge)
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(ThaumVibe.MODID)
public class ThaumVibe {
    public ThaumVibe(IEventBus modEventBus) { ... }
}

// After (Forge)
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ThaumVibe.MODID)
public class ThaumVibe {
    public ThaumVibe() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ...
    }
}
```

#### Registry Classes (ModItems.java, ModBlocks.java, ModCreativeTabs.java)
```java
// Before (NeoForge)
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public static final DeferredHolder<Item, Item> ITEM = ...

// After (Forge)
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public static final RegistryObject<Item> ITEM = ...
```

**Key Differences:**
- `net.neoforged.*` → `net.minecraftforge.*`
- `DeferredHolder` → `RegistryObject`
- `Registries.*` → `ForgeRegistries.*`
- Constructor injection of event bus → Manual retrieval via `FMLJavaModLoadingContext`

### 5. What Stayed the Same

These vanilla Minecraft classes didn't need changes:
- Item classes (WandItem, ThaumometerItem)
- Block definitions
- API classes (Aspect, AspectList, Research, etc.)
- All vanilla imports (net.minecraft.*)
- Item.Properties usage
- Block.Properties usage
- CreativeModeTab builder pattern (compatible across both)

## Files Modified

1. `build.gradle` - Build configuration
2. `gradle.properties` - Version properties
3. `settings.gradle` - Plugin repositories
4. `src/main/resources/META-INF/mods.toml` - Mod metadata (renamed from neoforge.mods.toml)
5. `src/main/java/com/vibecoding/thaumvibe/ThaumVibe.java` - Main mod class
6. `src/main/java/com/vibecoding/thaumvibe/core/init/ModItems.java` - Item registration
7. `src/main/java/com/vibecoding/thaumvibe/core/init/ModBlocks.java` - Block registration
8. `src/main/java/com/vibecoding/thaumvibe/core/init/ModCreativeTabs.java` - Creative tab registration
9. `README.md` - Documentation updates

## Files Unchanged

All API and game logic files remained unchanged:
- `src/main/java/com/vibecoding/thaumvibe/api/aspects/Aspect.java`
- `src/main/java/com/vibecoding/thaumvibe/api/aspects/AspectList.java`
- `src/main/java/com/vibecoding/thaumvibe/api/research/*.java`
- `src/main/java/com/vibecoding/thaumvibe/api/vis/VisStorage.java`
- `src/main/java/com/vibecoding/thaumvibe/common/items/WandItem.java`
- `src/main/java/com/vibecoding/thaumvibe/common/items/ThaumometerItem.java`
- `src/main/resources/assets/thaumvibe/lang/en_us.json`

## Compatibility Notes

### Advantages of Forge (1.20.1)
- More stable and mature ecosystem
- Broader mod compatibility with existing 1.20.1 mods
- Well-established tooling and documentation
- Larger existing player base

### Limitations
- Cannot use Minecraft 1.21+ features
- Limited to Forge's 1.20.x feature set
- No access to newer vanilla content from 1.21+

## Build Instructions

```bash
./gradlew build
```

The mod will compile for Minecraft 1.20.1 with Forge 47.3.0+.

## Installation

1. Install Minecraft 1.20.1
2. Install Forge 47.3.0 or higher
3. Place the compiled JAR in your mods folder

## Future Considerations

If you want to target Minecraft 1.21+, you would need to use NeoForge instead of Forge, as Forge development stopped at 1.20.x. NeoForge is the official continuation of Forge for modern Minecraft versions.
