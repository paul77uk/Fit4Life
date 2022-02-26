package com.paulvickers.fit4life.presentation.exercise_titles.exercise_title_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulvickers.fit4life.data.models.ExerciseTitle
import com.paulvickers.fit4life.data.repositories.ExerciseTitleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseTitleViewModel @Inject constructor(
    private val exerciseTitleRepository: ExerciseTitleRepository
) : ViewModel() {

    private var _exerciseTitles = MutableStateFlow<List<ExerciseTitle>>(emptyList())
    val exerciseTitles: StateFlow<List<ExerciseTitle>> = _exerciseTitles

    init {
        getExerciseTitles()
    }

//    fun onEvent(event: WorkoutTitleEvent) {
//        when (event) {
//            is WorkoutTitleEvent.DeleteWorkoutTitles ->
//                viewModelScope.launch {
//                    workoutTitleUseCases.deleteWorkoutTitleUseCase(event.workoutTitle)
//                    recentlyDeletedWorkoutTitle = event.workoutTitle
//                }
//            is WorkoutTitleEvent.GetWorkoutTitles ->
//                getWorkoutTitles()
//            WorkoutTitleEvent.RestoreWorkoutTitle ->
//                viewModelScope.launch {
//                    workoutTitleUseCases.insertWorkoutTitleUseCase(
//                        recentlyDeletedWorkoutTitle ?: return@launch
//                    )
//                    recentlyDeletedWorkoutTitle = null
//                }
//        }
//    }

    private fun getExerciseTitles() {
        viewModelScope.launch {
           exerciseTitleRepository.getExercisesByDayId(2).collect {
                _exerciseTitles.value = it
            }
        }
    }

}