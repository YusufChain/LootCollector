package com.yusufchain.lootcollector;

import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class LootCommand implements CommandExecutor {

    private final LootCollector plugin;
    private final LootStorage storage;

    public LootCommand(LootCollector plugin, LootStorage storage) {
        this.plugin = plugin;
        this.storage = storage;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) return true;
        Player p = (Player) sender;

        if (!p.hasPermission("lootcollector.use")) {
            p.sendMessage(plugin.color(plugin.getConfig().getString("messages.no-permission")));
            return true;
        }

        p.openInventory(storage.getInventory(p.getUniqueId()));
        p.sendMessage(plugin.color(
                plugin.getConfig().getString("messages.prefix") +
                        plugin.getConfig().getString("messages.opened")
        ));
        return true;
    }
}
