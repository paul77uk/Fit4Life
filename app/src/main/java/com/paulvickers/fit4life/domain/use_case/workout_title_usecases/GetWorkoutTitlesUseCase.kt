package com.paulvickers.fit4life.domain.use_case.workout_title_usecases

import com.paulvickers.fit4life.domain.model.WorkoutTitle
import com.paulvickers.fit4life.domain.repository.WorkoutTitleRepository
import kotlinx.coroutines.flow.Flow

class GetWorkoutTitlesUseCase(
    private val repository: WorkoutTitleRepository
) {

    operator fun invoke(): Flow<List<WorkoutTitle>> {
        return repository.getWorkoutTitles()
    }

}