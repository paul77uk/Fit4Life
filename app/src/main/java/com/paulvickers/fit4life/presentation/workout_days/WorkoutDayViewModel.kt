package com.paulvickers.fit4life.presentation.workout_days

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulvickers.fit4life.data.relations.WorkoutWithDays
import com.paulvickers.fit4life.domain.model.WorkoutDay
import com.paulvickers.fit4life.domain.use_case.GetDaysOfWorkoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutDayViewModel @Inject constructor(
    private val getDaysOfWorkoutUseCase: GetDaysOfWorkoutUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _workoutDays = MutableStateFlow<List<WorkoutDay>>(emptyList())
    val workoutDays = _workoutDays.asStateFlow()

    init {
        savedStateHandle.get<Int>("workoutId")?.let { workoutTitleId ->
            if (workoutTitleId != -1) {
                viewModelScope.launch(Dispatchers.IO) {
                    getDaysOfWorkoutUseCase.invoke(workoutTitleId)
                        .distinctUntilChanged()
                        .collect {
                            _workoutDays.value = it
                            Log.d("TAG", _workoutDays.value.toString())
                        }
                }
            }
        }
    }
}