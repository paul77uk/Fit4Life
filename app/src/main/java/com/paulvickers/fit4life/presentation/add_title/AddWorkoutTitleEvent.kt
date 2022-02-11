package com.paulvickers.fit4life.presentation.add_title

import androidx.compose.ui.focus.FocusState

sealed class AddWorkoutTitleEvent {
    data class EnteredTitle(val value: String): AddWorkoutTitleEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddWorkoutTitleEvent()
    object InsertWorkoutTitle: AddWorkoutTitleEvent()
    object UpdateWorkoutTitle: AddWorkoutTitleEvent()
}
