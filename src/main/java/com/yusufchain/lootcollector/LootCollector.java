package com.yusufchain.lootcollector;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class LootCollector extends JavaPlugin {

    private static LootCollector instance;
    private LootStorage storage;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        printBanner();

        storage = new LootStorage(this);

        Bukkit.getPluginManager().registerEvents(new LootListener(this, storage), this);
        Bukkit.getPluginManager().registerEvents(new LootGui(this), this);

        if (getCommand("lootcollector") != null) {
            getCommand("lootcollector").setExecutor(new LootCommand(this, storage));
        }

        log("&aPlugin successfully enabled.");
        log("&7Minecraft versions: &f1.16 &7→ &f1.21.4");
        log("&7System: &bGUI-based Personal Loot Storage");
    }

    @Override
    public void onDisable() {
        log("&cPlugin disabled.");
    }

    public static LootCollector get() {
        return instance;
    }

    public LootStorage getStorage() {
        return storage;
    }


    public String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public void log(String message) {
        Bukkit.getConsoleSender().sendMessage(color("&b[LootCollector] &7" + message));
    }

    private void printBanner() {
        Bukkit.getConsoleSender().sendMessage(color(
                "\n" +
                        "&b    mmmm     mmmm    mm        mm        mmmmmmmm     mmmm   mmmmmmmm    mmmm    mmmmmm\n" +
                        "&b  ##\"\"\"\"#   ##\"\"##   ##        ##        ##\"\"\"\"\"\"   ##\"\"\"\"#  \"\"\"##\"\"\"   ##\"\"##   ##\"\"\"\"##\n" +
                        "&b ##\"       ##    ##  ##        ##        ##        ##\"          ##     ##    ##  ##    ##\n" +
                        "&b ##        ##    ##  ##        ##        #######   ##           ##     ##    ##  #######\n" +
                        "&b ##m       ##    ##  ##        ##        ##        ##m          ##     ##    ##  ##  \"##m\n" +
                        "&b  ##mmmm#   ##mm##   ##mmmmmm  ##mmmmmm  ##mmmmmm   ##mmmm#     ##      ##mm##   ##    ##\n" +
                        "&7    \"\"\"\"     \"\"\"\"    \"\"\"\"\"\"\"\"  \"\"\"\"\"\"\"\"  \"\"\"\"\"\"\"\"     \"\"\"\"      \"\"       \"\"\"\"    \"\"    \"\"\n" +
                        "\n" +
                        "&7» &bLootCollector &f" + getDescription().getVersion() + "\n" +
                        "&7» &fDeveloper: &bYusufChain\n" +
                        "&7» &fGUI Loot Storage System\n"
        ));
    }
}
