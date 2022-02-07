package com.paulvickers.fit4life.data.repository

import com.paulvickers.fit4life.data.data_source.WorkoutTitleDao
import com.paulvickers.fit4life.domain.model.WorkoutTitle
import com.paulvickers.fit4life.domain.repository.WorkoutTitleRepository
import kotlinx.coroutines.flow.Flow

class WorkoutTitleRepositoryImpl(
    private val dao: WorkoutTitleDao
) : WorkoutTitleRepository {

    override fun getWorkoutTitles(): Flow<List<WorkoutTitle>> {
        return dao.getWorkoutTitles()
    }

    override suspend fun getWorkoutTitleById(id: Int): WorkoutTitle? {
        return dao.getWorkoutTitleById(id)
    }

    override suspend fun insertWorkoutTitle(workoutTitle: WorkoutTitle) {
        return dao.insertWorkoutTitle(workoutTitle)
    }

    override suspend fun deleteWorkoutTitle(workoutTitle: WorkoutTitle) {
        return dao.deleteWorkoutTitle(workoutTitle)
    }

}