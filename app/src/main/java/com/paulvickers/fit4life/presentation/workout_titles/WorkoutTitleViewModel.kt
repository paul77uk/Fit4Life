package com.paulvickers.fit4life.presentation.workout_titles

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulvickers.fit4life.domain.model.WorkoutTitle
import com.paulvickers.fit4life.domain.use_case.workout_title_usecases.WorkoutTitleUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutTitleViewModel @Inject constructor(
    private val workoutTitleUseCases: WorkoutTitleUseCases
) : ViewModel() {

    private var _state = mutableStateOf(WorkoutTitleState())
    val state: State<WorkoutTitleState> = _state

    private var recentlyDeletedWorkoutTitle: WorkoutTitle? = null
    private var getWorkoutTitlesJob: Job? = null

    init {
        getWorkoutTitles()
    }

    fun onEvent(event: WorkoutTitleEvent) {
        when (event) {
            is WorkoutTitleEvent.DeleteWorkoutTitles ->
                viewModelScope.launch {
                    workoutTitleUseCases.deleteWorkoutTitleUseCase(event.workoutTitle)
                    recentlyDeletedWorkoutTitle = event.workoutTitle
                }
            is WorkoutTitleEvent.GetWorkoutTitles ->
                getWorkoutTitles()
            WorkoutTitleEvent.RestoreWorkoutTitle ->
                viewModelScope.launch {
                    workoutTitleUseCases.insertWorkoutTitleUseCase(
                        recentlyDeletedWorkoutTitle ?: return@launch
                    )
                    recentlyDeletedWorkoutTitle = null
                }
        }
    }

    private fun getWorkoutTitles() {
        getWorkoutTitlesJob?.cancel()
        getWorkoutTitlesJob = workoutTitleUseCases.getWorkoutTitlesUseCase()
            .onEach { workoutTitles ->
                _state.value = state.value.copy(
                    workoutTitle = workoutTitles
                )
            }
            .launchIn(viewModelScope)
    }

//    fun deleteWorkoutTitleAndChildren(workoutTitle: WorkoutTitle) = viewModelScope.launch {
//        workoutTitleUseCases.deleteWorkoutTitleUseCase.invoke(workoutTitle)
//    }

}