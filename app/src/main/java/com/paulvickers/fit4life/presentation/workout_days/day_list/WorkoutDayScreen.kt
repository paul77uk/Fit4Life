package com.paulvickers.fit4life.presentation.workout_days.day_list

import android.util.Log
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.paulvickers.fit4life.navigation.Screen
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WorkoutDayScreen(
    navController: NavController,
    viewModel: WorkoutDayViewModel = hiltViewModel()
) {
    val workoutDays = viewModel.workoutDaysState.value
    val workoutTitle = navController.currentBackStackEntry?.arguments?.getString("workoutTitle")
    val workoutTitleId = navController.currentBackStackEntry?.arguments?.getInt("workoutTitleId")
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
//    var workoutDay = WorkoutDay(day = "", workoutTitleId = -1)
    Scaffold(
        topBar = {
            TopAppBar(content = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = workoutTitle ?: "",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h6,
                )
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(
                    Screen.AddDayScreen.route
                + "?dayId=-1&workoutTitleId=${workoutTitleId}"
                )
                Log.d("TAG", "WorkoutTitleId: $workoutTitleId")
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
            items(workoutDays.workoutDay) {
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
                                    navController.navigate(
                                        Screen.AddDayScreen.route
                                                + "?dayId=${it.id}&workoutTitleId=${it.workoutTitleId}"
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
                                    viewModel.deleteDay(it)
                                    scope.launch {
                                        val result = scaffoldState.snackbarHostState.showSnackbar(
                                            message = "Day deleted",
                                            actionLabel = "Undo"
                                        )
                                        if (result == SnackbarResult.ActionPerformed) { // if clicked on snackbar
                                            viewModel.restoreDay()
                                        }
                                    }
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
    }
}

//@Preview
//@Composable
//fun WorkoutDayScreenPrev() {
//    WorkoutDayScreen()
//}