package com.paulvickers.fit4life.data.data_access_objects

import androidx.room.*
import com.paulvickers.fit4life.data.models.WorkoutDay
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDayDao {

    @Query("SELECT * FROM WorkoutDay WHERE workoutWeekId = :workoutWeekId ")
    fun getDaysByWorkoutWeekId(workoutWeekId: Int): Flow<List<WorkoutDay>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutDay(workoutDay: WorkoutDay)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWorkoutDay(workoutDay: WorkoutDay)

    @Delete
    suspend fun deleteWorkoutDay(workoutDay: WorkoutDay)

    @Query("SELECT MIN(id) FROM WorkoutDay")
    fun getMinDayId() : Flow<Int>

}