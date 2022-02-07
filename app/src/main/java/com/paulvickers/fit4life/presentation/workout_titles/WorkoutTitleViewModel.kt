package com.paulvickers.fit4life.presentation.workout_titles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulvickers.fit4life.domain.model.WorkoutTitle
import com.paulvickers.fit4life.domain.use_case.DeleteWorkoutDaysByWorkoutTitleIdUseCase
import com.paulvickers.fit4life.domain.use_case.DeleteWorkoutTitleUseCase
import com.paulvickers.fit4life.domain.use_case.GetWorkoutTitlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutTitleViewModel @Inject constructor(
    private val getWorkoutTitlesUseCase: GetWorkoutTitlesUseCase,
    private val deleteWorkoutTitleUseCase: DeleteWorkoutTitleUseCase,
    private val deleteWorkoutDaysByWorkoutTitleIdUseCase: DeleteWorkoutDaysByWorkoutTitleIdUseCase
) : ViewModel() {

    private var _workoutTitles = MutableStateFlow<List<WorkoutTitle>>(emptyList())
    val workoutTitles = _workoutTitles.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getWorkoutTitlesUseCase.invoke()
                .distinctUntilChanged()
                .collect {
                    _workoutTitles.value = it
                }
        }
    }

    fun deleteWorkoutTitleAndChildren(workoutTitle: WorkoutTitle) = viewModelScope.launch {
        deleteWorkoutTitleUseCase.invoke(workoutTitle)
        workoutTitle.id?.let { deleteWorkoutDaysByWorkoutTitleIdUseCase.invoke(it) }
    }

}