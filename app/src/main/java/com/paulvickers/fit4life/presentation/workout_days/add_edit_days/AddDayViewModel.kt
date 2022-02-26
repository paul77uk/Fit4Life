package com.paulvickers.fit4life.presentation.workout_days.add_edit_days

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulvickers.fit4life.data.models.WorkoutDay
import com.paulvickers.fit4life.data.repositories.WorkoutDayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddDayViewModel @Inject constructor(
    private val workoutDayRepository: WorkoutDayRepository,
//    savedStateHandle: SavedStateHandle
) : ViewModel() {

//    private val _workoutDayState = mutableStateOf(
//        WorkoutDayTextFieldState(
//        )
//    )
//    val workoutDayState: State<WorkoutDayTextFieldState> = _workoutDayState
//
//    private val _eventFlow = MutableSharedFlow<UiEvent>()
//    val eventFlow = _eventFlow.asSharedFlow()
//
//    private var currentWorkoutDayId: Int? = null
//
//    val workoutTitleId = savedStateHandle.get<Int>("workoutTitleId")
//
//    init {
//        savedStateHandle.get<Int>("dayId")?.let { workoutDayId ->
//            if (workoutDayId != -1) {
//                viewModelScope.launch {
//                    workoutDayRepository.getDaysByWorkoutTitleId(workoutDayId)
//                }
//            }
//        }
//    }
//
//    sealed class UiEvent {
//        data class ShowSnackbar(val message: String) : UiEvent()
//        object InsertWorkoutDay : UiEvent()
//        object UpdateWorkoutDay : UiEvent()
//    }
//
//    fun onEvent(event: AddDayEvent) {
//        when (event) {
//            is AddDayEvent.EnteredDay ->
//                _workoutDayState.value = workoutDayState.value.copy(
//                    text = event.value
//                )
//            AddDayEvent.InsertDay ->
//                viewModelScope.launch {
//                    try {
//                        workoutDayRepository.insertWorkoutDay(
//                            WorkoutDay(
//                                id = currentWorkoutDayId,
//                                day = _workoutDayState.value.text,
//                                workoutTitleId = workoutTitleId ?: -1
//                            )
//                        )
//
//                        _eventFlow.emit(UiEvent.InsertWorkoutDay)
//                    } catch (e: InvalidInputException) {
//                        _eventFlow.emit(
//                            UiEvent.ShowSnackbar(
//                                message = e.message ?: "Day not saved"
//                            )
//                        )
//                    }
//
//                }
//            AddDayEvent.UpdateDay ->
//                viewModelScope.launch {
//                    try {
//                        workoutDayRepository.updateWorkoutDay(
//                            WorkoutDay(
//                                id = currentWorkoutDayId,
//                                day = _workoutDayState.value.text,
//                                workoutTitleId = workoutTitleId ?: -1
//                            )
//                        )
//                        _eventFlow.emit(UiEvent.UpdateWorkoutDay)
//                    } catch (e: InvalidInputException) {
//                        _eventFlow.emit(
//                            UiEvent.ShowSnackbar(
//                                message = e.message ?: "Day not saved"
//                            )
//                        )
//                    }
//                }
//        }
//    }

    fun addUpdateDay(dayId: Int, day: String, workoutTitleId: Int) {
        viewModelScope.launch {
            if (dayId != -1) {
                workoutDayRepository.updateWorkoutDay(
                    WorkoutDay(
                        id = dayId,
                        day = day,
                        workoutTitleId = workoutTitleId
                    )
                )
            } else {
                workoutDayRepository.insertWorkoutDay(
                    WorkoutDay(
                        day = day,
                        workoutTitleId = workoutTitleId
                    )
                )
            }
        }
    }

}