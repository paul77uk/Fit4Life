package com.paulvickers.fit4life.presentation.workout_days.day_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulvickers.fit4life.data.models.ExerciseTitle
import com.paulvickers.fit4life.data.models.WorkoutDay
import com.paulvickers.fit4life.data.repositories.ExerciseTitleRepository
import com.paulvickers.fit4life.data.repositories.WorkoutDayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutDayViewModel @Inject constructor(
    private val workoutDayRepository: WorkoutDayRepository,
    private val exerciseTitleRepository: ExerciseTitleRepository
) : ViewModel() {

    private val _days =
        MutableStateFlow<List<WorkoutDay>>(emptyList())
    val days: StateFlow<List<WorkoutDay>> = _days

    private var _exerciseTitles = MutableStateFlow<List<ExerciseTitle>>(emptyList())
    val exerciseTitles: StateFlow<List<ExerciseTitle>> = _exerciseTitles

    private val _minDayId = MutableStateFlow(0)
    val minDayId: StateFlow<Int> = _minDayId

    init {
        getMinDayId()
        getExerciseTitles()
    }

//    fun getDays(workoutWeekId: Int) {
//        viewModelScope.launch {
//            workoutDayRepository.getDaysByWorkoutWeekId(workoutWeekId).collect {
//                _days.value = it
//            }
//        }
//    }

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
                    workoutWeekId = workoutTitleId
                )
            )
        }
    }

    fun getExerciseTitles() {
        viewModelScope.launch {
            exerciseTitleRepository.getExercises.collect {
                _exerciseTitles.value = it
            }
        }
    }

    private fun getMinDayId() {
        viewModelScope.launch {
            workoutDayRepository.getMinDayId.collect {
                _minDayId.value = it
            }
        }
    }

}
