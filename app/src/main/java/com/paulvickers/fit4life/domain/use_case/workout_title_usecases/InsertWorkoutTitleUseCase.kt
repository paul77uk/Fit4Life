package com.paulvickers.fit4life.domain.use_case.workout_title_usecases

import com.paulvickers.fit4life.domain.model.WorkoutTitle
import com.paulvickers.fit4life.domain.repository.WorkoutTitleRepository
import com.paulvickers.fit4life.utils.InvalidInputException

class InsertWorkoutTitleUseCase(
    private val repository: WorkoutTitleRepository
) {

    @Throws(InvalidInputException::class)
    suspend operator fun invoke(workoutTitle: WorkoutTitle) {
        if (workoutTitle.title.isBlank()) {
            throw InvalidInputException("The title cannot be empty.")
        }
        repository.insertWorkoutTitle(workoutTitle)
    }

}