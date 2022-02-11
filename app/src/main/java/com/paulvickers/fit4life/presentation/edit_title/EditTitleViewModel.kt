package com.paulvickers.fit4life.presentation.edit_title

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulvickers.fit4life.domain.model.WorkoutTitle
import com.paulvickers.fit4life.domain.use_case.workout_title_usecases.WorkoutTitleUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTitleViewModel @Inject constructor(
    private val workoutTitleUseCases: WorkoutTitleUseCases,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private var _workoutTitle = mutableStateOf(WorkoutTitle(title = ""))
    val workoutTitle = _workoutTitle

    init {
        savedStateHandle.get<Int>("workoutId")?.let { workoutId ->
            if (workoutId != -1) {
                viewModelScope.launch(Dispatchers.IO) {
                    _workoutTitle.value = workoutTitleUseCases.getWorkoutTitleByIdUseCase.invoke(workoutId)!!

                }
            }
        }
    }

    fun insertWorkoutTitle(workoutTitle: WorkoutTitle) =
        viewModelScope.launch {
            workoutTitleUseCases.insertWorkoutTitleUseCase.invoke(
                _workoutTitle.value
            )

        }

}