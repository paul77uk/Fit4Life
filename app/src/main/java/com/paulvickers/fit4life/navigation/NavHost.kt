package com.paulvickers.fit4life.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.paulvickers.fit4life.presentation.add_title.AddTitleScreen
import com.paulvickers.fit4life.presentation.edit_title.EditTitleScreen
import com.paulvickers.fit4life.presentation.workout_days.WorkoutDayScreen
import com.paulvickers.fit4life.presentation.workout_titles.WorkoutTitleScreen

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
                    + "?workoutTitle={workoutTitle}&workoutId={workoutId}",
            arguments = listOf(
                navArgument(
                    name = "workoutTitle"
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(
                    name = "workoutId"
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
            route = Screen.EditTitleScreen.route
                    + "?workoutTitle={workoutTitle}&workoutId={workoutId}",
            arguments = listOf(
                navArgument(
                    name = "workoutTitle"
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(
                    name = "workoutId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
            )
        ) {
            EditTitleScreen(navController)
        }
    }
}
