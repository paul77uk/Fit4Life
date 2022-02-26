package com.paulvickers.fit4life.data.repositories

import com.paulvickers.fit4life.data.models.WorkoutTitle
import com.paulvickers.fit4life.domain.repository.WorkoutTitleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeWorkoutRepository :
    WorkoutTitleRepository {

    private val workouts = mutableListOf<WorkoutTitle>()

    override fun getWorkoutTitles(): Flow<List<WorkoutTitle>> {
        return flow { emit(workouts) }
    }

    override suspend fun getWorkoutTitleById(id: Int): WorkoutTitle? {
        return workouts.find { it.id == id }
    }

    override suspend fun insertWorkoutTitle(workoutTitle: WorkoutTitle) {
        workouts.add(workoutTitle)
    }

    override suspend fun updateWorkoutTitle(workoutTitle: WorkoutTitle) {
        workouts.add(workoutTitle.id!!, workoutTitle)
    }

    override suspend fun deleteWorkoutTitle(workoutTitle: WorkoutTitle) {
        workouts.remove(workoutTitle)
    }


}