package com.paulvickers.fit4life.domain.repository

import androidx.room.Query
import com.paulvickers.fit4life.data.relations.WorkoutWithDays
import com.paulvickers.fit4life.domain.model.WorkoutDay
import kotlinx.coroutines.flow.Flow

interface WorkoutDayRepository {

    fun getWorkoutDays(): Flow<List<WorkoutDay>>

    suspend fun getWorkoutDayById(id: Int): WorkoutDay?

    suspend fun insertWorkoutDay(workoutDay: WorkoutDay)

    suspend fun deleteWorkoutDay(workoutDay: WorkoutDay)

    suspend fun getDaysOfWorkout(workoutTitleId: Int): Flow<List<WorkoutDay>>

    suspend fun deleteWorkoutDaysByWorkoutTitleId(workoutTitleId: Int)

}