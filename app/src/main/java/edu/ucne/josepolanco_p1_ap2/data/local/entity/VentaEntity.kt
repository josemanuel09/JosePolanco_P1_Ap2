package edu.ucne.josepolanco_p1_ap2.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Venta")
data class VentaEntity (
    @PrimaryKey
    val ventaId: Int? = null,
    val nEmpresa: String = "",
    val galones: Double? = null,
    val descuento: Double? = null,
    val precio: Double? = null,
    val totalDescontado: Double? = null,
    val total: Double? = null

)