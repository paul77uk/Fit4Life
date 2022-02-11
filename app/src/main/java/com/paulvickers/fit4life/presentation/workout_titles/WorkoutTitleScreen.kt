package com.paulvickers.fit4life.presentation.workout_titles

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.paulvickers.fit4life.navigation.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WorkoutTitleScreen(
    navController: NavController,
    viewModel: WorkoutTitleViewModel = hiltViewModel()
) {

//    val workoutTitles = viewModel.workoutTitles.collectAsState().value
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
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
            FloatingActionButton(
                onClick = {
                    navController.navigate(
                        Screen.AddTitleScreen.route
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        },
        scaffoldState = scaffoldState
    ) {
        LazyColumn(
            Modifier.padding(vertical = 4.dp),
        ) {
            items(state.workoutTitle) {
                Card(
                    Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    border = BorderStroke(width = 0.5.dp, color = Color.LightGray)
                ) {
                    ListItem(
                        modifier = Modifier.clickable {
                            navController.navigate(
                                Screen.DayScreen.route
                                        + "?workoutTitle=${it.title}&workoutId=${it.id}"
                            )
                        },
                        icon = {
                            IconButton(
                                onClick = {
                                    navController.navigate(
                                        Screen.AddTitleScreen.route
                                                + "?workoutTitleId=${it.id}"
                                    )
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit Workout",
                                    tint = MaterialTheme.colors.onSurface
                                )
                            }
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
                            IconButton(
                                onClick = {
                                    viewModel.onEvent(WorkoutTitleEvent.DeleteWorkoutTitles(it))
                                    scope.launch {
                                        val result = scaffoldState.snackbarHostState.showSnackbar(
                                            message = "Workout deleted",
                                            actionLabel = "Undo"
                                        )
                                        if (result == SnackbarResult.ActionPerformed) { // if clicked on snackbar
                                            viewModel.onEvent(WorkoutTitleEvent.RestoreWorkoutTitle)
                                        }
                                    }
                                }
                            ) {
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
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun ListItemPrev() {
    ListItem(
        icon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Delete Workout",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        },
        modifier = Modifier.clickable {},
        text =
        {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Workout",
                textAlign = TextAlign.Center
            )
        },
        trailing = {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Workout",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        }
    )
}