package com.paulvickers.fit4life.domain.use_case.workout_day_use_cases

import com.paulvickers.fit4life.domain.model.WorkoutDay
import com.paulvickers.fit4life.domain.repository.WorkoutDayRepository

class UpdateWorkoutDayUseCase(
    private val repository: WorkoutDayRepository
) {

    suspend operator fun invoke(workoutDay: WorkoutDay) {
        return repository.updateWorkoutDay(workoutDay)
    }

}