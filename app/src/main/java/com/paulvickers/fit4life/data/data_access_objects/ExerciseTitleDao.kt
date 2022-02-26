package com.paulvickers.fit4life.data.data_access_objects

import androidx.room.*
import com.paulvickers.fit4life.data.models.ExerciseTitle
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseTitleDao {

    @Query("SELECT * FROM ExerciseTitle Where dayId = :dayId")
    fun getExercisesByDayId(dayId: Int): Flow<List<ExerciseTitle>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExerciseTitle(exerciseTitle: ExerciseTitle)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateExerciseTitle(exerciseTitle: ExerciseTitle)

    @Delete
    suspend fun deleteExerciseTitle(exerciseTitle: ExerciseTitle)

}