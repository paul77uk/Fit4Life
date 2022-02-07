package com.paulvickers.fit4life.domain.repository

import com.paulvickers.fit4life.domain.model.WorkoutTitle
import kotlinx.coroutines.flow.Flow

interface WorkoutTitleRepository {

    fun getWorkoutTitles(): Flow<List<WorkoutTitle>>

    suspend fun getWorkoutTitleById(id: Int): WorkoutTitle?

    suspend fun insertWorkoutTitle(workoutTitle: WorkoutTitle)

    suspend fun deleteWorkoutTitle(workoutTitle: WorkoutTitle)
}