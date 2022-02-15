package com.paulvickers.fit4life.domain.use_case.workout_day_use_cases

import com.paulvickers.fit4life.domain.model.WorkoutDay
import com.paulvickers.fit4life.domain.repository.WorkoutDayRepository
import kotlinx.coroutines.flow.Flow

class GetDaysOfWorkoutUseCase(
    private val repository: WorkoutDayRepository
) {

    suspend operator fun invoke(workoutTitleId: Int): Flow<List<WorkoutDay>> {
        return repository.getDaysOfWorkout(workoutTitleId)
    }

}