package com.paulvickers.fit4life.domain.use_case

import com.paulvickers.fit4life.domain.repository.WorkoutDayRepository

class DeleteWorkoutDaysByWorkoutTitleIdUseCase(
    private val repository: WorkoutDayRepository
) {

    suspend operator fun invoke(workoutTitle: Int) {
        return repository.deleteWorkoutDaysByWorkoutTitleId(workoutTitle)
    }

}