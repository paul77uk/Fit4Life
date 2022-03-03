package com.paulvickers.fit4life.presentation.exercise_titles.add_edit_exercises

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulvickers.fit4life.data.models.ExerciseTitle
import com.paulvickers.fit4life.data.repositories.ExerciseTitleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddExerciseViewModel @Inject constructor(
    private val exerciseTitleRepository: ExerciseTitleRepository,
) : ViewModel() {

    fun addExerciseTitle(exerciseId: Int, exerciseTitle: String, dayId: Int) {
        viewModelScope.launch {
            if (exerciseId != -1) {
                exerciseTitleRepository.updateExerciseTitle(
                    ExerciseTitle(
                        id = exerciseId,
                        title = exerciseTitle,
                        dayId = dayId
                    )
                )
            } else {
                exerciseTitleRepository.insertExerciseTitle(
                    ExerciseTitle(
                        title = exerciseTitle,
                        dayId = dayId
                    )
                )
            }
        }
    }

}