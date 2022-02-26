package com.paulvickers.fit4life.presentation.workout_days.day_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.paulvickers.fit4life.data.models.WorkoutDay
import com.paulvickers.fit4life.presentation.destinations.AddDayScreenDestination
import com.paulvickers.fit4life.presentation.shared_components.DialogWindow
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun WorkoutDayScreen(
    workoutTitleId: Int,
    workoutTitleTitle: String,
//    workoutTitle: WorkoutTitle,
    navigator: DestinationsNavigator,
    viewModel: WorkoutDayViewModel = hiltViewModel()
) {
//    LaunchedEffect(key1 = true) {
//        viewModel.getAllDays(workoutTitle.id?: -1)
//    }
    val days by viewModel.days.collectAsState()
    lateinit var day: WorkoutDay
    var openDialog by remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    viewModel.getDays(workoutTitleId)
    Scaffold(
        topBar = {
            TopAppBar(content = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = workoutTitleTitle,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h6,
                )
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navigator.navigate(
                    AddDayScreenDestination(dayId = -1, day = "", workoutTitleId = workoutTitleId)
//                    Screen.AddDayScreen.route
//                + "?dayId=-1&workoutTitleId=${workoutTitleId}"
                )
//                Log.d("TAG", "WorkoutTitleId: $workoutTitleId")
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        },
        scaffoldState = scaffoldState
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(days) {
                Card(
                    Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    border = BorderStroke(width = 0.5.dp, color = Color.LightGray)
                ) {
                    ListItem(
                        modifier = Modifier.clickable {

                        },
                        icon = {
                            IconButton(
                                onClick = {
                                    navigator.navigate(
                                        AddDayScreenDestination(dayId = it.id ?: -1, day = it.day, workoutTitleId = workoutTitleId)
//                                        Screen.AddDayScreen.route
//                                                + "?dayId=${it.id}&workoutTitleId=${it.workoutTitleId}"
                                    )
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit Day",
                                    tint = MaterialTheme.colors.onSurface
                                )
                            }
                        },
                        text = {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = it.day,
                                textAlign = TextAlign.Center
                            )
                        },
                        trailing = {
                            IconButton(
                                onClick = {
                                    openDialog = true
                                    day = it
//                                    viewModel.deleteDay(it)
//                                    scope.launch {
//                                        val result = scaffoldState.snackbarHostState.showSnackbar(
//                                            message = "Day deleted",
//                                            actionLabel = "Undo"
//                                        )
//                                        if (result == SnackbarResult.ActionPerformed) { // if clicked on snackbar
////                                            viewModel.restoreDay()
//                                        }
//                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete Day",
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
                    viewModel.deleteDay(day)
                    openDialog = false
                },
                titleToDelete = "day"
            )
        }
    }
}

//@Preview
//@Composable
//fun WorkoutDayScreenPrev() {
//    WorkoutDayScreen()
//}