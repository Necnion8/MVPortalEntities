package com.gmail.necnionch.myplugin.mvportalentities.bukkit;

import com.onarandombox.MultiversePortals.event.MVPortalEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PortalListener implements Listener {

    private final MVPortalEntities plugin;

    public PortalListener(MVPortalEntities plugin) {
        this.plugin = plugin;
    }


    @EventHandler(ignoreCancelled = true)
    public void onPortal(MVPortalEvent event) {
        Player player = event.getTeleportee();

        for (Entity entity : player.getNearbyEntities(10, 10, 10)) {
            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entity;
                if (livingEntity.isLeashed() && player.equals(livingEntity.getLeashHolder())) {
                    entity.teleport(event.getDestination().getLocation(player));

                    plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                        livingEntity.setLeashHolder(player);
                    }, 0);
                }
            }
        }
    }

}
