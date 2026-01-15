# Wand Spell System

ThaumVibe now includes an exciting combat-focused spell system! Cast powerful spells using your magical wand.

## How to Use

1. **Craft a Wand** - The wand is your primary tool for casting spells
2. **Right-Click** - Cast the currently selected spell
3. **Shift + Right-Click** - Cycle through available spells
4. **Watch your Vis** - Each spell consumes Vis energy, which recharges over time

## Available Spells

### Offensive Spells

#### Fireball
- **Vis Cost:** 20
- **Cooldown:** 2 seconds
- **Description:** Launches a blazing fireball that explodes on impact
- **Effect:** Fire damage and small explosion

#### Zap
- **Vis Cost:** 30
- **Cooldown:** 3 seconds
- **Description:** Strikes the target location with lightning
- **Effect:** Direct lightning damage to target area

#### Ice Shard
- **Vis Cost:** 15
- **Cooldown:** 1.5 seconds
- **Description:** Fires an ice shard that freezes and damages enemies
- **Effect:** Magic damage + Slowness III + Freezing

#### Explosion
- **Vis Cost:** 40
- **Cooldown:** 5 seconds
- **Description:** Creates a massive explosion ahead of you
- **Effect:** Multiple TNT explosions with staggered timing

#### Void Vortex
- **Vis Cost:** 35
- **Cooldown:** 4 seconds
- **Description:** Creates a void vortex that pulls and damages enemies
- **Effect:** Pulls enemies in, deals damage, applies Weakness and Slowness

#### Shockwave
- **Vis Cost:** 30
- **Cooldown:** 3 seconds
- **Description:** Unleashes a shockwave that knocks back enemies
- **Effect:** Area knockback + damage + Slowness

### Defensive/Utility Spells

#### Shield
- **Vis Cost:** 20
- **Cooldown:** 4 seconds
- **Description:** Grants temporary damage resistance and absorption
- **Effect:** 5 seconds of Resistance II + Absorption I

#### Heal
- **Vis Cost:** 25
- **Cooldown:** 5 seconds
- **Description:** Restores health and grants brief regeneration
- **Effect:** Instant healing (3 hearts) + Regeneration II (2 seconds)

### Movement Spells

#### Blink (Teleport)
- **Vis Cost:** 25
- **Cooldown:** 2 seconds
- **Description:** Instantly teleports you forward 10 blocks
- **Effect:** Forward teleport + brief nausea + no fall damage

#### Leap
- **Vis Cost:** 15
- **Cooldown:** 1.5 seconds
- **Description:** Launches you into the air with magical force
- **Effect:** Forward and upward momentum + Slow Falling (3 seconds)

## Vis System

- **Maximum Vis:** 100
- **Recharge Rate:** 1 Vis per second
- **Recharge Type:** Automatic (passive)
- All wands in your inventory recharge simultaneously

## Visual Indicators

- The **durability bar** on your wand shows current Vis level
- **Enchantment glint** on wands indicates they are magical items
- **Action bar messages** display spell casting, Vis usage, and cooldowns

## Combat Strategies

### Close Combat
- **Shockwave** to knock enemies back
- **Shield** for protection
- **Leap** to escape dangerous situations

### Ranged Combat
- **Fireball** for consistent damage
- **Ice Shard** to slow approaching enemies
- **Zap** for precise strikes

### Area Control
- **Void Vortex** to group enemies
- **Explosion** for massive damage
- **Shockwave** to control space

### Mobility
- **Blink** for quick repositioning
- **Leap** for vertical movement and escapes
- Combine with combat spells for hit-and-run tactics

## Tips

1. **Manage your Vis** - Don't spam expensive spells, wait for recharge
2. **Combine spells** - Use Void Vortex to group enemies, then Explosion
3. **Use terrain** - Leap and Blink work great with the environment
4. **Cooldown awareness** - Different spells have different cooldowns
5. **Spell cycling** - Learn which spell comes next in the rotation

## Technical Details

- Spells are stored in the wand's NBT data
- Cooldowns are tracked per wand, not globally
- Particle effects provide visual feedback
- Sound effects enhance the magical experience
- All spells check for sufficient Vis before casting
