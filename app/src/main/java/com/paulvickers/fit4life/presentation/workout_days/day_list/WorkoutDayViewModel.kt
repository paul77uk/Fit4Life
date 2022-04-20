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
class WorkoutDayViewModel @Inject constructor(
    private val workoutDayRepository: WorkoutDayRepository,
    private val exerciseTitleRepository: ExerciseTitleRepository,
    private val workoutWeekRepository: WorkoutWeekRepository,
    private val setRepository: SetRepository,
) : ViewModel() {

    private var _sets = MutableStateFlow<List<Set>>(emptyList())
    val sets: StateFlow<List<Set>> = _sets

    private val _weeks =
        MutableStateFlow<List<WorkoutWeek>>(emptyList())
    val weeks: StateFlow<List<WorkoutWeek>> = _weeks

    private val _selectedWeek =
        MutableStateFlow<Int>(1)
    val selectedWeek: StateFlow<Int> = _selectedWeek

    private val _days =
        MutableStateFlow<List<WorkoutDay>>(emptyList())
    val days: StateFlow<List<WorkoutDay>> = _days

    private val _selectedDay =
        MutableStateFlow<Int>(0)
    val selectedDay: StateFlow<Int> = _selectedDay

    private val _dayId =
        MutableStateFlow<Int>(0)
    val dayId: StateFlow<Int> = _dayId

    private var _exerciseTitles = MutableStateFlow<List<ExerciseTitle>>(emptyList())
    val exerciseTitles: StateFlow<List<ExerciseTitle>> = _exerciseTitles

    private val _minDayId = MutableStateFlow(0)
    val minDayId: StateFlow<Int> = _minDayId

    var firstWeek = false
    var firstDay = false

    init {
//        getMinDayId()
//        getMinWeekId()
        firstWeek = true
        firstDay = true
        getExerciseTitles()
//        getExerciseForSetsByDayId()
    }

//    fun getDays(workoutWeekId: Int) {
//        viewModelScope.launch {
//            workoutDayRepository.getDaysByWorkoutWeekId(workoutWeekId).collect {
//                _days.value = it
//            }
//        }
//    }

    fun addSet(
        numberOfSets: Int,
        exerciseId: Int,
        isRepsDistTime: Int,
        exerciseForSetsId: Int,
        dayId: Int
    ) {
        repeat(numberOfSets) {
            viewModelScope.launch {
                setRepository.insertSet(
                    Set(
                        setNum = it + 1,
                        weight = 0,
                        repsDistTime = 0,
                        exerciseId = exerciseId + 1,
                        isCompleted = 0,
                        isRepsDistTime = isRepsDistTime,
                        exerciseForSetsId = exerciseForSetsId,
                        dayId = dayId
                    )
                )
            }
        }
    }

    fun getExerciseTitles() {
        viewModelScope.launch {
            exerciseTitleRepository.getExercises.collect {
                _exerciseTitles.value = it
            }
        }
    }

    fun getWeeks(workoutTitleId: Int) {
        viewModelScope.launch {
            workoutWeekRepository.getWeeksByWorkoutTitleId(workoutTitleId).collect {
                _weeks.value = it
                if (firstWeek) {
                    _selectedWeek.value = it.first().id ?: 0
                    firstWeek = false
                }
//                else {
//                    _selectedDay.value = (_selectedWeek.value - 1) * (it.size)
//                }
                getDays()
            }
        }
    }

    fun getDayId(dayId: Int) {
        _dayId.value = dayId
    }

    fun getDays() {
        viewModelScope.launch {
            workoutDayRepository.getDaysByWorkoutWeekId(selectedWeek.value).collect {
                _days.value = it
                if (firstDay) {
                    _selectedDay.value = it.first().id ?: 0
//                    firstDay = false
                }
            }
        }
    }

    fun getSets(dayId: Int) {
        viewModelScope.launch {
            setRepository.getSetByDayId(dayId).collect {
                _sets.value = it
            }
        }
    }

    fun deleteSet(set: Set) {
        viewModelScope.launch {
            setRepository.deleteSet(set)
        }
    }

    fun setSelectedWeek(id: Int) {
        _selectedWeek.value = id
    }

    fun setSelectedDay(id: Int) {
        _selectedDay.value = id
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
                    workoutWeekId = workoutTitleId
                )
            )
        }
    }

    private fun getMinDayId() {
        viewModelScope.launch {
            workoutDayRepository.getMinDayId.collect {
                _minDayId.value = it
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
                    repsDistTime = set.repsDistTime,
                    exerciseId = set.exerciseId,
                    isCompleted = if (set.isCompleted == 1) 0 else 1,
                    isRepsDistTime = set.isRepsDistTime,
                    exerciseForSetsId = set.exerciseForSetsId,
                    dayId = set.dayId
                )
            )
        }
    }

    fun updateRound(set: Set) {
        viewModelScope.launch {
            setRepository.updateSet(
                Set(
                    id = set.id,
                    setNum = set.setNum,
                    weight = set.weight,
                    repsDistTime = set.repsDistTime,
                    exerciseId = set.exerciseId,
                    isCompleted = if (set.isCompleted < set.setNum) set.isCompleted + 1 else set.isCompleted,
                    isRepsDistTime = set.isRepsDistTime,
                    exerciseForSetsId = set.exerciseForSetsId,
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
                    repsDistTime = set.repsDistTime,
                    exerciseId = set.exerciseId,
                    isCompleted = set.isCompleted,
                    isRepsDistTime = set.isRepsDistTime,
                    exerciseForSetsId = set.exerciseForSetsId,
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
                    repsDistTime = reps ?: 0,
                    exerciseId = set.exerciseId,
                    isCompleted = set.isCompleted,
                    isRepsDistTime = set.isRepsDistTime,
                    exerciseForSetsId = set.exerciseForSetsId,
                    dayId = set.dayId
                )
            )
        }
    }

}
