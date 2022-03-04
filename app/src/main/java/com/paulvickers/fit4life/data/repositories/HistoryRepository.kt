package com.paulvickers.fit4life.data.repositories

import com.paulvickers.fit4life.data.data_access_objects.HistoryDao
import com.paulvickers.fit4life.data.models.History
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class HistoryRepository @Inject constructor(private val historyDao: HistoryDao) {

    fun getHistoryByExerciseId(exerciseId: Int): Flow<List<History>> {
        return historyDao.getHistoryByExerciseId(exerciseId)
    }

    suspend fun insertHistory(history: History) {
        historyDao.insertHistory(history)
    }

    suspend fun updateHistory(history: History) {
        historyDao.updateHistory(history)
    }

    suspend fun deleteHistory(history: History) {
        historyDao.deleteHistory(history)
    }

}