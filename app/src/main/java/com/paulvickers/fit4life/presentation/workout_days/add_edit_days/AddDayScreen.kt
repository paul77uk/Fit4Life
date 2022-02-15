package com.paulvickers.fit4life.presentation.workout_days.add_edit_days

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddDayScreen(
    navController: NavController,
    viewModel: AddDayViewModel = hiltViewModel()
) {
    val dayState = viewModel.workoutDayState.value
    val scaffoldState = rememberScaffoldState()
    val workoutTitleId = navController.currentBackStackEntry?.arguments?.getString("workoutTitleId")

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest {
            when (it) {
                AddDayViewModel.UiEvent.InsertWorkoutDay ->
                    navController.navigateUp()
                is AddDayViewModel.UiEvent.ShowSnackbar ->
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = it.message
                    )
                AddDayViewModel.UiEvent.UpdateWorkoutDay ->
                    navController.navigateUp()
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = dayState.text,
                onValueChange = { viewModel.onEvent(AddDayEvent.EnteredDay(it)) },
                label = { Text(text = "Enter day title") },
                modifier = Modifier
                    .padding(16.dp),
                singleLine = true
            )
            Button(
                onClick = {
                    if (viewModel.workoutDayState.value.isAdd)
                        viewModel.onEvent(AddDayEvent.InsertDay)
                    else
                        viewModel.onEvent(AddDayEvent.UpdateDay)
                }
            ) {
                Text(text = "Save")
            }
        }
    }
}