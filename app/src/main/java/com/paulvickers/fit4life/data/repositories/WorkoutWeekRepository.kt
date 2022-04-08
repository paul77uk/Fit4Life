package com.paulvickers.fit4life.data.repositories

import com.paulvickers.fit4life.data.data_access_objects.WorkoutWeekDao
import com.paulvickers.fit4life.data.models.WorkoutWeek
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class WorkoutWeekRepository @Inject constructor(private val workoutWeekDao: WorkoutWeekDao) {

    fun getWeeksByWorkoutTitleId(workoutTitleId: Int): Flow<List<WorkoutWeek>> {
        return workoutWeekDao.getWeeksByWorkoutTitleId(workoutTitleId)
    }

    suspend fun insertWorkoutWeek(workoutWeek: WorkoutWeek) {
        workoutWeekDao.insertWorkoutWeek(workoutWeek)
    }

    suspend fun updateWorkoutWeek(workoutWeek: WorkoutWeek) {
        workoutWeekDao.updateWorkoutWeek(workoutWeek)
    }

    suspend fun deleteWorkoutWeek(workoutWeek: WorkoutWeek) {
        workoutWeekDao.deleteWorkoutWeek(workoutWeek)
    }

    val getMaxWeekId: Flow<Int> = workoutWeekDao.getMaxWeekId()

}