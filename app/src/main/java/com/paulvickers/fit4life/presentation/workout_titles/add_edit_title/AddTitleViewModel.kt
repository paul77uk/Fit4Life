package com.paulvickers.fit4life.presentation.workout_titles.add_edit_title

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulvickers.fit4life.data.models.WorkoutDay
import com.paulvickers.fit4life.data.models.WorkoutTitle
import com.paulvickers.fit4life.data.models.WorkoutWeek
import com.paulvickers.fit4life.data.repositories.WorkoutDayRepository
import com.paulvickers.fit4life.data.repositories.WorkoutTitleRepository
import com.paulvickers.fit4life.data.repositories.WorkoutWeekRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTitleViewModel @Inject constructor(
    private val workoutTitleRepository: WorkoutTitleRepository,
    private val workoutDayRepository: WorkoutDayRepository,
    private val workoutWeekRepository: WorkoutWeekRepository
) : ViewModel() {

    private val _maxWorkoutId = MutableStateFlow(0)
    val maxWorkoutId: StateFlow<Int> = _maxWorkoutId

    init {
        getMaxWorkoutId()
    }

    fun addWorkoutTitle(workoutTitleId: Int, workoutTitleTitle: String) {
        viewModelScope.launch {
            if (workoutTitleId == -1) {
                workoutTitleRepository.insertWorkoutTitle(
                    WorkoutTitle(
                        title = workoutTitleTitle
                    )
                )
            } else {
                workoutTitleRepository.updateWorkoutTitle(
                    WorkoutTitle(
                        id = workoutTitleId,
                        title = workoutTitleTitle
                    )
                )
            }

        }
    }

    fun updateWorkoutTitle(workoutTitleId: Int, workoutTitleTitle: String) {
        viewModelScope.launch {
            workoutTitleRepository.updateWorkoutTitle(
                WorkoutTitle(
                    id = workoutTitleId,
                    title = workoutTitleTitle
                )
            )
        }
    }

    private fun getMaxWorkoutId() {
        viewModelScope.launch {
            workoutTitleRepository.getMaxId.collect {
                _maxWorkoutId.value = it
            }
        }
    }

    fun addWorkoutWeeks(numOfDays: Int) {
        viewModelScope.launch {
            if (numOfDays == -1) {
                for (i in 1..numOfDays) {
                    workoutWeekRepository.insertWorkoutWeek(
                        WorkoutWeek(
                            id = 7,
                            week = "Week$i",
                            workoutTitleId = 10
                        )
                    )
                }
            }
        }
    }

    fun addWorkoutDays(numOfDays: Int) {
        viewModelScope.launch {
            if (numOfDays == -1) {
                for (i in 1..numOfDays) {
                    workoutDayRepository.insertWorkoutDay(
                        WorkoutDay(
                            day = "Day$i",
                            workoutWeekId = 7
                        )
                    )
                }
            }
        }
    }

}