package com.paulvickers.fit4life.presentation.workout_titles

import com.paulvickers.fit4life.domain.model.WorkoutTitle

sealed class WorkoutTitleEvent {
    data class DeleteWorkoutTitles(val workoutTitle: WorkoutTitle) : WorkoutTitleEvent()
    object RestoreWorkoutTitle : WorkoutTitleEvent()
    object GetWorkoutTitles : WorkoutTitleEvent()
}
