package com.wardensmp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class WardenShardMain extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new WardenShardListener(), this);
        getLogger().info("WardenShardPlugin has been successfully enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("WardenShardPlugin has been disabled.");
    }
}
