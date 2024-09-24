package edu.ucne.josepolanco_p1_ap2.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.josepolanco_p1_ap2.data.local.dao.AlgoDao
import edu.ucne.josepolanco_p1_ap2.data.local.entity.AlgoEntity

@Database(
    entities = [
        AlgoEntity::class
    ],

    version = 1,
    exportSchema = false
)
abstract class ParcialRoom : RoomDatabase() {
    abstract fun AlgoDao(): AlgoDao
}