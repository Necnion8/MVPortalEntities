package com.gmail.necnionch.myplugin.mvportalentities.bukkit;

import org.bukkit.plugin.java.JavaPlugin;

public final class MVPortalEntities extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PortalListener(this), this);
    }

}
