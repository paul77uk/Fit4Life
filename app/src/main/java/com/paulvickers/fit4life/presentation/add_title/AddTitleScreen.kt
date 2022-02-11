package com.paulvickers.fit4life.presentation.add_title

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.paulvickers.fit4life.presentation.add_title.components.TransparentHintTextField
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddTitleScreen(
    navController: NavController,
    viewModel: AddTitleViewModel = hiltViewModel()
) {
    val titleState = viewModel.workoutTitleState.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest {
            when (it) {
                AddTitleViewModel.UiEvent.SaveWorkoutTitle ->
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
            TransparentHintTextField(
                text = titleState.text,
                hint = titleState.hint,
                onValueChange = {
                    viewModel.onEvent(AddWorkoutTitleEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddWorkoutTitleEvent.ChangeTitleFocus(it))
                },
                isHintVisible = titleState.isHintVisible,
                textStyle = MaterialTheme.typography.body1
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