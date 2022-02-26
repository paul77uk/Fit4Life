package com.paulvickers.fit4life.presentation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.paulvickers.fit4life.MainActivity
import com.paulvickers.fit4life.di.AppModule
import com.paulvickers.fit4life.navigation.Screen
import com.paulvickers.fit4life.presentation.workout_days.add_edit_days.AddDayScreen
import com.paulvickers.fit4life.presentation.workout_days.day_list.WorkoutDayScreen
import com.paulvickers.fit4life.presentation.workout_titles.add_edit_title.AddTitleScreen
import com.paulvickers.fit4life.presentation.workout_titles.workout_title_list.WorkoutTitleScreen
import com.paulvickers.fit4life.ui.theme.Fit4LifeTheme
import com.paulvickers.fit4life.utils.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class WorkoutTitleEndToEndTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            Fit4LifeTheme {
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
        }
    }

    @Test
    fun saveWorkoutTitle_editAfterwards() {
        composeRule.onNodeWithContentDescription("Add").performClick()

        composeRule
            .onNodeWithTag(TestTags.WORKOUT_TITLE_TEXT_FIELD)
            .performTextInput("test-title")
        composeRule.onNodeWithContentDescription("Save").performClick()

        composeRule.onNodeWithText("test-title").assertIsDisplayed()
//        composeRule.onNodeWithText("test-title").performClick()

    }


}