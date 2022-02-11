package com.paulvickers.fit4life.domain.use_case.workout_title_usecases

import com.paulvickers.fit4life.domain.model.WorkoutTitle
import com.paulvickers.fit4life.domain.repository.WorkoutTitleRepository

class GetWorkoutTitleByIdUseCase(
    private val repository: WorkoutTitleRepository
) {

    suspend operator fun invoke(id: Int): WorkoutTitle? {
        return repository.getWorkoutTitleById(id)
    }

}