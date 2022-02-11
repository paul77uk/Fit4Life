package com.paulvickers.fit4life.presentation.workout_titles

import com.paulvickers.fit4life.domain.model.WorkoutTitle

data class WorkoutTitleState(
    val workoutTitle: List<WorkoutTitle> = emptyList()
)