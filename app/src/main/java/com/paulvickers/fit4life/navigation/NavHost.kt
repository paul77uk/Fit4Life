package com.paulvickers.fit4life.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.paulvickers.fit4life.presentation.workout_days.add_edit_days.AddDayScreen
import com.paulvickers.fit4life.presentation.workout_days.day_list.WorkoutDayScreen
import com.paulvickers.fit4life.presentation.workout_titles.add_edit_title.AddTitleScreen
import com.paulvickers.fit4life.presentation.workout_titles.workout_title_list.WorkoutTitleScreen

@Composable
fun NavSetUp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.WorkoutTitleScreen.route
    ) {
        composable(route = Screen.WorkoutTitleScreen.route) {
            WorkoutTitleScreen(navController)
        }
        composable(
            route = Screen.DayScreen.route
                    + "?workoutTitle={workoutTitle}&workoutTitleId={workoutTitleId}",
            arguments = listOf(
                navArgument(
                    name = "workoutTitle"
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(
                    name = "workoutTitleId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
            )
        ) {
//            val color = it.arguments?.getInt("noteColor") ?: -1
            WorkoutDayScreen(navController)
        }
        composable(
            route = Screen.AddTitleScreen.route
                    + "?workoutTitleId={workoutTitleId}",
            arguments = listOf(
                navArgument(
                    name = "workoutTitleId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            AddTitleScreen(navController)
        }
        composable(
            route = Screen.AddDayScreen.route
                    + "?dayId={dayId}&workoutTitleId={workoutTitleId}",
            arguments = listOf(
                navArgument(
                    name = "dayId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(
                    name = "workoutTitleId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
            )
        ) {
            AddDayScreen(navController)
        }
    }
}
