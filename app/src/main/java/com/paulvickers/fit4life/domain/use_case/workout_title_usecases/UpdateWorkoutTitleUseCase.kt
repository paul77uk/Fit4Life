package com.paulvickers.fit4life.domain.use_case.workout_title_usecases

import com.paulvickers.fit4life.domain.model.InvalidWorkoutTitleException
import com.paulvickers.fit4life.domain.model.WorkoutTitle
import com.paulvickers.fit4life.domain.repository.WorkoutTitleRepository

class UpdateWorkoutTitleUseCase(
    private val repository: WorkoutTitleRepository
) {

    @Throws(InvalidWorkoutTitleException::class)
    suspend operator fun invoke(workoutTitle: WorkoutTitle) {
        if (workoutTitle.title.isBlank()) {
            throw InvalidWorkoutTitleException("The title cannot be empty.")
        }
        repository.updateWorkoutTitle(workoutTitle)
    }

}