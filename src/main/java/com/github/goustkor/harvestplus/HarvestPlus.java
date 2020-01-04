package com.github.goustkor.harvestplus;

import com.github.goustkor.harvestplus.Listener.BreakCrops;
import com.github.goustkor.harvestplus.Listener.HoeCheck;
import org.bukkit.plugin.java.JavaPlugin;

public final class HarvestPlus extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BreakCrops(this), this);
        getServer().getPluginManager().registerEvents(new HoeCheck(this), this);

    }

    @Override
    public void onDisable() {
    }
}
