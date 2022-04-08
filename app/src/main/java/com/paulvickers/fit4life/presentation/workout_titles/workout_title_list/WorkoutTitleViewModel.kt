package com.paulvickers.fit4life.presentation.workout_titles.workout_title_list

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
class WorkoutTitleViewModel @Inject constructor(
    private val workoutTitleRepository: WorkoutTitleRepository,
    private val weekRepository: WorkoutWeekRepository,
    private val dayRepository: WorkoutDayRepository
) : ViewModel() {

    private val _allWorkoutTitles = MutableStateFlow<List<WorkoutTitle>>(emptyList())
    val allWorkoutTitles: StateFlow<List<WorkoutTitle>> = _allWorkoutTitles

//    private val _allWorkoutWeeks = MutableStateFlow<List<WorkoutWeek>>(emptyList())


    private val _maxWorkoutId = MutableStateFlow(0)
    val maxWorkoutId: StateFlow<Int> = _maxWorkoutId

    private val _maxWeekId = MutableStateFlow(0)
    val maxWeekId: StateFlow<Int> = _maxWeekId

    init {
        getWorkoutTitles()
        getMaxWorkoutId()
//        getWorkoutWeeks()
        getMaxWeekId()
    }

    private fun getWorkoutTitles() {
        viewModelScope.launch {
            workoutTitleRepository.getWorkoutTitles.collect {
                _allWorkoutTitles.value = it
            }
        }
    }

//    private fun getWorkoutWeeks() {
//        viewModelScope.launch {
//            weekRepository.getWeeksByWorkoutTitleId(_maxWorkoutId.value).collect {
//                _allWorkoutWeeks.value = it
//                Log.d("_allWorkoutWeeks.value", "getWorkoutWeeks: ${_allWorkoutWeeks.value}")
//
//            }
//        }
//    }

    fun deleteWorkoutTitle(workoutTitle: WorkoutTitle) {
        viewModelScope.launch {
            workoutTitleRepository.deleteWorkoutTitle(workoutTitle)
        }
    }

    private fun getMaxWorkoutId() {
        viewModelScope.launch {
            workoutTitleRepository.getMaxId.collect {
                _maxWorkoutId.value = it
            }
        }
    }

    private fun getMaxWeekId() {
//        getWorkoutWeeks()
        viewModelScope.launch {
//            if (_allWorkoutWeeks.value.isEmpty()) _maxWeekId.value = 0
//            else
             weekRepository.getMaxWeekId.collect {
                _maxWeekId.value = it
            }
        }
    }

    fun saveWorkoutTitle(title: String, numOfWeeks: String, numOfDays: String) {
//        getWorkoutTitles()
//        getMaxWorkoutId()
        val currentNewMaxWorkoutId = _maxWorkoutId.value.plus(1)
        viewModelScope.launch {
            workoutTitleRepository.insertWorkoutTitle(
                workoutTitle = WorkoutTitle(
                    id = currentNewMaxWorkoutId,
                    title = title
                )
            )
        }
        for (i in 1..numOfWeeks.toInt()) {
//            getWorkoutWeeks()
//            getMaxWeekId()
            if (_maxWeekId.value.equals(null)) _maxWeekId.value = 0
            else viewModelScope.launch {
                weekRepository.insertWorkoutWeek(
                    WorkoutWeek(
                        id = _maxWeekId.value.plus(i),
                        week = "Week $i",
                        workoutTitleId = currentNewMaxWorkoutId
                    )
                )
            }
            for (j in 1..numOfDays.toInt()) {
                viewModelScope.launch {
                    dayRepository.insertWorkoutDay(
                        WorkoutDay(
                            day = "Day $j",
                            workoutWeekId = _maxWeekId.value.plus(i)
                        )
                    )
                }
            }
        }


    }

}