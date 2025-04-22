package com.dripleafanywhere;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.java.JavaPlugin;

public class DripleafAnywhere extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Register the event listener to listen for player interactions
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("DripleafAnywhere plugin enabled successfully!");
    }

    @Override
    public void onDisable() {
        getLogger().info("DripleafAnywhere plugin disabled.");
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        // Check if the interaction is a right-click with the main hand
        if (event.getAction().toString().contains("RIGHT_CLICK") && event.getHand() == EquipmentSlot.HAND) {
            // If the player right-clicked on a block
            if (event.hasBlock()) {
                // Log the block the player right-clicked on
                Material blockType = event.getClickedBlock().getType();
                getLogger().info("Player right-clicked on a block: " + blockType);

                // Check if the block is placeable (including Hoppers and other blocks)
                if (blockType == Material.GRASS_BLOCK || blockType == Material.DIRT || blockType == Material.STONE
                        || blockType == Material.HOPPER || blockType == Material.AIR) {

                    Block clickedBlock = event.getClickedBlock();
                    BlockData data = Material.BIG_DRIPLEAF.createBlockData();

                    // Try to place the Big Dripleaf in the block above the clicked block (without removing the clicked block)
                    Block aboveBlock = clickedBlock.getRelative(0, 1, 0); // Block directly above the clicked block

                    // Only place the Big Dripleaf if the block above is air (so the original block is not replaced)
                    if (aboveBlock.getType() == Material.AIR) {
                        aboveBlock.setType(Material.BIG_DRIPLEAF);
                        aboveBlock.setBlockData(data);

                        getLogger().info("Placed Big Dripleaf above: " + aboveBlock.getLocation());
                    }
                }
            }
        }
    }
}
