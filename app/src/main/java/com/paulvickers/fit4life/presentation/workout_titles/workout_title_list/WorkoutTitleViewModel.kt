package com.paulvickers.fit4life.presentation.workout_titles.workout_title_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulvickers.fit4life.data.models.WorkoutTitle
import com.paulvickers.fit4life.data.repositories.WorkoutTitleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutTitleViewModel @Inject constructor(
    private val workoutTitleRepository: WorkoutTitleRepository
) : ViewModel() {

    private  val _allWorkoutTitles = MutableStateFlow<List<WorkoutTitle>>(emptyList())
    val allWorkoutTitles: StateFlow<List<WorkoutTitle>> = _allWorkoutTitles

    init {
        getWorkoutTitles()
    }

    private fun getWorkoutTitles() {
        viewModelScope.launch {
            workoutTitleRepository.getWorkoutTitles.collect {
                _allWorkoutTitles.value = it
            }
        }
    }

    fun deleteWorkoutTitle(workoutTitle: WorkoutTitle) {
        viewModelScope.launch {
            workoutTitleRepository.deleteWorkoutTitle(workoutTitle)
        }
    }

}