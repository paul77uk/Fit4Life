package com.paulvickers.fit4life.presentation.workout_titles

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.paulvickers.fit4life.navigation.Screen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WorkoutTitleScreen(
    navController: NavController
) {
    val viewModel: WorkoutTitleViewModel = hiltViewModel()
    val workoutTitles = viewModel.workoutTitles.collectAsState().value
    Scaffold(
        topBar = {
            TopAppBar(content = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Workouts",
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
            items(workoutTitles) {
                ListItem(
                    modifier = Modifier.clickable {
                        navController.navigate(
                            Screen.DayScreen.route
                                    + "?workoutTitle=${it.title}&workoutId=${it.id}"
                        )
                    },
                    text =
                    {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = it.title,
                            textAlign = TextAlign.Center
                        )
                    },
                    trailing = {
                        IconButton(onClick = { viewModel.deleteWorkoutTitleAndChildren(it) }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete Workout",
                                tint = MaterialTheme.colors.onSurface
                            )
                        }
                    }
                )
            }
        }
    }
}