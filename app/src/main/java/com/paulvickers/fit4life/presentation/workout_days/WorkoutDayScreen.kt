package com.paulvickers.fit4life.presentation.workout_days

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.paulvickers.fit4life.presentation.workout_titles.WorkoutTitleViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WorkoutDayScreen(navController: NavController) {
//    val viewModel: WorkoutDayViewModel = hiltViewModel()
//    val workoutDays = viewModel.workoutDays.collectAsState().value
    val argument = navController.currentBackStackEntry?.arguments?.getString("workoutTitle")
    val viewModel: WorkoutDayViewModel = hiltViewModel()
    val workoutDays = viewModel.workoutDays.collectAsState().value
    Scaffold(
        topBar = {
            TopAppBar(content = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = argument ?: "",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h6,
                )
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(workoutDays) {
                ListItem(
                    modifier = Modifier.clickable {

                    },
                    text =
                    {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = it.day,
                            textAlign = TextAlign.Center
                        )
                    }

                )
            }
        }
    }
}

//@Preview
//@Composable
//fun WorkoutDayScreenPrev() {
//    WorkoutDayScreen()
//}