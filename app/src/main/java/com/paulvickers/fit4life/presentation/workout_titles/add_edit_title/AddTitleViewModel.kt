package com.paulvickers.fit4life.presentation.workout_titles.add_edit_title

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulvickers.fit4life.domain.model.WorkoutTitle
import com.paulvickers.fit4life.domain.use_case.workout_title_usecases.WorkoutTitleUseCases
import com.paulvickers.fit4life.utils.InvalidInputException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTitleViewModel @Inject constructor(
    private val workoutTitleUseCases: WorkoutTitleUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _workoutTitleState = mutableStateOf(
        WorkoutTitleTextFieldState(
        )
    )
    val workoutTitleState: State<WorkoutTitleTextFieldState> = _workoutTitleState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentWorkoutTitleId: Int? = null

    init {
        savedStateHandle.get<Int>("workoutTitleId")?.let { workoutTitleId ->
            if (workoutTitleId != -1) {
                viewModelScope.launch {
                    workoutTitleUseCases.getWorkoutTitleByIdUseCase(workoutTitleId)
                        ?.also { WorkoutTitle ->
                            currentWorkoutTitleId = WorkoutTitle.id
                            _workoutTitleState.value = workoutTitleState.value.copy(
                                text = WorkoutTitle.title,
                                isAdd = false
                            )
                        }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object InsertWorkoutTitle : UiEvent()
        object UpdateWorkoutTitle : UiEvent()
    }

    fun onEvent(event: AddWorkoutTitleEvent) {
        when (event) {
            is AddWorkoutTitleEvent.EnteredTitle ->
                _workoutTitleState.value = workoutTitleState.value.copy(
                    text = event.value
                )
            AddWorkoutTitleEvent.InsertWorkoutTitle ->
                viewModelScope.launch {
                    try {
                        workoutTitleUseCases.insertWorkoutTitleUseCase(
                            WorkoutTitle(
                                title = _workoutTitleState.value.text,
                                id = currentWorkoutTitleId
                            )
                        )
                        _eventFlow.emit(UiEvent.InsertWorkoutTitle)
                    } catch (e: InvalidInputException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Workout title not saved"
                            )
                        )
                    }
                }
            AddWorkoutTitleEvent.UpdateWorkoutTitle ->
                viewModelScope.launch {
                    try {
                        workoutTitleUseCases.updateWorkoutTitleUseCase(
                            WorkoutTitle(
                                title = workoutTitleState.value.text,
                                id = currentWorkoutTitleId
                            )
                        )
                        _eventFlow.emit(UiEvent.UpdateWorkoutTitle)
                    } catch (e: InvalidInputException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Workout title not saved"
                            )
                        )
                    }
                }
        }
    }

}