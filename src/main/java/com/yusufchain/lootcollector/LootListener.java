package com.yusufchain.lootcollector;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class LootListener implements Listener {

    private static final String META_KEY = "lootcollector-copied";

    private final LootCollector plugin;
    private final LootStorage storage;

    public LootListener(LootCollector plugin, LootStorage storage) {
        this.plugin = plugin;
        this.storage = storage;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPickup(EntityPickupItemEvent e) {

        if (!(e.getEntity() instanceof Player p)) return;

        if (p.getInventory().firstEmpty() != -1) return;

        Item itemEntity = e.getItem();

        if (itemEntity.hasMetadata(META_KEY)) {
            e.setCancelled(true);
            return;
        }

        ItemStack stack = itemEntity.getItemStack();
        if (stack == null || stack.getType().isAir()) return;

        e.setCancelled(true);

        Inventory guiInv = storage.getInventory(p.getUniqueId());
        guiInv.addItem(stack.clone());

        itemEntity.setMetadata(
                META_KEY,
                new FixedMetadataValue(plugin, true)
        );
    }
}
