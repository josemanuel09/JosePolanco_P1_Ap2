package edu.ucne.josepolanco_p1_ap2.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.josepolanco_p1_ap2.data.local.dao.VentaDao
import edu.ucne.josepolanco_p1_ap2.data.local.entity.VentaEntity

@Database(
    entities = [
        VentaEntity::class
    ],

    version = 1,
    exportSchema = false
)
abstract class ParcialRoom : RoomDatabase() {
    abstract fun VentaDao(): VentaDao
}