package com.paulvickers.fit4life.domain.use_case

import com.paulvickers.fit4life.data.relations.WorkoutWithDays
import com.paulvickers.fit4life.domain.model.WorkoutTitle
import com.paulvickers.fit4life.domain.repository.WorkoutTitleRepository
import kotlinx.coroutines.flow.Flow

class DeleteWorkoutTitleUseCase(
    private val repository: WorkoutTitleRepository
) {

    suspend operator fun invoke(workoutTitle: WorkoutTitle) {
        return repository.deleteWorkoutTitle(workoutTitle)
    }

}