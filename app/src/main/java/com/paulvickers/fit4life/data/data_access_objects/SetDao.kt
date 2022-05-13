package com.paulvickers.fit4life.data.data_access_objects

import androidx.room.*
import com.paulvickers.fit4life.data.models.Set
import kotlinx.coroutines.flow.Flow

@Dao
interface SetDao {

    @Query("SELECT * FROM `Set` WHERE dayId = :dayId")
    fun getSetsByDayId(dayId: Int): Flow<List<Set>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSet(set: Set)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSet(set: Set)

    @Query("UPDATE `Set` SET weight = :weight WHERE id == :id")
    suspend fun updateWeightById(weight: Int, id: Int)

    @Query("UPDATE `Set` SET reps = :reps WHERE id == :id")
    suspend fun updateRepsById(reps: Int, id: Int)

    @Query("UPDATE `Set` SET distance = :distance WHERE id == :id")
    suspend fun updateDistanceById(distance: Int, id: Int)

    @Query("UPDATE `Set` SET time = :time WHERE id == :id")
    suspend fun updateTimeById(time: Double, id: Int)

    @Query("UPDATE `Set` SET isCompleted = :isCompleted WHERE id == :id")
    suspend fun updateIsCompletedById(isCompleted: Int, id: Int)

    @Delete
    suspend fun deleteSet(set: Set)

}