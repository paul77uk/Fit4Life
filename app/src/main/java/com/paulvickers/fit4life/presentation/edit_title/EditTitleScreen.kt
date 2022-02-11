package com.paulvickers.fit4life.presentation.edit_title

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.paulvickers.fit4life.domain.model.WorkoutTitle

@Composable
fun EditTitleScreen(
    navController: NavController
) {
    val workoutTitle = navController.currentBackStackEntry?.arguments?.getString("workoutTitle")
    Scaffold {
        var workoutTitleInput by remember { mutableStateOf("") }
        val viewModel: EditTitleViewModel = hiltViewModel()
        val workoutTitle = viewModel.workoutTitle

        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = workoutTitleInput,
                onValueChange = { workoutTitleInput = it },
                label = { Text(workoutTitle.value.title) }
            )
            Button(
                onClick = {
                    viewModel.insertWorkoutTitle(
                        WorkoutTitle(
                            title = workoutTitleInput
                        ),
                    )
                    navController.popBackStack()
                }
            ) {
                Text(text = "Save")
            }
        }
    }
}