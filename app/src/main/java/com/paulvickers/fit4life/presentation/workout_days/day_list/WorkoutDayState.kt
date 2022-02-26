package com.paulvickers.fit4life.presentation.workout_days.day_list

import com.paulvickers.fit4life.data.models.WorkoutDay

data class WorkoutDayState(
    val workoutDay: List<WorkoutDay> = emptyList()
)