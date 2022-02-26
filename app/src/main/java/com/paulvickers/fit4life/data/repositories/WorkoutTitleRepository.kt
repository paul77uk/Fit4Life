package com.paulvickers.fit4life.data.repositories

import com.paulvickers.fit4life.data.data_access_objects.WorkoutTitleDao
import com.paulvickers.fit4life.data.models.WorkoutTitle
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped // will be alive for as long as the viewModel in which we use it - it's viewModelScope
class WorkoutTitleRepository @Inject constructor(private val workoutTitleDao: WorkoutTitleDao) {

    val getWorkoutTitles: Flow<List<WorkoutTitle>> = workoutTitleDao.getWorkoutTitles()

    suspend fun insertWorkoutTitle(workoutTitle: WorkoutTitle) {
        workoutTitleDao.insertWorkoutTitle(workoutTitle)
    }

    suspend fun updateWorkoutTitle(workoutTitle: WorkoutTitle) {
        workoutTitleDao.updateWorkoutTitle(workoutTitle)
    }

    suspend fun deleteWorkoutTitle(workoutTitle: WorkoutTitle) {
        workoutTitleDao.deleteWorkoutTitle(workoutTitle)
    }

}