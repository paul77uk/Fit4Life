package com.paulvickers.fit4life.data.data_source

import androidx.room.*
import com.paulvickers.fit4life.data.relations.WorkoutWithDays
import com.paulvickers.fit4life.domain.model.WorkoutDay
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDayDao {

    @Query("SELECT * FROM WorkoutDay")
    fun getWorkoutDays(): Flow<List<WorkoutDay>>

    @Query("SELECT * FROM WorkoutDay WHERE id = :id")
    suspend fun getWorkoutDayById(id: Int): WorkoutDay?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutDay(workoutDay: WorkoutDay)

    @Delete
    suspend fun deleteWorkoutDay(workoutDay: WorkoutDay)

    @Query("DELETE FROM WorkoutDay WHERE workoutTitleId = :workoutTitleId")
    suspend fun deleteWorkoutDaysByWorkoutTitleId(workoutTitleId: Int)

    @Query("SELECT * FROM WorkoutDay WHERE workoutTitleId = :workoutTitleId ")
    fun getDaysOfWorkout(workoutTitleId: Int): Flow<List<WorkoutDay>>

}