package edu.ucne.josepolanco_p1_ap2.data.repository

import edu.ucne.josepolanco_p1_ap2.data.local.dao.AlgoDao
import edu.ucne.josepolanco_p1_ap2.data.local.entity.AlgoEntity
import javax.inject.Inject

class AlgoRepository @Inject constructor(
    private val algoDao: AlgoDao
){
    suspend fun save(algo: AlgoEntity) = algoDao.save(algo)

    suspend fun find(id: Int) = algoDao.find(id)

    suspend fun delete(algo: AlgoEntity) = algoDao.delete(algo)

    fun getAll() = algoDao.getAll()
}