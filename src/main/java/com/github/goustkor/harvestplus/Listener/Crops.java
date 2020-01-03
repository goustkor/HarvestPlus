package com.github.goustkor.harvestplus.Listener;

import com.github.goustkor.harvestplus.HarvestPlus;
import org.bukkit.ChatColor;
import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.graalvm.compiler.lir.alloc.lsra.LinearScan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.bukkit.Material.*;

public class Crops implements Listener {

    private HarvestPlus plugin = HarvestPlus.getPlugin(HarvestPlus.class);

    public Crops() {

    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();

        Block block = event.getClickedBlock();

        if(block != null) {
            Material target = null;
            Material seed = null;

            switch(block.getType()){
                case CARROTS:
                    target = CARROTS;
                    seed = CARROT;
                    break;
                case WHEAT:
                    target = WHEAT;
                    seed = WHEAT_SEEDS;
                    break;
                case BEETROOTS:
                    target = BEETROOTS;
                    seed = BEETROOT_SEEDS;
                    break;
                case POTATOES:
                    target = POTATOES;
                    seed = POTATO;
                    break;
                case COCOA:
                    target = COCOA;
                    seed = COCOA_BEANS;
                    break;
            }

            if (target != null) {
                BlockData bdata = block.getBlockData();
                Ageable age = (Ageable) bdata;
                if(age.getAge() == age.getMaximumAge()){
                    if(player.getInventory().contains(seed, 1)){
                        consumeItem(player, 1, seed);
                        block.breakNaturally();
                        ((Ageable) bdata).setAge(0);
                        block.setType(target);
                        block.setBlockData(bdata);
                    }else{
                        block.breakNaturally();
                    }
                }
            }
        }
    }

    private boolean consumeItem(Player player, int count, Material mat) {
        Map<Integer, ? extends ItemStack> ammo = player.getInventory().all(mat);

        int found = 0;
        for (ItemStack stack : ammo.values())
            found += stack.getAmount();
        if (count > found)
            return false;

        for (Integer index : ammo.keySet()) {
            ItemStack stack = ammo.get(index);

            int removed = Math.min(count, stack.getAmount());
            count -= removed;

            if (stack.getAmount() == removed)
                player.getInventory().setItem(index, null);
            else
                stack.setAmount(stack.getAmount() - removed);

            if (count <= 0)
                break;
        }

        player.updateInventory();
        return true;
    }

}
