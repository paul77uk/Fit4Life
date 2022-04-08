package com.paulvickers.fit4life.presentation.workout_days.day_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulvickers.fit4life.data.models.ExerciseTitle
import com.paulvickers.fit4life.data.models.Set
import com.paulvickers.fit4life.data.models.WorkoutDay
import com.paulvickers.fit4life.data.models.WorkoutWeek
import com.paulvickers.fit4life.data.repositories.ExerciseTitleRepository
import com.paulvickers.fit4life.data.repositories.SetRepository
import com.paulvickers.fit4life.data.repositories.WorkoutDayRepository
import com.paulvickers.fit4life.data.repositories.WorkoutWeekRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeekViewModel @Inject constructor(
    private val workoutWeekRepository: WorkoutWeekRepository,
    private val exerciseTitleRepository: ExerciseTitleRepository,
    private val workoutDayRepository: WorkoutDayRepository,
    private val setRepository: SetRepository
) : ViewModel() {

    private val _weeks =
        MutableStateFlow<List<WorkoutWeek>>(emptyList())
    val weeks: StateFlow<List<WorkoutWeek>> = _weeks

    private val _selectedWeek =
        MutableStateFlow<Int>(0)
    val selectedWeek: StateFlow<Int> = _selectedWeek

    private val _days =
        MutableStateFlow<List<WorkoutDay>>(emptyList())
    val days: StateFlow<List<WorkoutDay>> = _days

    private val _selectedDay =
        MutableStateFlow<Int>(0)
    val selectedDay: StateFlow<Int> = _selectedDay


    private var _exerciseTitles = MutableStateFlow<List<ExerciseTitle>>(emptyList())
    val exerciseTitles: StateFlow<List<ExerciseTitle>> = _exerciseTitles

    private val _minDayId = MutableStateFlow(0)
    val minDayId: StateFlow<Int> = _minDayId

    var firstWeek = false
    var firstDay = false

    init {
        getMinWeekId()
        firstWeek = true
        firstDay = true
    }

    fun getWeeks(workoutTitleId: Int) {
        viewModelScope.launch {
            workoutWeekRepository.getWeeksByWorkoutTitleId(workoutTitleId).collect {
                _weeks.value = it
                if (firstWeek) {
                    _selectedWeek.value = it.first().id ?: 0
                    firstWeek = false
                }
                getDays()
            }
        }
    }

    fun getDays() {
        viewModelScope.launch {
            workoutDayRepository.getDaysByWorkoutWeekId(selectedWeek.value).collect {
                _days.value = it
                if (firstDay) {
                    _selectedDay.value = it.first().id ?: 0
                }
            }
        }
    }

    fun setSelectedWeek(id: Int) {
        _selectedWeek.value = id
    }

    fun setSelectedDay(id: Int) {
        _selectedDay.value = id
    }

    fun deleteWeek(week: WorkoutWeek) {
        viewModelScope.launch {
            workoutWeekRepository.deleteWorkoutWeek(week)
        }
    }

    fun addUpdateWeek(weekId: Int, week: String, workoutTitleId: Int) {
        viewModelScope.launch {
            workoutWeekRepository.insertWorkoutWeek(
                WorkoutWeek(
                    week = week,
                    workoutTitleId = workoutTitleId
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

    private fun getMinWeekId() {
        viewModelScope.launch {
            workoutWeekRepository.getMaxWeekId.collect {
                _minDayId.value = it
            }
        }
    }

    fun updateSet(set: Set) {
        viewModelScope.launch {
            setRepository.updateSet(
                Set(
                    id = set.id,
                    setNum = set.setNum,
                    weight = set.weight,
                    reps = set.reps,
                    exerciseId = set.exerciseId,
                    isCompleted = if (set.isCompleted == 1) 0 else 1,
                    dayId = set.dayId
                )
            )
        }
    }

    fun updateWeight(set: Set, weight: Int?) {
        viewModelScope.launch {
            setRepository.updateSet(
                Set(
                    id = set.id,
                    setNum = set.setNum,
                    weight = weight ?: 0,
                    reps = set.reps,
                    exerciseId = set.exerciseId,
                    isCompleted = set.isCompleted,
                    dayId = set.dayId
                )
            )
        }
    }

    fun updateReps(set: Set, reps: Int?) {
        viewModelScope.launch {
            setRepository.updateSet(
                Set(
                    id = set.id,
                    setNum = set.setNum,
                    weight = set.weight,
                    reps = reps ?: 0,
                    exerciseId = set.exerciseId,
                    isCompleted = set.isCompleted,
                    dayId = set.dayId
                )
            )
        }
    }

}
