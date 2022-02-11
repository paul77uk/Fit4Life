package com.paulvickers.fit4life.presentation.add_title

data class WorkoutTitleTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true,
    val isAdd: Boolean = true
)
