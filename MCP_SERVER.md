# ThaumVibe Minecraft MCP Server

A Model Context Protocol (MCP) server for ThaumVibe, a recreation of the classic Thaumcraft mod for Minecraft 1.21.1+. This MCP server provides AI assistants with access to comprehensive information about ThaumVibe's magical systems, items, blocks, and gameplay progression.

## What is ThaumVibe?

ThaumVibe is a NeoForge mod that recreates the magical experience of Thaumcraft, featuring:
- **Aspects System**: 16 magical aspects (6 primal + 10 compound) that form the foundation of all magic
- **Vis Energy**: Magical power that flows through the world
- **Research System**: Discover magical knowledge through scanning and experimentation
- **Progression**: Three-tiered gameplay from basic magic to forbidden knowledge

## What is MCP?

The Model Context Protocol (MCP) is an open protocol that enables AI assistants to securely access external data sources and tools. This server allows AI assistants to query ThaumVibe game data to help players understand the mod's mechanics and progression.

## Features

This MCP server provides the following tools:

### Aspect Tools
- `list_aspects` - List all Thaumcraft aspects (primal, compound, or all)
- `get_aspect_info` - Get detailed information about a specific aspect

### Item Tools
- `list_items` - List all ThaumVibe items by tier (early, middle, late)
- `get_item_info` - Get detailed information about a specific item

### Block Tools
- `list_blocks` - List all ThaumVibe blocks by tier
- `get_block_info` - Get detailed information about a specific block

### Research & Progression Tools
- `get_research_info` - Get information about research categories
- `get_progression_guide` - Get progression guides for different game stages

## Installation

### Prerequisites
- Node.js 18 or higher
- npm or yarn

### Setup

1. Clone the repository:
```bash
git clone https://github.com/vibecoding-inc/ThaumVibe.git
cd ThaumVibe
```

2. Install dependencies:
```bash
npm install
```

3. Build the server:
```bash
npm run build
```

## Usage

### Running the Server

#### Development Mode
```bash
npm run dev
```

#### Production Mode
```bash
npm run build
npm start
```

### Using with Claude Desktop

To use this MCP server with Claude Desktop, add the following to your Claude Desktop configuration file:

**MacOS**: `~/Library/Application Support/Claude/claude_desktop_config.json`
**Windows**: `%APPDATA%\Claude\claude_desktop_config.json`

```json
{
  "mcpServers": {
    "thaumvibe": {
      "command": "node",
      "args": ["/absolute/path/to/ThaumVibe/dist/index.js"]
    }
  }
}
```

Replace `/absolute/path/to/ThaumVibe` with the actual path to your ThaumVibe directory.

### Using with Other MCP Clients

This server implements the standard MCP protocol and can be used with any MCP-compatible client. It communicates via stdio, making it easy to integrate.

## Example Queries

Once connected to an AI assistant through MCP, you can ask questions like:

- "What are the primal aspects in ThaumVibe?"
- "Tell me about the Thaumometer item"
- "What blocks do I need for middle game progression?"
- "How does the research system work?"
- "What is the Praecantatio aspect made of?"
- "Give me a progression guide for early game"

## Tools Reference

### list_aspects
List all Thaumcraft aspects.

**Parameters:**
- `type` (optional): Filter by "all", "primal", or "compound"

**Example:**
```json
{
  "type": "primal"
}
```

### get_aspect_info
Get detailed information about a specific aspect.

**Parameters:**
- `aspect` (required): The aspect tag (e.g., "aer", "praecantatio")

**Example:**
```json
{
  "aspect": "praecantatio"
}
```

### list_items
List all ThaumVibe items.

**Parameters:**
- `tier` (optional): Filter by "all", "early", "middle", or "late"

**Example:**
```json
{
  "tier": "early"
}
```

### get_item_info
Get detailed information about a specific item.

**Parameters:**
- `item` (required): The item name or ID

**Example:**
```json
{
  "item": "wand"
}
```

### list_blocks
List all ThaumVibe blocks.

**Parameters:**
- `tier` (optional): Filter by "all", "early", "middle", or "late"

**Example:**
```json
{
  "tier": "middle"
}
```

### get_block_info
Get detailed information about a specific block.

**Parameters:**
- `block` (required): The block name or ID

**Example:**
```json
{
  "block": "infusion_altar"
}
```

### get_research_info
Get information about the research system.

**Parameters:**
- `category` (optional): Filter by "all", "basics", "alchemy", "artifice", "thaumaturgy", or "eldritch"

**Example:**
```json
{
  "category": "alchemy"
}
```

### get_progression_guide
Get progression guide for ThaumVibe gameplay.

**Parameters:**
- `stage` (optional): Filter by "all", "early", "middle", or "late"

**Example:**
```json
{
  "stage": "early"
}
```

## Development

### Project Structure
```
ThaumVibe/
├── mcp-server/          # MCP server source code
│   ├── index.ts         # Main server implementation
│   └── data.ts          # Game data (aspects, items, blocks, etc.)
├── src/                 # Minecraft mod source code
├── dist/                # Compiled JavaScript (generated)
├── package.json         # Node.js dependencies
├── tsconfig.json        # TypeScript configuration
└── MCP_SERVER.md        # This file
```

### Adding New Data

To add new items, blocks, or aspects:

1. Edit `mcp-server/data.ts`
2. Add your new data to the appropriate array
3. Rebuild the server: `npm run build`

### TypeScript

The server is written in TypeScript for type safety and better IDE support. The source code is in `mcp-server/` and compiles to `dist/`.

## Troubleshooting

### Server won't start
- Ensure Node.js 18+ is installed: `node --version`
- Check that dependencies are installed: `npm install`
- Verify the build completed: `npm run build`

### Can't connect from Claude Desktop
- Verify the path in `claude_desktop_config.json` is absolute and correct
- Check that the server builds without errors
- Restart Claude Desktop after updating the config

### Tool returns "not found" errors
- Check the exact spelling of item/block/aspect names
- Use `list_items`, `list_blocks`, or `list_aspects` to see available options
- Names are case-insensitive but must match exactly

## Contributing

Contributions are welcome! Please feel free to submit issues or pull requests.

## License

MIT License - see the main repository for details.

## About ThaumVibe

ThaumVibe is a recreation of the classic Thaumcraft mod for modern Minecraft (1.21.1+) using NeoForge. It brings back the magical experience of Thaumaturgy with aspects, research, and mystical crafting.

For more information about the mod itself, see the main [README.md](README.md).

## Links

- [ThaumVibe Repository](https://github.com/vibecoding-inc/ThaumVibe)
- [Model Context Protocol Documentation](https://modelcontextprotocol.io/)
- [MCP Specification](https://spec.modelcontextprotocol.io/)
