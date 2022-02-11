package com.paulvickers.fit4life.data.data_source

import androidx.room.*
import com.paulvickers.fit4life.domain.model.WorkoutTitle
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutTitleDao {

    @Query("SELECT * FROM WorkoutTitle")
    fun getWorkoutTitles(): Flow<List<WorkoutTitle>>

    @Query("SELECT * FROM WorkoutTitle WHERE id = :id")
    suspend fun getWorkoutTitleById(id: Int): WorkoutTitle?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutTitle(workoutTitle: WorkoutTitle)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWorkoutTitle(workoutTitle: WorkoutTitle)

    @Delete
    suspend fun deleteWorkoutTitle(workoutTitle: WorkoutTitle)

}