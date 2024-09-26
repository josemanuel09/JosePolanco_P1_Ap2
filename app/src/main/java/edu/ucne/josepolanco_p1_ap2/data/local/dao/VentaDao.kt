package edu.ucne.josepolanco_p1_ap2.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.josepolanco_p1_ap2.data.local.entity.VentaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VentaDao {
    @Upsert
    suspend fun save(algo: VentaEntity)

    @Query("Select * from Venta where ventaId = :id")
    suspend fun find(id: Int): VentaEntity

    @Delete
    suspend fun delete(algo: VentaEntity)

    @Query("Select * from Venta")
     fun getAll(): Flow<List<VentaEntity>>
}