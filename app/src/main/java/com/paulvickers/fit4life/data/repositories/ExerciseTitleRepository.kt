package com.paulvickers.fit4life.data.repositories

import com.paulvickers.fit4life.data.data_access_objects.ExerciseTitleDao
import com.paulvickers.fit4life.data.models.ExerciseTitle
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class ExerciseTitleRepository @Inject constructor(private val exerciseTitleDao: ExerciseTitleDao) {

    val getExercises: Flow<List<ExerciseTitle>> = exerciseTitleDao.getExercises()

    suspend fun insertExerciseTitle(exerciseTitle: ExerciseTitle) {
        exerciseTitleDao.insertExerciseTitle(exerciseTitle)
    }

    suspend fun insertExerciseTitleList(exerciseTitleList: List<ExerciseTitle>) {
        exerciseTitleDao.insertExerciseTitleList(exerciseTitleList)
    }

    suspend fun updateExerciseTitle(exerciseTitle: ExerciseTitle) {
        exerciseTitleDao.updateExerciseTitle(exerciseTitle)
    }

    suspend fun deleteExerciseTitle(exerciseTitle: ExerciseTitle) {
        exerciseTitleDao.deleteExerciseTitle(exerciseTitle)
    }

}