package com.paulvickers.fit4life.navigation

sealed class Screen(val route: String) {
    object WorkoutTitleScreen: Screen("workout_title_screen")
    object DayScreen: Screen("day_screen")
    object AddTitleScreen: Screen("add_title_screen")
    object EditTitleScreen: Screen("edit_title_screen")
}
