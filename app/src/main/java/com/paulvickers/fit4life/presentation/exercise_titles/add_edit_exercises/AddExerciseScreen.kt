package com.paulvickers.fit4life.presentation.exercise_titles.add_edit_exercises

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
fun AddExerciseScreen(
    exerciseId: Int,
    exerciseTitle: String,
    dayId: Int,
    navigator: DestinationsNavigator,
    viewModel: AddExerciseViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    var textState by rememberSaveable { mutableStateOf(exerciseTitle) }

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
                label = { Text(text = "Enter exercise title") },
                modifier = Modifier
                    .padding(16.dp),
                singleLine = true
            )
            Button(
                onClick = {
                    if (textState.isNotBlank()) {
                        viewModel.addExerciseTitle(
                            exerciseId = exerciseId,
                            exerciseTitle = textState,
                            dayId = dayId,
                        )
                        navigator.popBackStack()
                    } else {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                "Exercise title cannot be empty", null, SnackbarDuration.Short
                            )
                        }
                    }
                }
            ) {
                Text(text = "Save")
            }
        }
    }
}