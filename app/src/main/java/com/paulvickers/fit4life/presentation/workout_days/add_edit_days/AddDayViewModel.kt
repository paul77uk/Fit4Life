package com.paulvickers.fit4life.presentation.workout_days.add_edit_days

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulvickers.fit4life.domain.model.WorkoutDay
import com.paulvickers.fit4life.domain.use_case.workout_day_use_cases.WorkoutDayUseCases
import com.paulvickers.fit4life.utils.InvalidInputException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddDayViewModel @Inject constructor(
    private val workoutDayUseCases: WorkoutDayUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _workoutDayState = mutableStateOf(
        WorkoutDayTextFieldState(
        )
    )
    val workoutDayState: State<WorkoutDayTextFieldState> = _workoutDayState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentWorkoutDayId: Int? = null

    val workoutTitleId = savedStateHandle.get<Int>("workoutTitleId")
    val dayId = savedStateHandle.get<Int>("dayId")

    init {
        savedStateHandle.get<Int>("dayId")?.let { workoutDayId ->
            if (workoutDayId != -1) {
                viewModelScope.launch {
                    workoutDayUseCases.getWorkoutDayByIdUseCase(workoutDayId)
                        ?.also { WorkoutDay ->
                            currentWorkoutDayId = WorkoutDay.id
                            _workoutDayState.value = workoutDayState.value.copy(
                                text = WorkoutDay.day,
                                isAdd = false
                            )
                        }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object InsertWorkoutDay : UiEvent()
        object UpdateWorkoutDay : UiEvent()
    }

    fun onEvent(event: AddDayEvent) {
        when (event) {
            is AddDayEvent.EnteredDay ->
                _workoutDayState.value = workoutDayState.value.copy(
                    text = event.value
                )
            AddDayEvent.InsertDay ->
                viewModelScope.launch {
                    try {
                        workoutDayUseCases.insertWorkoutDayUseCase(
                            WorkoutDay(
                                id = currentWorkoutDayId,
                                day = _workoutDayState.value.text,
                                workoutTitleId = workoutTitleId ?: -1
                            )
                        )
                        _eventFlow.emit(UiEvent.InsertWorkoutDay)
                    } catch (e: InvalidInputException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Day not saved"
                            )
                        )
                    }
                }
            AddDayEvent.UpdateDay ->
                viewModelScope.launch {
                    try {
                        workoutDayUseCases.updateWorkoutDayUseCase(
                            WorkoutDay(
                                id = currentWorkoutDayId,
                                day = _workoutDayState.value.text,
                                workoutTitleId = workoutTitleId ?: -1
                            )
                        )
                        _eventFlow.emit(UiEvent.UpdateWorkoutDay)
                    } catch (e: InvalidInputException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Day not saved"
                            )
                        )
                    }
                }
        }
    }

}