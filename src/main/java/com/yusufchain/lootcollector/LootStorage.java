package com.yusufchain.lootcollector;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LootStorage {

    private final LootCollector plugin;
    private final Map<UUID, Inventory> storage = new HashMap<>();

    public LootStorage(LootCollector plugin) {
        this.plugin = plugin;
    }

    public Inventory getInventory(UUID uuid) {
        return storage.computeIfAbsent(uuid, id -> createInventory());
    }

    private Inventory createInventory() {
        int size = plugin.getConfig().getInt("settings.gui.size", 54);
        String title = plugin.color(plugin.getConfig().getString("settings.gui.title"));
        return Bukkit.createInventory(new LootHolder(), size, title);
    }
}
