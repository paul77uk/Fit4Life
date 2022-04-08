package com.paulvickers.fit4life.presentation.exercise_titles.exercise_title_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.paulvickers.fit4life.data.models.ExerciseTitle
import com.paulvickers.fit4life.presentation.destinations.AddExerciseScreenDestination
import com.paulvickers.fit4life.presentation.destinations.ExerciseTitleScreenDestination
import com.paulvickers.fit4life.presentation.destinations.SetsListScreenDestination
import com.paulvickers.fit4life.presentation.shared_components.DialogWindow
import com.paulvickers.fit4life.presentation.workout_days.day_list.WorkoutDayViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun ExerciseTitleScreen(
//    dayId: Int,
//    dayTitle: String,
    workoutTitleId: Int,
    navigator: DestinationsNavigator,
    viewModel: ExerciseTitleViewModel = hiltViewModel(),
    dayViewModel: WorkoutDayViewModel = hiltViewModel()
) {
    val exerciseTitles by viewModel.exerciseTitles.collectAsState()
    val days by dayViewModel.days.collectAsState()
    lateinit var exerciseTitle: ExerciseTitle
    var openDialog by remember { mutableStateOf(false) }
//    val currentScreen by remember { mutableStateOf(dayId) }
//    viewModel.getExerciseTitles(1)
//    dayViewModel.getDays(workoutTitleId) // experiment with kazwokout
    Scaffold(
        topBar = {
            Column {
//                TopAppBar(content = {
//                    Text(
//                        modifier = Modifier.fillMaxWidth(),
//                        text = dayTitle,
//                        textAlign = TextAlign.Center,
//                        style = MaterialTheme.typography.h6,
//                    )
//                })

                BottomAppBar() {
                    days.forEach {
                        BottomNavigationItem(
                            selected = true,
                            selectedContentColor = Color.White,
                            onClick = {
                                navigator.navigate(
                                    ExerciseTitleScreenDestination(
                                        it.id ?: -1
                                    //                                        , it.day
                                    )
                                )
                            },
                            icon = { Text(text = it.day) },
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier,
                onClick = {
                    navigator.navigate(
                        AddExerciseScreenDestination(
                            exerciseId = -1,
                            exerciseTitle = "",
                            dayId = 1
                        )
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(exerciseTitles) {
                Card(
                    Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    border = BorderStroke(width = 0.5.dp, color = Color.LightGray)
                ) {
                    ListItem(
                        modifier = Modifier.clickable {
                            navigator.navigate(
                                SetsListScreenDestination(
                                    exerciseId = it.id ?: -1,
                                    exerciseTitle = it.title,
                                )
                            )
                        },
                        icon = {
                            IconButton(
                                onClick = {
                                    navigator.navigate(
                                        AddExerciseScreenDestination(
                                            exerciseId = it.id ?: -1,
                                            exerciseTitle = it.title,
                                            dayId = 1
                                        )
                                    )
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit Exercise",
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
                            Row {
                                IconButton(
                                    onClick = {
                                        openDialog = true
                                        exerciseTitle = it
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete Exercise",
                                        tint = MaterialTheme.colors.onSurface
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }

        if (openDialog) {
            DialogWindow(
                dismiss = { openDialog = false },
                delete = {
                    viewModel.deleteExercise(exerciseTitle)
                    openDialog = false
                },
                titleToDelete = "exercise"
            )
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