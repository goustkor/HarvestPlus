package com.github.goustkor.harvestplus.Listener;

import com.github.goustkor.harvestplus.HarvestPlus;
import com.github.goustkor.harvestplus.Utils.CheckCrop;
import com.github.goustkor.harvestplus.Utils.RemoveInventoryItems;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class BreakCrops implements Listener {

    private HarvestPlus plugin;

    public BreakCrops(HarvestPlus plugin) { this.plugin = plugin; }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if(player.isSneaking()) return;

        Block block = event.getClickedBlock();

        if(block != null) {
            CheckCrop crop = new CheckCrop(block);

            if (crop.isCrop()) {
                if(crop.getAge() == crop.getMaxAge()){
                    if(player.getInventory().contains(crop.getSeed(), 1)){
                        new RemoveInventoryItems(player, 1, crop.getSeed());
                        block.breakNaturally();
                        block.setType(crop.getType());
                    }else{
                        block.breakNaturally();
                    }
                }
            }
        }
    }

}
