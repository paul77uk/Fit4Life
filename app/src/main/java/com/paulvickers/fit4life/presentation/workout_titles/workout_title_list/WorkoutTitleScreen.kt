package com.paulvickers.fit4life.presentation.workout_titles.workout_title_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.paulvickers.fit4life.data.models.WorkoutTitle
import com.paulvickers.fit4life.presentation.destinations.AddTitleScreenDestination
import com.paulvickers.fit4life.presentation.destinations.WorkoutDayScreenDestination
import com.paulvickers.fit4life.presentation.shared_components.DialogWindow
import com.paulvickers.fit4life.utils.TestTags
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterialApi::class)
@Destination(start = true)
@Composable
fun WorkoutTitleScreen(
    navigator: DestinationsNavigator,
    viewModel: WorkoutTitleViewModel = hiltViewModel()
) {
    val allWorkoutTitles by viewModel.allWorkoutTitles.collectAsState()
    lateinit var workoutTitle: WorkoutTitle
    var openDialog by remember { mutableStateOf(false) }
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
                modifier = Modifier
                    .testTag(TestTags.FLOATING_BUTTON),
                onClick = {
                    navigator.navigate(
                        AddTitleScreenDestination(workoutTitleId = -1, workoutTitleTitle = "")
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        },
//        scaffoldState = scaffoldState
    ) {
        Column() {
            LazyColumn(
                Modifier.padding(vertical = 4.dp),
            ) {
                items(allWorkoutTitles) {
                    Card(
                        Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        border = BorderStroke(width = 0.5.dp, color = Color.LightGray)
                    ) {
                        ListItem(
                            modifier = Modifier.clickable {
                                navigator.navigate(
                                    WorkoutDayScreenDestination(it.id ?: -1, it.title)
                                )
                            },
                            icon = {
                                IconButton(
                                    onClick = {
                                        navigator.navigate(
                                            AddTitleScreenDestination(
                                                workoutTitleId = it.id ?: -1,
                                                workoutTitleTitle = it.title
                                            )
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
                                        openDialog = true
                                        workoutTitle = it
//                                        scope.launch {
//                                            val result = scaffoldState.snackbarHostState.showSnackbar(
//                                                message = "Are you sure you want to delete workout",
//                                                actionLabel = "Delete",
//                                            )
//                                            if (result == SnackbarResult.ActionPerformed) { // if clicked on snackbar
//                                                viewModel.onEvent(WorkoutTitleEvent.DeleteWorkoutTitles(it))
//                                            }
//                                        }
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

            if (openDialog) {
                DialogWindow(
                    dismiss = { openDialog = false },
                    delete = {
                        viewModel.deleteWorkoutTitle(workoutTitle)
//                        viewModel.onEvent(WorkoutTitleEvent.DeleteWorkoutTitles(workoutTitle))
                        openDialog = false
                    },
                    titleToDelete = "workout"
                )
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