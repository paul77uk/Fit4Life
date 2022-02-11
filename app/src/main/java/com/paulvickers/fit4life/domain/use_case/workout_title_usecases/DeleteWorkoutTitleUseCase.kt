package com.paulvickers.fit4life.domain.use_case.workout_title_usecases

import com.paulvickers.fit4life.domain.model.WorkoutTitle
import com.paulvickers.fit4life.domain.repository.WorkoutTitleRepository

class DeleteWorkoutTitleUseCase(
    private val repository: WorkoutTitleRepository
) {

    suspend operator fun invoke(workoutTitle: WorkoutTitle) {
        return repository.deleteWorkoutTitle(workoutTitle)
    }

}