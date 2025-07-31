package com.github.bea4dev.chiyogamiTeleportTester

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerAnimationEvent

class EventListener : Listener {
    @EventHandler
    fun onPlayerArmSwing(event: PlayerAnimationEvent) {
        val player = event.player

        if (player.isSneaking) {
            for (i in 0..<50) {
                TeleportEntity(player.location).runTaskTimer(plugin, 0, 1)
            }
        }
    }
}