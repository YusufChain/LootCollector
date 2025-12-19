package com.yusufchain.lootcollector;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Feedback {

    public static void send(LootCollector plugin, Player p) {

        if (plugin.getConfig().getBoolean("feedback.title.enabled")) {
            p.sendTitle(
                    plugin.color(plugin.getConfig().getString("feedback.title.text.title")),
                    plugin.color(plugin.getConfig().getString("feedback.title.text.subtitle")),
                    plugin.getConfig().getInt("feedback.title.fade-in"),
                    plugin.getConfig().getInt("feedback.title.stay"),
                    plugin.getConfig().getInt("feedback.title.fade-out")
            );
        }

        if (plugin.getConfig().getBoolean("feedback.sound.enabled")) {
            try {
                Sound s = Sound.valueOf(plugin.getConfig().getString("feedback.sound.name"));
                p.playSound(
                        p.getLocation(),
                        s,
                        (float) plugin.getConfig().getDouble("feedback.sound.volume"),
                        (float) plugin.getConfig().getDouble("feedback.sound.pitch")
                );
            } catch (Exception ignored) {}
        }
    }
}
