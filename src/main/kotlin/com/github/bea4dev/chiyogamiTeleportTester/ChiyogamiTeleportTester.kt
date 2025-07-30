package com.github.bea4dev.chiyogamiTeleportTester

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

lateinit var plugin: ChiyogamiTeleportTester

class ChiyogamiTeleportTester : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        plugin = this

        plugin.logger.info("On enable")

        val pluginManager = Bukkit.getPluginManager()
        pluginManager.registerEvents(EventListener(), this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
