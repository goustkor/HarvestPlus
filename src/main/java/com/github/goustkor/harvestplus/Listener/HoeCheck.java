package com.github.goustkor.harvestplus.Listener;

import com.github.goustkor.harvestplus.HarvestPlus;
import com.github.goustkor.harvestplus.Utils.CheckCrop;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Material.*;

public class HoeCheck implements Listener {

    private HarvestPlus plugin;

    public HoeCheck(HarvestPlus plugin) { this.plugin = plugin; }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        Block block = event.getClickedBlock();
        if(block != null) {
            if (isHoe(itemInHand)) {
                CheckCrop crop = new CheckCrop(block);
                if (crop.isCrop()) {
                    String percent = crop.getPercent() + "%";
                    if (percent.equals("100%")) percent = "Mature";
                    String msg = ChatColor.GOLD + "" + ChatColor.BOLD + crop.getName() + ": " + ChatColor.RESET + percent;
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(msg));
                }
            }
        }
    }

    private boolean isHoe(ItemStack item){

        switch (item.getType()){
            case DIAMOND_HOE:
            case GOLDEN_HOE:
            case IRON_HOE:
            case STONE_HOE:
            case WOODEN_HOE:
                return true;
        }

        return false;
    }
}
