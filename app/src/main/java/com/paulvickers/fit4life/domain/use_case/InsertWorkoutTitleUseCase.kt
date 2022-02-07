package com.paulvickers.fit4life.domain.use_case

import com.paulvickers.fit4life.domain.model.WorkoutTitle
import com.paulvickers.fit4life.domain.repository.WorkoutTitleRepository

class InsertWorkoutTitleUseCase(
    private val repository: WorkoutTitleRepository
) {

    suspend operator fun invoke(workoutTitle: WorkoutTitle) {
        return repository.insertWorkoutTitle(workoutTitle)
    }

}