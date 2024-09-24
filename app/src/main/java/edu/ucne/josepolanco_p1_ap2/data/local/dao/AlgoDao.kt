package edu.ucne.josepolanco_p1_ap2.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.josepolanco_p1_ap2.data.local.entity.AlgoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlgoDao {
    @Upsert
    suspend fun save(algo: AlgoEntity)

    @Query("Select * from Algo where algoId = :id")
    suspend fun find(id: Int): AlgoEntity

    @Delete
    suspend fun delete(algo: AlgoEntity)

    @Query("Select * from Algo")
     fun getAll(): Flow<List<AlgoEntity>>
}