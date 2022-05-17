package com.paulvickers.fit4life.presentation.workout_titles.add_edit_title

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.paulvickers.fit4life.presentation.destinations.WorkoutTitleScreenDestination
import com.paulvickers.fit4life.presentation.shared_components.F4LButton
import com.paulvickers.fit4life.ui.theme.F4LLightOrange
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
    val scaffoldState = rememberScaffoldState()
    var textState by rememberSaveable { mutableStateOf(workoutTitleTitle) }
    var dayState by rememberSaveable { mutableStateOf("") }
    var weekState by rememberSaveable { mutableStateOf("") }

    Scaffold(
        scaffoldState = scaffoldState
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = textState,
                onValueChange = {
                    textState = it
                },
                textStyle = TextStyle(
                    color = F4LLightOrange,
                    fontSize = 18.sp
                ),
                label = { Text(text = "Workout Name") },
//                modifier = Modifier
//                    .padding(16.dp),
                singleLine = true
            )
//            OutlinedTextField(
//                value = weekState,
//                onValueChange = {
//                    weekState = it
//                },
//                textStyle = TextStyle(
//                    color = F4LLightOrange,
//                    fontSize = 18.sp
//                ),
//                label = { Text(text = "Number of weeks") },
////                modifier = Modifier
////                    .padding(16.dp),
//                singleLine = true
//            )
//            OutlinedTextField(
//                value = dayState,
//                onValueChange = {
//                    dayState = it
//                },
//                textStyle = TextStyle(
//                    color = F4LLightOrange,
//                    fontSize = 18.sp
//                ),
//                label = { Text(text = "Number of days") },
////                modifier = Modifier
////                    .padding(16.dp),
//                singleLine = true
//            )
            F4LButton(
                text = "Save",
                onClick = {
                    if (textState.isNotBlank()
//                        && dayState.isNotBlank() && weekState.isNotBlank()
                    ) {
                        viewModel.addWorkoutTitle(workoutTitleId, textState)
//                        viewModel.addWorkoutWeeks(numOfDays = weekState.toInt())
//                        viewModel.addWorkoutDays(dayState.toInt())
                        navigator.navigate(
                            WorkoutTitleScreenDestination(
//                                workoutTitleId,
//                                workoutTitleTitle
                            )
                        )
                    } else {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                "Fields cannot be empty", null, SnackbarDuration.Short
                            )
                        }
                    }
                }
            )
        }
    }
}