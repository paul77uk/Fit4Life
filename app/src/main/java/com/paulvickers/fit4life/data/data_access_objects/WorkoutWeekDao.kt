package com.paulvickers.fit4life.data.data_access_objects

import androidx.room.*
import com.paulvickers.fit4life.data.models.WorkoutWeek
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutWeekDao {

    @Query("SELECT * FROM WorkoutWeek WHERE workoutTitleId = :workoutTitleId ")
    fun getWeeksByWorkoutTitleId(workoutTitleId: Int): Flow<List<WorkoutWeek>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutWeek(workoutWeek: WorkoutWeek)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutWeekList(workoutWeekList: List<WorkoutWeek>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWorkoutWeek(workoutWeek: WorkoutWeek)

    @Delete
    suspend fun deleteWorkoutWeek(workoutDayWeek: WorkoutWeek)

    @Query("SELECT MAX(id) FROM WorkoutWeek")
    fun getMaxWeekId() : Flow<Int>

}