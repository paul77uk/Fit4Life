package com.paulvickers.fit4life.presentation.add_title

sealed class AddWorkoutTitleEvent {
    data class EnteredTitle(val value: String): AddWorkoutTitleEvent()
    object InsertWorkoutTitle: AddWorkoutTitleEvent()
    object UpdateWorkoutTitle: AddWorkoutTitleEvent()
}
