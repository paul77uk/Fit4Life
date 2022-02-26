package com.paulvickers.fit4life.presentation.workout_titles.add_edit_title

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulvickers.fit4life.data.models.WorkoutTitle
import com.paulvickers.fit4life.data.repositories.WorkoutTitleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTitleViewModel @Inject constructor(
    private val workoutTitleRepository: WorkoutTitleRepository,
//    savedStateHandle: SavedStateHandle
) : ViewModel() {

//    private val _workoutTitleState = mutableStateOf(
//        WorkoutTitleTextFieldState(
//        )
//    )
//    val workoutTitleState: State<WorkoutTitleTextFieldState> = _workoutTitleState

//    private val _eventFlow = MutableSharedFlow<UiEvent>()
//    val eventFlow = _eventFlow.asSharedFlow()
//
//    private var currentWorkoutTitleId: Int? = null
//
//    val workoutTitleId = savedStateHandle.get<Int>("workoutTitleId")

//    init {
//        savedStateHandle.get<Int>("workoutTitleId")?.let { workoutTitleId ->
//            if (workoutTitleId != -1) {
//                viewModelScope.launch {
//                    workoutTitleRepository.getWorkoutTitles.collect {
//
//                    }
////                        ?.also { WorkoutTitle ->
////                            currentWorkoutTitleId = WorkoutTitle.id
////                            _workoutTitleState.value = workoutTitleState.value.copy(
////                                text = WorkoutTitle.title,
////                                isAdd = false
////                            )
////                        }
//                }
//            }
//        }
//    }

//    sealed class UiEvent {
//        data class ShowSnackbar(val message: String) : UiEvent()
//        object InsertWorkoutTitle : UiEvent()
//        object UpdateWorkoutTitle : UiEvent()
//    }

//    fun onEvent(event: AddWorkoutTitleEvent) {
//        when (event) {
//            is AddWorkoutTitleEvent.EnteredTitle ->
//                _workoutTitleState.value = workoutTitleState.value.copy(
//                    text = event.value
//                )
//            AddWorkoutTitleEvent.InsertWorkoutTitle ->
//                viewModelScope.launch {
//                    /*
//              TODO: Maybe instead of below I could just create an id starting from 1
//                    and autoIncrement it and save it to datastore
//                    I could have a workoutTitleAndWeekIdLink
//                    weekAndDayIdLink
//                    DayAndWorkoutIdLink
//                    */
//                    val uniqueId = UUID.randomUUID().mostSignificantBits.toInt()
//                    try {
//                        workoutTitleRepository.insertWorkoutTitle(
//                            WorkoutTitle(
//                                title = _workoutTitleState.value.text,
//                                id = currentWorkoutTitleId
//                            )
//                        )
//                        _eventFlow.emit(AddDayViewModel.UiEvent.InsertWorkoutTitle)
//                    } catch (e: InvalidInputException) {
//                        _eventFlow.emit(
//                            AddDayViewModel.UiEvent.ShowSnackbar(
//                                message = e.message ?: "Workout title not saved"
//                            )
//                        )
//                    }
////                    workoutDayUseCases.insertWorkoutDayUseCase(
////                        WorkoutDay(
////                            day = "Day $uniqueId",
////                            workoutTitleId = currentWorkoutTitleId
////                        )
////                    )
//                }
//            AddWorkoutTitleEvent.UpdateWorkoutTitle ->
//                viewModelScope.launch {
//                    try {
//                        workoutTitleRepository.updateWorkoutTitle(
//                            WorkoutTitle(
//                                title = workoutTitleState.value.text,
//                                id = currentWorkoutTitleId
//                            )
//                        )
//                        _eventFlow.emit(AddDayViewModel.UiEvent.UpdateWorkoutTitle)
//                    } catch (e: InvalidInputException) {
//                        _eventFlow.emit(
//                            AddDayViewModel.UiEvent.ShowSnackbar(
//                                message = e.message ?: "Workout title not saved"
//                            )
//                        )
//                    }
//                }
//        }
//    }

    fun addUpdateWorkoutTitle(workoutTitleId: Int, workoutTitleTitle: String) {
        viewModelScope.launch {
            if (workoutTitleId != -1) {
                workoutTitleRepository.updateWorkoutTitle(
                    WorkoutTitle(
                        id = workoutTitleId,
                        title = workoutTitleTitle
                    )
                )
            } else {
                workoutTitleRepository.insertWorkoutTitle(
                    WorkoutTitle(
                        title = workoutTitleTitle
                    )
                )
            }
        }
    }
}