package com.paulvickers.fit4life.presentation.workout_days.day_list

import com.paulvickers.fit4life.domain.model.WorkoutDay

data class WorkoutDayState(
    val workoutDay: List<WorkoutDay> = emptyList()
)