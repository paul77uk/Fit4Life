package com.paulvickers.fit4life.data.repository

import androidx.room.Query
import com.paulvickers.fit4life.data.data_source.WorkoutDayDao
import com.paulvickers.fit4life.data.data_source.WorkoutTitleDao
import com.paulvickers.fit4life.data.relations.WorkoutWithDays
import com.paulvickers.fit4life.domain.model.WorkoutDay
import com.paulvickers.fit4life.domain.model.WorkoutTitle
import com.paulvickers.fit4life.domain.repository.WorkoutDayRepository
import com.paulvickers.fit4life.domain.repository.WorkoutTitleRepository
import kotlinx.coroutines.flow.Flow

class WorkoutDayRepositoryImpl(
    private val dao: WorkoutDayDao
) : WorkoutDayRepository {

    override fun getWorkoutDays(): Flow<List<WorkoutDay>> {
        return dao.getWorkoutDays()
    }

    override suspend fun getWorkoutDayById(id: Int): WorkoutDay? {
        return dao.getWorkoutDayById(id)
    }

    override suspend fun insertWorkoutDay(workoutDay: WorkoutDay) {
        return dao.insertWorkoutDay(workoutDay)
    }

    override suspend fun deleteWorkoutDay(workoutDay: WorkoutDay) {
        return dao.deleteWorkoutDay(workoutDay)
    }

    override suspend fun deleteWorkoutDaysByWorkoutTitleId(workoutTitleId: Int) {
        return dao.deleteWorkoutDaysByWorkoutTitleId(workoutTitleId)
    }

    override suspend fun getDaysOfWorkout(workoutTitleId: Int): Flow<List<WorkoutDay>> {
        return dao.getDaysOfWorkout(workoutTitleId)
    }

}