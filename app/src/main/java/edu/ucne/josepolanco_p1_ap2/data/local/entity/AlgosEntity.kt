package edu.ucne.josepolanco_p1_ap2.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Algo")
data class AlgoEntity (
    @PrimaryKey
    val algoid: Int? = null
)