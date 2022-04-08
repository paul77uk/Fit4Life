package com.paulvickers.fit4life.data.data_access_objects

import androidx.room.*
import com.paulvickers.fit4life.data.models.WorkoutTitle
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutTitleDao {

    @Query("SELECT * FROM WorkoutTitle")
    fun getWorkoutTitles(): Flow<List<WorkoutTitle>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutTitle(workoutTitle: WorkoutTitle)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWorkoutTitle(workoutTitle: WorkoutTitle)

    @Delete
    suspend fun deleteWorkoutTitle(workoutTitle: WorkoutTitle)

    @Query("SELECT MAX(id) FROM WorkoutTitle")
    fun getMaxId() : Flow<Int>

}