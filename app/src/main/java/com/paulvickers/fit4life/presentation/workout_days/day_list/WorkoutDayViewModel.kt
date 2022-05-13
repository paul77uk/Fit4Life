package com.paulvickers.fit4life.presentation.workout_days.day_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
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


    private var _repValue = mutableStateOf(TextFieldValue())
    val repValue: State<TextFieldValue> = _repValue

    private var _weightValue = mutableStateOf(TextFieldValue())
    val weightValue: State<TextFieldValue> = _weightValue

    private var _distanceValue = mutableStateOf(TextFieldValue())
    val distanceValue: State<TextFieldValue> = _distanceValue

    private var _timeValue = mutableStateOf(TextFieldValue())
    val timeValue: State<TextFieldValue> = _timeValue

    private var _openRepDialog = mutableStateOf(false)
    val openRepDialog: State<Boolean> = _openRepDialog

    private var _openWeightDialog = mutableStateOf(false)
    val openWeightDialog: State<Boolean> = _openWeightDialog

    private var _openDistanceDialog = mutableStateOf(false)
    val openDistanceDialog: State<Boolean> = _openDistanceDialog

    private var _openTimeDialog = mutableStateOf(false)
    val openTimeDialog: State<Boolean> = _openTimeDialog

    private var _setId = mutableStateOf(0)
    val setId: State<Int> = _setId


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
        exerciseForSetsId: Int,
        dayId: Int
    ) {
        repeat(numberOfSets) {
            viewModelScope.launch {
                setRepository.insertSet(
                    Set(
                        setNum = it + 1,
                        weight = 0,
                        exerciseId = exerciseId + 1,
                        isCompleted = 0,
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

//    fun updateSet(set: Set) {
//        viewModelScope.launch {
//            setRepository.updateSet(
//                Set(
//                    id = set.id,
//                    setNum = set.setNum,
//                    weight = set.weight,
//                    exerciseId = set.exerciseId,
//                    isCompleted = if (set.isCompleted == 1) 0 else 1,
//                    exerciseForSetsId = set.exerciseForSetsId,
//                    dayId = set.dayId
//                )
//            )
//        }
//    }

    fun updateWeightById(weight: Int) {
        viewModelScope.launch {
            setRepository.updateWeightById(weight, _setId.value)
        }
    }

    fun updateRepsById(reps: Int) {
        viewModelScope.launch {
            setRepository.updateRepsById(reps, _setId.value)
        }
    }

    fun updateDistanceById(distance: Int) {
        viewModelScope.launch {
            setRepository.updateDistanceById(distance, _setId.value)
        }
    }

    fun updateTimeById(time: Double) {
        viewModelScope.launch {
            setRepository.updateTimeById(time, _setId.value)
        }
    }

    fun updateIsCompletedById(isCompleted: Int) {
        viewModelScope.launch {
            setRepository.updateIsCompletedById(if (isCompleted == 1) 0 else 1, _setId.value)
        }
    }

    fun updateRound(set: Set) {
        viewModelScope.launch {
            setRepository.updateSet(
                Set(
                    id = set.id,
                    setNum = set.setNum,
                    weight = set.weight,
                    exerciseId = set.exerciseId,
                    isCompleted = if (set.isCompleted < set.setNum) set.isCompleted + 1 else set.isCompleted,
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
                    exerciseId = set.exerciseId,
                    isCompleted = set.isCompleted,
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
                    exerciseId = set.exerciseId,
                    isCompleted = set.isCompleted,
                    exerciseForSetsId = set.exerciseForSetsId,
                    dayId = set.dayId
                )
            )
        }
    }

    fun isLastDay() {
        if (days.value.last().id == dayId.value) {}// showButton
            // if button clicked to reset checkboxes
                // reset all checkboxes by workoutId
    }

    fun setOpenWeightDialog(bool: Boolean) {
        _openWeightDialog.value = bool
    }

    fun setOpenRepDialog(bool: Boolean) {
        _openRepDialog.value = bool
    }

    fun setOpenDistanceDialog(bool: Boolean) {
        _openDistanceDialog.value = bool
    }

    fun setOpenTimeDialog(bool: Boolean) {
        _openTimeDialog.value = bool
    }

    fun onWeightValueChange(weightValue: String) {
        _weightValue.value = TextFieldValue(weightValue)
    }

    fun onRepValueChange(repValue: String) {
        _repValue.value = TextFieldValue(repValue)
    }

    fun onDistanceValueChange(distanceValue: String) {
        _distanceValue.value = TextFieldValue(distanceValue)
    }

    fun onTimeValueChange(timeValue: String) {
        _timeValue.value = TextFieldValue(timeValue)
    }

    fun setSetId(id: Int) {
        _setId.value = id
    }

}
