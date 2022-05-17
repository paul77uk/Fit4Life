package com.paulvickers.fit4life.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.paulvickers.fit4life.R
import com.paulvickers.fit4life.presentation.destinations.AddTitleScreenDestination
import com.paulvickers.fit4life.presentation.destinations.WorkoutTitleScreenDestination
import com.paulvickers.fit4life.presentation.shared_components.DestinationCard
import com.paulvickers.fit4life.presentation.shared_components.TopBarText
import com.paulvickers.fit4life.presentation.workout_days.day_list.WorkoutDayViewModel
import com.paulvickers.fit4life.presentation.workout_titles.workout_title_list.WorkoutTitleViewModel
import com.paulvickers.fit4life.ui.theme.F4LLightGrey
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(start = true)
fun MainScreen(
    navigator: DestinationsNavigator,
//    viewModel: WorkoutTitleViewModel = hiltViewModel(),
//dayViewModel: WorkoutDayViewModel = hiltViewModel()
) {
    MainScreenScaffold(navigator)
//    { navigator.navigate(WorkoutTitleScreenDestination()) }
//    viewModel.getWorkoutTitles()
//    dayViewModel.getExerciseForSet()
}

@Composable
private fun MainScreenScaffold(
    navigator: DestinationsNavigator,
    viewModel: WorkoutTitleViewModel = hiltViewModel(),
    workoutDayViewModel: WorkoutDayViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = { TopBarText("FIT 4 LIFE") },
    ) {
        Column {
            LazyColumn(
                Modifier.padding(it),
            ) {
                item {
                    DestinationCard(
                        text = "Workouts",
                        clickable = {
                            navigator.navigate(WorkoutTitleScreenDestination())
//                            viewModel.getWorkoutTitles()
//                            workoutDayViewModel.getDays()
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    DestinationCard(
                        text = "Exercises",
                        clickable = { }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    DestinationCard(
                        text = "History",
                        clickable = { }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    DestinationCard(
                        text = "1 Rep Max",
                        clickable = { }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    DestinationCard(
                        text = "Working Weight",
                        clickable = { }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    DestinationCard(
                        text = "Create New Workout",
                        clickable = { navigator.navigate(AddTitleScreenDestination("", -1)) }
                    )
                }
            }
        }
    }
}

@Composable
fun LogoImage() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painterResource(R.drawable.logo5),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(8.dp),
            //        alignment = Alignment.Center,
            contentScale = ContentScale.FillWidth,
//            alpha = 0.85f,
            colorFilter = ColorFilter.tint(
                F4LLightGrey
            )
        )
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TopBarTextPrev() {
    TopBarText("FIT 4 LIFE")
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DestinationCardPrev() {
    DestinationCard(text = "Workouts", clickable = {})
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ImagePrev() {
    LogoImage()
}

//@Preview()
//@Composable()
//fun MainScreenPrev() {
//    Fit4LifeTheme {
//        MainScreenScaffold {}
//    }
//}