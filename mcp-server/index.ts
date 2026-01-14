#!/usr/bin/env node

import { Server } from "@modelcontextprotocol/sdk/server/index.js";
import { StdioServerTransport } from "@modelcontextprotocol/sdk/server/stdio.js";
import {
  CallToolRequestSchema,
  ListToolsRequestSchema,
  Tool,
} from "@modelcontextprotocol/sdk/types.js";
import { thaumVibeData } from "./data.js";

// Create MCP server instance
const server = new Server(
  {
    name: "thaumvibe-minecraft-mcp-server",
    version: "1.0.0",
  },
  {
    capabilities: {
      tools: {},
    },
  }
);

// Define available tools
const TOOLS: Tool[] = [
  {
    name: "list_aspects",
    description: "List all Thaumcraft aspects including primal and compound aspects with their properties",
    inputSchema: {
      type: "object",
      properties: {
        type: {
          type: "string",
          enum: ["all", "primal", "compound"],
          description: "Filter aspects by type: all (default), primal, or compound",
        },
      },
    },
  },
  {
    name: "get_aspect_info",
    description: "Get detailed information about a specific Thaumcraft aspect",
    inputSchema: {
      type: "object",
      properties: {
        aspect: {
          type: "string",
          description: "The aspect tag (e.g., 'aer', 'terra', 'praecantatio')",
        },
      },
      required: ["aspect"],
    },
  },
  {
    name: "list_items",
    description: "List all ThaumVibe items organized by progression tier",
    inputSchema: {
      type: "object",
      properties: {
        tier: {
          type: "string",
          enum: ["all", "early", "middle", "late"],
          description: "Filter items by progression tier",
        },
      },
    },
  },
  {
    name: "get_item_info",
    description: "Get detailed information about a specific ThaumVibe item",
    inputSchema: {
      type: "object",
      properties: {
        item: {
          type: "string",
          description: "The item name (e.g., 'wand', 'thaumometer', 'thaumonomicon')",
        },
      },
      required: ["item"],
    },
  },
  {
    name: "list_blocks",
    description: "List all ThaumVibe blocks organized by progression tier",
    inputSchema: {
      type: "object",
      properties: {
        tier: {
          type: "string",
          enum: ["all", "early", "middle", "late"],
          description: "Filter blocks by progression tier",
        },
      },
    },
  },
  {
    name: "get_block_info",
    description: "Get detailed information about a specific ThaumVibe block",
    inputSchema: {
      type: "object",
      properties: {
        block: {
          type: "string",
          description: "The block name (e.g., 'crucible', 'research_table', 'infusion_altar')",
        },
      },
      required: ["block"],
    },
  },
  {
    name: "get_research_info",
    description: "Get information about the research system and categories",
    inputSchema: {
      type: "object",
      properties: {
        category: {
          type: "string",
          enum: ["all", "basics", "alchemy", "artifice", "thaumaturgy", "eldritch"],
          description: "Research category to get info about",
        },
      },
    },
  },
  {
    name: "get_progression_guide",
    description: "Get progression guide for ThaumVibe gameplay",
    inputSchema: {
      type: "object",
      properties: {
        stage: {
          type: "string",
          enum: ["all", "early", "middle", "late"],
          description: "Progression stage to get guide for",
        },
      },
    },
  },
];

// Handle list_tools request
server.setRequestHandler(ListToolsRequestSchema, async () => {
  return { tools: TOOLS };
});

// Handle tool calls
server.setRequestHandler(CallToolRequestSchema, async (request) => {
  const { name, arguments: args } = request.params;

  try {
    switch (name) {
      case "list_aspects": {
        const type = (args?.type as string) || "all";
        const aspects = thaumVibeData.aspects.filter((aspect) => {
          if (type === "primal") return aspect.isPrimal;
          if (type === "compound") return !aspect.isPrimal;
          return true;
        });

        return {
          content: [
            {
              type: "text",
              text: JSON.stringify(aspects, null, 2),
            },
          ],
        };
      }

      case "get_aspect_info": {
        const aspectTag = args?.aspect as string;
        const aspect = thaumVibeData.aspects.find((a) => a.tag === aspectTag.toLowerCase());

        if (!aspect) {
          return {
            content: [
              {
                type: "text",
                text: `Aspect '${aspectTag}' not found. Available aspects: ${thaumVibeData.aspects.map((a) => a.tag).join(", ")}`,
              },
            ],
            isError: true,
          };
        }

        return {
          content: [
            {
              type: "text",
              text: JSON.stringify(aspect, null, 2),
            },
          ],
        };
      }

      case "list_items": {
        const tier = (args?.tier as string) || "all";
        let items = thaumVibeData.items;

        if (tier !== "all") {
          items = items.filter((item) => item.tier === tier);
        }

        return {
          content: [
            {
              type: "text",
              text: JSON.stringify(items, null, 2),
            },
          ],
        };
      }

      case "get_item_info": {
        const itemName = args?.item as string;
        const item = thaumVibeData.items.find(
          (i) => i.name.toLowerCase() === itemName.toLowerCase() || i.id === itemName.toLowerCase()
        );

        if (!item) {
          return {
            content: [
              {
                type: "text",
                text: `Item '${itemName}' not found. Use list_items to see available items.`,
              },
            ],
            isError: true,
          };
        }

        return {
          content: [
            {
              type: "text",
              text: JSON.stringify(item, null, 2),
            },
          ],
        };
      }

      case "list_blocks": {
        const tier = (args?.tier as string) || "all";
        let blocks = thaumVibeData.blocks;

        if (tier !== "all") {
          blocks = blocks.filter((block) => block.tier === tier);
        }

        return {
          content: [
            {
              type: "text",
              text: JSON.stringify(blocks, null, 2),
            },
          ],
        };
      }

      case "get_block_info": {
        const blockName = args?.block as string;
        const block = thaumVibeData.blocks.find(
          (b) => b.name.toLowerCase() === blockName.toLowerCase() || b.id === blockName.toLowerCase()
        );

        if (!block) {
          return {
            content: [
              {
                type: "text",
                text: `Block '${blockName}' not found. Use list_blocks to see available blocks.`,
              },
            ],
            isError: true,
          };
        }

        return {
          content: [
            {
              type: "text",
              text: JSON.stringify(block, null, 2),
            },
          ],
        };
      }

      case "get_research_info": {
        const category = (args?.category as string) || "all";
        let research = thaumVibeData.research;

        if (category !== "all") {
          research = research.filter((r) => r.category === category);
        }

        return {
          content: [
            {
              type: "text",
              text: JSON.stringify(
                {
                  categories: thaumVibeData.researchCategories,
                  research: research,
                },
                null,
                2
              ),
            },
          ],
        };
      }

      case "get_progression_guide": {
        const stage = (args?.stage as string) || "all";
        let progression = thaumVibeData.progression;

        if (stage !== "all") {
          progression = progression.filter((p) => p.stage === stage);
        }

        return {
          content: [
            {
              type: "text",
              text: JSON.stringify(progression, null, 2),
            },
          ],
        };
      }

      default:
        return {
          content: [
            {
              type: "text",
              text: `Unknown tool: ${name}`,
            },
          ],
          isError: true,
        };
    }
  } catch (error) {
    return {
      content: [
        {
          type: "text",
          text: `Error executing tool ${name}: ${error instanceof Error ? error.message : String(error)}`,
        },
      ],
      isError: true,
    };
  }
});

// Start the server
async function main() {
  const transport = new StdioServerTransport();
  await server.connect(transport);
  console.error("ThaumVibe Minecraft MCP Server running on stdio");
}

main().catch((error) => {
  console.error("Fatal error in main():", error);
  process.exit(1);
});
