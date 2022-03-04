package com.paulvickers.fit4life.data.data_access_objects

import androidx.room.*
import com.paulvickers.fit4life.data.models.History
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Query("SELECT * FROM History Where exerciseId = :exerciseId")
    fun getHistoryByExerciseId(exerciseId: Int): Flow<List<History>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: History)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateHistory(history: History)

    @Delete
    suspend fun deleteHistory(history: History)

}