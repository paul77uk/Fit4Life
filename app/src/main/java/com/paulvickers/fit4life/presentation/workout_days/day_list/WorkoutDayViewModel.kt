package com.paulvickers.fit4life.presentation.workout_days.day_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulvickers.fit4life.domain.model.WorkoutDay
import com.paulvickers.fit4life.domain.use_case.workout_day_use_cases.WorkoutDayUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutDayViewModel @Inject constructor(
    private val dayUseCases: WorkoutDayUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _workoutDaysState = mutableStateOf(WorkoutDayState())
    val workoutDaysState: State<WorkoutDayState> = _workoutDaysState

    private var recentlyDeletedDay: WorkoutDay? = null
    private var getDayJob: Job? = null


    init {
        savedStateHandle.get<Int>("workoutTitleId")?.let { workoutTitleId ->
            if (workoutTitleId != -1) {
                viewModelScope.launch {
                    getDayJob?.cancel()
                    getDayJob = dayUseCases.getDaysOfWorkoutUseCase(workoutTitleId)
                        .onEach { workoutDays ->
                            _workoutDaysState.value = workoutDaysState.value.copy(
                                workoutDay = workoutDays
                            )
                        }
                        .launchIn(viewModelScope)
                }
            }
        }
    }

    fun deleteDay(workoutDay: WorkoutDay) {
        viewModelScope.launch {
            dayUseCases.deleteDayUseCase(workoutDay)
            recentlyDeletedDay = workoutDay
        }
    }

    fun restoreDay() {
        viewModelScope.launch {
            dayUseCases.insertWorkoutDayUseCase(
                recentlyDeletedDay ?: return@launch
            )
            recentlyDeletedDay = null
        }
    }

}
