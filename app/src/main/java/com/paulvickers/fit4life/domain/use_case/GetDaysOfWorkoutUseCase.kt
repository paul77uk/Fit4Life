package com.paulvickers.fit4life.domain.use_case

import com.paulvickers.fit4life.data.relations.WorkoutWithDays
import com.paulvickers.fit4life.domain.model.WorkoutDay
import com.paulvickers.fit4life.domain.model.WorkoutTitle
import com.paulvickers.fit4life.domain.repository.WorkoutDayRepository
import com.paulvickers.fit4life.domain.repository.WorkoutTitleRepository
import kotlinx.coroutines.flow.Flow

class GetDaysOfWorkoutUseCase(
    private val repository: WorkoutDayRepository
) {

    suspend operator fun invoke(workoutTitleId: Int): Flow<List<WorkoutDay>> {
        return repository.getDaysOfWorkout(workoutTitleId)
    }

}