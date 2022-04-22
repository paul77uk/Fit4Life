package com.paulvickers.fit4life.data.data_access_objects

import androidx.room.*
import com.paulvickers.fit4life.data.models.ExerciseTitle
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseTitleDao {

    @Query("SELECT * FROM ExerciseTitle")
    fun getExercises(): Flow<List<ExerciseTitle>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExerciseTitle(exerciseTitle: ExerciseTitle)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExerciseTitleList(exerciseTitleList: List<ExerciseTitle>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateExerciseTitle(exerciseTitle: ExerciseTitle)

    @Delete
    suspend fun deleteExerciseTitle(exerciseTitle: ExerciseTitle)

}