package com.github.goustkor.harvestplus;

import com.github.goustkor.harvestplus.Listener.Crops;
import org.bukkit.plugin.java.JavaPlugin;

public final class HarvestPlus extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Crops(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
