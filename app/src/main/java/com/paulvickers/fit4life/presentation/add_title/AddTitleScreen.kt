package com.paulvickers.fit4life.presentation.add_title

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
fun AddTitleScreen(
    navController: NavController,
    viewModel: AddTitleViewModel = hiltViewModel()
) {
    val titleState = viewModel.workoutTitleState.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest {
            when (it) {
                AddTitleViewModel.UiEvent.InsertWorkoutTitle ->
                    navController.navigateUp()
                is AddTitleViewModel.UiEvent.ShowSnackbar ->
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = it.message
                    )
                AddTitleViewModel.UiEvent.UpdateWorkoutTitle ->
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
                value = titleState.text,
                onValueChange = { viewModel.onEvent(AddWorkoutTitleEvent.EnteredTitle(it)) },
                label = { Text(text = "Enter Workout Name") },
                modifier = Modifier
                    .padding(16.dp),
                singleLine = true
            )
            Button(
                onClick = {
                    if (viewModel.workoutTitleState.value.isAdd)
                        viewModel.onEvent(AddWorkoutTitleEvent.InsertWorkoutTitle)
                    else
                        viewModel.onEvent(AddWorkoutTitleEvent.UpdateWorkoutTitle)
                }
            ) {
                Text(text = "Save")
            }
        }
    }
}