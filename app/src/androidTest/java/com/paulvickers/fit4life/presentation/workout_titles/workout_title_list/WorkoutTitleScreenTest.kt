package com.paulvickers.fit4life.presentation.workout_titles.workout_title_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.paulvickers.fit4life.MainActivity
import com.paulvickers.fit4life.di.AppModule
import com.paulvickers.fit4life.navigation.Screen
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
class WorkoutTitleScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            val navController = rememberNavController()
            Fit4LifeTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screen.WorkoutTitleScreen.route
                ) {
                    composable(route = Screen.WorkoutTitleScreen.route) {
                        WorkoutTitleScreen(navController = navController)
                    }
                }
            }
        }
    }

    @Test
    fun floatingActionButton_isVisible() {
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        composeRule.onNodeWithTag(TestTags.FLOATING_BUTTON).assertDoesNotExist()
//        composeRule.onNodeWithContentDescription("Add").performClick()
        composeRule.onNodeWithTag(TestTags.FLOATING_BUTTON).assertIsDisplayed()

    }

}