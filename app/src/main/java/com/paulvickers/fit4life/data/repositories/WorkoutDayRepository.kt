package com.paulvickers.fit4life.data.repositories

import com.paulvickers.fit4life.data.data_access_objects.WorkoutDayDao
import com.paulvickers.fit4life.data.models.WorkoutDay
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class WorkoutDayRepository @Inject constructor(private val workoutDayDao: WorkoutDayDao) {

    fun getDaysByWorkoutWeekId(workoutWeekId: Int): Flow<List<WorkoutDay>> {
        return workoutDayDao.getDaysByWorkoutWeekId(workoutWeekId)
    }

    suspend fun insertWorkoutDay(workoutDay: WorkoutDay) {
        workoutDayDao.insertWorkoutDay(workoutDay)
    }

    suspend fun updateWorkoutDay(workoutDay: WorkoutDay) {
        workoutDayDao.updateWorkoutDay(workoutDay)
    }

    suspend fun deleteWorkoutDay(workoutDay: WorkoutDay) {
        workoutDayDao.deleteWorkoutDay(workoutDay)
    }

    val getMinDayId: Flow<Int> = workoutDayDao.getMinDayId()

}