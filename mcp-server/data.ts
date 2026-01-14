// ThaumVibe game data for MCP server

export interface Aspect {
  tag: string;
  name: string;
  color: string;
  isPrimal: boolean;
  components?: string[];
  description: string;
}

export interface Item {
  id: string;
  name: string;
  tier: string;
  description: string;
  uses: string[];
}

export interface Block {
  id: string;
  name: string;
  tier: string;
  description: string;
  uses: string[];
}

export interface ResearchCategory {
  id: string;
  name: string;
  description: string;
}

export interface Research {
  category: string;
  description: string;
}

export interface Progression {
  stage: string;
  title: string;
  objective: string;
  steps: string[];
  unlocks: string[];
  warnings?: string[];
}

export interface ThaumVibeData {
  aspects: Aspect[];
  items: Item[];
  blocks: Block[];
  researchCategories: ResearchCategory[];
  research: Research[];
  progression: Progression[];
}

export const thaumVibeData: ThaumVibeData = {
  aspects: [
    // Primal Aspects
    {
      tag: "aer",
      name: "Air",
      color: "#FFFFE5",
      isPrimal: true,
      description: "Represents air, flight, and the sky",
    },
    {
      tag: "terra",
      name: "Earth",
      color: "#00FF00",
      isPrimal: true,
      description: "Represents earth, stone, and the ground",
    },
    {
      tag: "ignis",
      name: "Fire",
      color: "#FF5555",
      isPrimal: true,
      description: "Represents fire, heat, and destruction",
    },
    {
      tag: "aqua",
      name: "Water",
      color: "#5555FF",
      isPrimal: true,
      description: "Represents water, fluidity, and life",
    },
    {
      tag: "ordo",
      name: "Order",
      color: "#AAAAFF",
      isPrimal: true,
      description: "Represents structure, organization, and law",
    },
    {
      tag: "perditio",
      name: "Entropy",
      color: "#404040",
      isPrimal: true,
      description: "Represents chaos, destruction, and decay",
    },
    // Compound Aspects
    {
      tag: "vacuos",
      name: "Void",
      color: "#888888",
      isPrimal: false,
      components: ["aer", "perditio"],
      description: "Emptiness and vacuum",
    },
    {
      tag: "lux",
      name: "Light",
      color: "#FFFFC0",
      isPrimal: false,
      components: ["aer", "ignis"],
      description: "Light and illumination",
    },
    {
      tag: "motus",
      name: "Motion",
      color: "#CDCDFF",
      isPrimal: false,
      components: ["aer", "ordo"],
      description: "Movement and change",
    },
    {
      tag: "gelum",
      name: "Ice",
      color: "#E0FFFF",
      isPrimal: false,
      components: ["ignis", "perditio"],
      description: "Cold and freezing",
    },
    {
      tag: "vitreus",
      name: "Crystal",
      color: "#80FFFF",
      isPrimal: false,
      components: ["terra", "ordo"],
      description: "Glass and crystals",
    },
    {
      tag: "metallum",
      name: "Metal",
      color: "#B5B5CD",
      isPrimal: false,
      components: ["terra", "ordo"],
      description: "Metals and ores",
    },
    {
      tag: "victus",
      name: "Life",
      color: "#DE0005",
      isPrimal: false,
      components: ["aqua", "terra"],
      description: "Living things",
    },
    {
      tag: "mortuus",
      name: "Death",
      color: "#6A0005",
      isPrimal: false,
      components: ["aqua", "perditio"],
      description: "Death and undeath",
    },
    {
      tag: "potentia",
      name: "Energy",
      color: "#C0FFFF",
      isPrimal: false,
      components: ["ordo", "ignis"],
      description: "Power and energy",
    },
    {
      tag: "praecantatio",
      name: "Magic",
      color: "#CF00FF",
      isPrimal: false,
      components: ["vacuos", "potentia"],
      description: "Pure magical energy",
    },
  ],

  items: [
    // Early Game Items
    {
      id: "thaumonomicon",
      name: "Thaumonomicon",
      tier: "early",
      description: "Your magical research book and encyclopedia",
      uses: ["Track all research progress", "Learn about discovered aspects", "View unlocked recipes"],
    },
    {
      id: "thaumometer",
      name: "Thaumometer",
      tier: "early",
      description: "Scan objects to discover aspects and gain research points",
      uses: ["Right-click to scan blocks, items, and entities", "Discover new aspects", "Gain research progress"],
    },
    {
      id: "wand",
      name: "Wand",
      tier: "early",
      description: "Stores and channels Vis for magical tasks",
      uses: ["Store up to 100 Vis", "Recharges at 1 Vis per tick", "Required for Arcane Workbench crafting"],
    },
    {
      id: "salis_mundus",
      name: "Salis Mundus",
      tier: "early",
      description: "Magic salt used to activate magical blocks",
      uses: ["Activate Arcane Workbench", "Create magical structures", "Essential crafting component"],
    },
    {
      id: "crystal_shard_air",
      name: "Air Crystal Shard",
      tier: "early",
      description: "Crystal containing Aer aspect",
      uses: ["Found naturally in caves", "Source of Air aspect", "Basic magical material"],
    },
    {
      id: "crystal_shard_fire",
      name: "Fire Crystal Shard",
      tier: "early",
      description: "Crystal containing Ignis aspect",
      uses: ["Found naturally in caves", "Source of Fire aspect", "Basic magical material"],
    },
    {
      id: "crystal_shard_water",
      name: "Water Crystal Shard",
      tier: "early",
      description: "Crystal containing Aqua aspect",
      uses: ["Found naturally in caves", "Source of Water aspect", "Basic magical material"],
    },
    {
      id: "crystal_shard_earth",
      name: "Earth Crystal Shard",
      tier: "early",
      description: "Crystal containing Terra aspect",
      uses: ["Found naturally in caves", "Source of Earth aspect", "Basic magical material"],
    },
    {
      id: "crystal_shard_order",
      name: "Order Crystal Shard",
      tier: "early",
      description: "Crystal containing Ordo aspect",
      uses: ["Found naturally in caves", "Source of Order aspect", "Basic magical material"],
    },
    {
      id: "crystal_shard_entropy",
      name: "Entropy Crystal Shard",
      tier: "early",
      description: "Crystal containing Perditio aspect",
      uses: ["Found naturally in caves", "Source of Entropy aspect", "Basic magical material"],
    },
    // Middle Game Items
    {
      id: "vis_crystal",
      name: "Vis Crystal",
      tier: "middle",
      description: "Concentrated magical energy storage",
      uses: ["Store large amounts of Vis", "Power complex devices", "Advanced crafting component"],
    },
    {
      id: "alchemical_brass",
      name: "Alchemical Brass",
      tier: "middle",
      description: "Magical alloy for advanced crafting",
      uses: ["Create advanced magical devices", "Required for Golem cores", "Intermediate crafting material"],
    },
    {
      id: "thaumium_ingot",
      name: "Thaumium Ingot",
      tier: "middle",
      description: "Magically-infused metal stronger than iron",
      uses: ["Craft Thaumium tools and armor", "Better than diamond in some aspects", "Advanced crafting material"],
    },
    {
      id: "nitor",
      name: "Nitor",
      tier: "middle",
      description: "Eternal magical flame",
      uses: ["Permanent light source", "Never burns out", "Used in advanced recipes"],
    },
    {
      id: "quicksilver",
      name: "Quicksilver",
      tier: "middle",
      description: "Liquid magic essence",
      uses: ["Advanced alchemy ingredient", "Mirror creation", "Infusion catalyst"],
    },
    // Late Game Items
    {
      id: "void_metal_ingot",
      name: "Void Metal Ingot",
      tier: "late",
      description: "Metal from the void dimension",
      uses: ["Craft the most powerful tools", "Create void armor", "Endgame material"],
    },
    {
      id: "primordial_pearl",
      name: "Primordial Pearl",
      tier: "late",
      description: "Condensed primal magic",
      uses: ["Most powerful infusion catalyst", "Create primordial items", "Extremely rare"],
    },
    {
      id: "eldritch_eye",
      name: "Eldritch Eye",
      tier: "late",
      description: "Peer into forbidden realms",
      uses: ["Reveal hidden blocks", "Access eldritch dimensions", "See through walls"],
    },
    {
      id: "sanity_checker",
      name: "Sanity Checker",
      tier: "late",
      description: "Monitor your descent into forbidden knowledge",
      uses: ["Track warp levels", "Monitor sanity", "Prevent mental degradation"],
    },
  ],

  blocks: [
    // Early Game Blocks
    {
      id: "crucible",
      name: "Crucible",
      tier: "early",
      description: "Alchemical cauldron for transmutation",
      uses: ["Transform items using essentia", "Basic alchemy", "Heat with fire underneath"],
    },
    {
      id: "research_table",
      name: "Research Table",
      tier: "early",
      description: "Where you complete research",
      uses: ["Unlock new recipes", "Combine aspects", "Progress through the Thaumonomicon"],
    },
    {
      id: "arcane_workbench",
      name: "Arcane Workbench",
      tier: "early",
      description: "Crafting table that uses Vis",
      uses: ["Craft magical items", "Requires Wand with Vis", "Enhanced crafting"],
    },
    {
      id: "cinnabar_ore",
      name: "Cinnabar Ore",
      tier: "early",
      description: "Magical ore that yields Quicksilver",
      uses: ["Source of Quicksilver", "Found in the world", "Mine with iron pickaxe or better"],
    },
    {
      id: "amber_ore",
      name: "Amber Ore",
      tier: "early",
      description: "Ore containing preserved magical essence",
      uses: ["Source of magical materials", "Contains preserved creatures", "Rare ore"],
    },
    // Middle Game Blocks
    {
      id: "infusion_altar",
      name: "Infusion Altar",
      tier: "middle",
      description: "Multi-block structure for complex infusions",
      uses: ["Advanced magical crafting", "Requires pedestals", "Most powerful crafting method"],
    },
    {
      id: "alchemical_furnace",
      name: "Alchemical Furnace",
      tier: "middle",
      description: "Advanced smelting with essentia extraction",
      uses: ["Smelt while extracting essentia", "More efficient than normal furnace", "Aspect preservation"],
    },
    {
      id: "essentia_smeltery",
      name: "Essentia Smeltery",
      tier: "middle",
      description: "Break down items into pure essentia",
      uses: ["Extract pure aspects", "Convert items to essentia", "Feed into jars"],
    },
    {
      id: "shimmerleaf",
      name: "Shimmerleaf",
      tier: "middle",
      description: "Glowing magical plant",
      uses: ["Produces Quicksilver", "Glows in the dark", "Grows on Silverwood trees"],
    },
    {
      id: "greatwood_log",
      name: "Greatwood Log",
      tier: "middle",
      description: "Large magical trees",
      uses: ["Better than normal wood for wands", "Higher Vis capacity", "Found in magical forests"],
    },
    {
      id: "silverwood_log",
      name: "Silverwood Log",
      tier: "middle",
      description: "Pure magical trees that repel flux",
      uses: ["Best wood for wands", "Repels magical pollution", "Very rare"],
    },
    // Late Game Blocks
    {
      id: "eldritch_obelisk",
      name: "Eldritch Obelisk",
      tier: "late",
      description: "Connection to eldritch dimensions",
      uses: ["Access forbidden realms", "Summon eldritch entities", "Dangerous but powerful"],
    },
    {
      id: "flux_scrubber",
      name: "Flux Scrubber",
      tier: "late",
      description: "Cleans up magical pollution",
      uses: ["Remove flux from area", "Prevent corruption", "Essential for large workshops"],
    },
  ],

  researchCategories: [
    {
      id: "basics",
      name: "Basics",
      description: "Basic Thaumaturgy and getting started with magic",
    },
    {
      id: "alchemy",
      name: "Alchemy",
      description: "Alchemical transmutation and essentia manipulation",
    },
    {
      id: "artifice",
      name: "Artifice",
      description: "Magical item creation, golems, and automation",
    },
    {
      id: "thaumaturgy",
      name: "Thaumaturgy",
      description: "Advanced magical knowledge and complex spells",
    },
    {
      id: "eldritch",
      name: "Eldritch",
      description: "Forbidden knowledge - dangerous but powerful",
    },
  ],

  research: [
    {
      category: "basics",
      description: "Learn to use the Thaumometer, create your first wand, and understand the fundamentals of aspects",
    },
    {
      category: "alchemy",
      description: "Master the Crucible, Alchemical Furnace, and Essentia Smeltery to transmute materials",
    },
    {
      category: "artifice",
      description: "Create magical items, build golems, and automate magical processes",
    },
    {
      category: "thaumaturgy",
      description: "Unlock the Infusion Altar and access powerful magical crafting recipes",
    },
    {
      category: "eldritch",
      description: "Research forbidden knowledge to unlock void metal, eldritch obelisks, and primordial crafting. WARNING: May have consequences",
    },
  ],

  progression: [
    {
      stage: "early",
      title: "Discovery",
      objective: "Learn magic exists and gather basic materials",
      steps: [
        "Find Crystal Shards in caves (Air, Fire, Water, Earth, Order, Entropy)",
        "Build a Crucible (cauldron for alchemy)",
        "Create your first Thaumometer",
        "Scan everything to discover aspects",
        "Build a Research Table",
        "Craft Salis Mundus (magical crafting catalyst)",
      ],
      unlocks: ["Basic understanding of aspects", "Ability to scan and research"],
    },
    {
      stage: "early",
      title: "Establishment",
      objective: "Set up your magical workshop",
      steps: [
        "Build an Arcane Workbench",
        "Craft your first Wand",
        "Research basic Thaumaturgy",
        "Mine Cinnabar and Amber Ores",
        "Complete fundamental research",
      ],
      unlocks: ["Ability to craft basic magical items", "Vis storage and usage"],
    },
    {
      stage: "middle",
      title: "Mastery",
      objective: "Master alchemy and infusion",
      steps: [
        "Build an Infusion Altar",
        "Construct an Alchemical Furnace",
        "Create an Essentia Smeltery",
        "Research alchemical transmutation",
        "Find and cultivate Shimmerleaf",
        "Harvest Greatwood and Silverwood",
        "Craft Thaumium equipment",
        "Create Nitor (eternal flame)",
        "Produce Alchemical Brass",
      ],
      unlocks: [
        "Thaumium tools and armor",
        "Complex magical crafting",
        "Essentia manipulation",
      ],
    },
    {
      stage: "late",
      title: "Transcendence",
      objective: "Delve into forbidden knowledge",
      steps: [
        "Research Eldritch knowledge",
        "Craft Void Metal equipment",
        "Create Primordial Pearls",
        "Build Eldritch Obelisks",
        "Obtain the Eldritch Eye",
        "Monitor sanity with Sanity Checker",
        "Manage Flux with Flux Scrubbers",
      ],
      unlocks: [
        "Most powerful magical items",
        "Dimension manipulation",
        "Mastery over all aspects",
      ],
      warnings: [
        "Eldritch research can have consequences",
        "Too much forbidden knowledge affects sanity",
        "Flux pollution can corrupt the world",
      ],
    },
  ],
};
