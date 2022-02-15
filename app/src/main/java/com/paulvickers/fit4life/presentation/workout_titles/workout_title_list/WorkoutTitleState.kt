package com.paulvickers.fit4life.presentation.workout_titles.workout_title_list

import com.paulvickers.fit4life.domain.model.WorkoutTitle

data class WorkoutTitleState(
    val workoutTitle: List<WorkoutTitle> = emptyList()
)