package com.paulvickers.fit4life.presentation.workout_titles.add_edit_title

sealed class AddWorkoutTitleEvent {
    data class EnteredTitle(val value: String): AddWorkoutTitleEvent()
    object InsertWorkoutTitle: AddWorkoutTitleEvent()
    object UpdateWorkoutTitle: AddWorkoutTitleEvent()
}
