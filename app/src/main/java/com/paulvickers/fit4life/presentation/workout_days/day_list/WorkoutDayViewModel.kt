package com.paulvickers.fit4life.presentation.workout_days.day_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulvickers.fit4life.data.models.WorkoutDay
import com.paulvickers.fit4life.data.repositories.WorkoutDayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutDayViewModel @Inject constructor(
    private val workoutDayRepository: WorkoutDayRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

//    private var _workoutDaysState = mutableStateOf(WorkoutDayState())
//    val workoutDaysState: State<WorkoutDayState> = _workoutDaysState
//
//    private var recentlyDeletedDay: WorkoutDay? = null
//    private var getDayJob: Job? = null

    private val _days =
        MutableStateFlow<List<WorkoutDay>>(emptyList())
    val days: StateFlow<List<WorkoutDay>> = _days


//    init {
//        savedStateHandle.get<Int>("workoutTitleId")?.let { workoutTitleId ->
//            if (workoutTitleId != -1) {
//                viewModelScope.launch {
//                    workoutDayRepository.getDaysByWorkoutTitleId(workoutTitleId)
//
//                }
//            }
//        }
//    }

//    fun getAllDays(workoutTitleId: Int) {
//        viewModelScope.launch { // coroutines  scope tied to the lifecycle of our viewModel
//            dayUseCases.getDaysOfWorkoutUseCase(workoutTitleId).collect {
//                _days.value = it
//            }
//        }
//    }

//    fun deleteDay(workoutDay: WorkoutDay) {
//        viewModelScope.launch {
//            dayUseCases.deleteDayUseCase(workoutDay)
//            recentlyDeletedDay = workoutDay
//        }
//    }
//
//    fun restoreDay() {
//        viewModelScope.launch {
//            dayUseCases.insertWorkoutDayUseCase(
//                recentlyDeletedDay ?: return@launch
//            )
//            recentlyDeletedDay = null
//        }
//    }

    fun getDays(workoutTitleId: Int) {
        viewModelScope.launch {
            workoutDayRepository.getDaysByWorkoutTitleId(workoutTitleId).collect {
                _days.value = it
            }
        }
    }

    fun deleteDay(day: WorkoutDay) {
        viewModelScope.launch {
            workoutDayRepository.deleteWorkoutDay(day)
        }
    }

    fun addUpdateDay(dayId: Int, day: String, workoutTitleId: Int) {
        viewModelScope.launch {
            workoutDayRepository.insertWorkoutDay(
                WorkoutDay(
                    day = day,
                    workoutTitleId = workoutTitleId
                )
            )
        }
    }

}
