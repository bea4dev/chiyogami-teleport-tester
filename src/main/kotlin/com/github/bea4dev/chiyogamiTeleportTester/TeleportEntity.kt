package com.github.bea4dev.chiyogamiTeleportTester

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.util.Vector
import world.chiyogami.chiyogamilib.scheduler.WorldThreadRunnable
import java.util.logging.Level
import kotlin.random.Random

class TeleportEntity(startLocation: Location) : BukkitRunnable() {
    var i = 0
    val entity = startLocation.world.spawnEntity(startLocation, EntityType.ARMOR_STAND)

    override fun run() {
        if (entity.isDead) {
            plugin.logger.log(Level.WARNING, "Entity is dead!")
            i = 200
        }

        if (i >= 200) {
            cancel()
            return
        }

        TeleportTask(entity).runTask(plugin)

        i++
    }
}

class TeleportTask(private val entity: Entity) : WorldThreadRunnable(entity.world) {
    override fun run() {
        val worlds = Bukkit.getWorlds()
        val distWorld = worlds[Random.nextInt(worlds.size)]!!

        val distPosition = entity.location.toVector().add(
            Vector(
                Random.nextInt(50) - 25, 0, Random.nextInt(50) - 25
            )
        )

        val distLocation = distPosition.toLocation(distWorld)

        if (!entity.isDead) {
            distLocation.chunk.load()
            entity.teleport(distLocation)
        }
    }
}