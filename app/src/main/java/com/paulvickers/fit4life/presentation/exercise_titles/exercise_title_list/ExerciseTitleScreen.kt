package com.paulvickers.fit4life.presentation.exercise_titles.exercise_title_list

import android.util.Log
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.paulvickers.fit4life.data.models.WorkoutTitle
import com.paulvickers.fit4life.utils.TestTags
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun ExerciseTitleScreen(
    viewModel: ExerciseTitleViewModel = hiltViewModel()
) {
    val exerciseTitles = viewModel.exerciseTitles.collectAsState().value
//    val scaffoldState = rememberScaffoldState()
//    val scope = rememberCoroutineScope()
    var workoutTitle = WorkoutTitle(title = "")
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
//                    navController.navigate(
//                        Screen.AddTitleScreen.route
//                    )
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
            val openDialog = remember { mutableStateOf(false)  }
            LazyColumn(
                Modifier.padding(vertical = 4.dp),
            ) {
                items(exerciseTitles) {
                    Card(
                        Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        border = BorderStroke(width = 0.5.dp, color = Color.LightGray)
                    ) {
                        ListItem(
                            modifier = Modifier.clickable {
//                                navController.navigate(
//                                    Screen.DayScreen.route
//                                            + "?workoutTitle=${it.title}&workoutTitleId=${it.id}"
//                                )
                                Log.d("2TAG", "WorkoutTitleId: ${it.id}")
                            },
                            icon = {
                                IconButton(
                                    onClick = {
//                                        navController.navigate(
//                                            Screen.AddTitleScreen.route
//                                                    + "?workoutTitleId=${it.id}"
//                                        )
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
//                                        openDialog.value = true
//                                        workoutTitle = it
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

//            if (openDialog.value) {
//               DialogWindow (
//                   dismiss = { openDialog.value = false },
//                   delete = {
//                       viewModel.onEvent(WorkoutTitleEvent.DeleteWorkoutTitles(workoutTitle))
//                       openDialog.value = false
//                   }
//               )
//            }
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