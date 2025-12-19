package com.yusufchain.lootcollector;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;

public class LootGui implements Listener {

    public LootGui(LootCollector lootCollector) {
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Inventory inv = e.getInventory();

        if (!(inv.getHolder() instanceof LootHolder)) return;

        if (e.getClickedInventory() != null && e.getClickedInventory() != inv) {
            if (e.isShiftClick()) {
                e.setCancelled(true);
            }
            return;
        }

        if (e.getCursor() != null && !e.getCursor().getType().isAir()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent e) {
        if (e.getInventory().getHolder() instanceof LootHolder) {
            e.setCancelled(true);
        }
    }
}
