package com.paulvickers.fit4life.presentation.workout_days.add_edit_days

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination
@Composable
fun AddDayScreen(
    dayId: Int,
    day: String,
    workoutTitleId: Int,
    navigator: DestinationsNavigator,
    viewModel: AddDayViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
//    val dayState = viewModel.workoutDayState.value
    val scaffoldState = rememberScaffoldState()
    var textState by rememberSaveable { mutableStateOf(day) }
//    val workoutTitleId = navController.currentBackStackEntry?.arguments?.getString("workoutTitleId")

//    LaunchedEffect(key1 = true) {
//        viewModel.eventFlow.collectLatest {
//            when (it) {
//                AddDayViewModel.UiEvent.InsertWorkoutDay ->
//                    navController.navigateUp()
//                is AddDayViewModel.UiEvent.ShowSnackbar ->
//                    scaffoldState.snackbarHostState.showSnackbar(
//                        message = it.message
//                    )
//                AddDayViewModel.UiEvent.UpdateWorkoutDay ->
//                    navController.navigateUp()
//            }
//        }
//    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            OutlinedTextField(
//                value = dayState.text,
//                onValueChange = { viewModel.onEvent(AddDayEvent.EnteredDay(it)) },
//                label = { Text(text = "Enter day title") },
//                modifier = Modifier
//                    .padding(16.dp),
//                singleLine = true
//            )
            OutlinedTextField(
                value = textState,
                onValueChange = {
                    textState = it
                },
                label = { Text(text = "Enter day title") },
                modifier = Modifier
                    .padding(16.dp),
                singleLine = true
            )
            Button(
                onClick = {
                    if (textState.isNotBlank()) {
                        viewModel.addUpdateDay(
                            dayId = dayId,
                            day = textState,
                            workoutTitleId = workoutTitleId
                        )
                        navigator.popBackStack()
                    } else {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                "Day title cannot be empty", null, SnackbarDuration.Short
                            )
                        }
//                        viewModel.onEvent(AddDayEvent.InsertDay)
//                    else
//                        viewModel.onEvent(AddDayEvent.UpdateDay)
                    }
                }
            ) {
                Text(text = "Save")
            }
        }
    }
}