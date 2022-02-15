package com.paulvickers.fit4life.presentation.workout_days.add_edit_days

sealed class AddDayEvent {
    data class EnteredDay(val value: String): AddDayEvent()
    object InsertDay: AddDayEvent()
    object UpdateDay: AddDayEvent()
}
