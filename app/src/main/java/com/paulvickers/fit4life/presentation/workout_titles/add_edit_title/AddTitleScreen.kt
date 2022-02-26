package com.paulvickers.fit4life.presentation.workout_titles.add_edit_title

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.paulvickers.fit4life.utils.TestTags
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

/*TODO:
   Add enter number of weeks and days textFields.
   Insert workout name on save button press
   then navigate to week screen passing numOfWeeks, numOfDays, and workoutTitleId arguments
   in the viewModel init block insert weeks and days according to the passed arguments
   using the passed id also to create the week
   */

@Composable
@Destination
fun AddTitleScreen(
    workoutTitleTitle: String,
    workoutTitleId: Int,
    navigator: DestinationsNavigator,
    viewModel: AddTitleViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
//    val titleState = viewModel.workoutTitleState.value
    val scaffoldState = rememberScaffoldState()
    var textState by rememberSaveable { mutableStateOf(workoutTitleTitle) }

//    LaunchedEffect(key1 = true) {
//        viewModel.eventFlow.collectLatest {
//            when (it) {
//                AddTitleViewModel.UiEvent.InsertWorkoutTitle ->
//                    navController.navigateUp()
//                is AddTitleViewModel.UiEvent.ShowSnackbar ->
//                    scaffoldState.snackbarHostState.showSnackbar(
//                        message = it.message
//                    )
//                AddTitleViewModel.UiEvent.UpdateWorkoutTitle ->
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
            OutlinedTextField(
                value = textState,
                onValueChange = {
                    textState = it
                },
                label = { Text(text = "Enter Workout Name") },
                modifier = Modifier
                    .padding(16.dp)
                    .testTag(TestTags.WORKOUT_TITLE_TEXT_FIELD),
                singleLine = true
            )
            Button(
                onClick = {
                    if (textState.isNotBlank()) {
                        viewModel.addUpdateWorkoutTitle(workoutTitleId, textState)
                        navigator.popBackStack()
                    } else {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                "Workout title cannot be empty", null, SnackbarDuration.Short
                            )
                        }
//                    if (viewModel.workoutTitleState.value.isAdd) {
//                        viewModel.onEvent(AddWorkoutTitleEvent.InsertWorkoutTitle)
//                    } else
//                        viewModel.onEvent(AddWorkoutTitleEvent.UpdateWorkoutTitle)
                    }
                }
            ) {
                Text(text = "Save")
            }
        }
    }
}