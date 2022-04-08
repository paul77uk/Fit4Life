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
    private val exerciseTitleRepository: ExerciseTitleRepository,
) : ViewModel() {

    private var _exerciseTitles = MutableStateFlow<List<ExerciseTitle>>(emptyList())
    val exerciseTitles: StateFlow<List<ExerciseTitle>> = _exerciseTitles

//    fun getExerciseTitles(dayId: Int) {
//        viewModelScope.launch {
//           exerciseTitleRepository.getExercises(dayId).collect {
//                _exerciseTitles.value = it
//            }
//        }
//    }

    fun deleteExercise(exerciseTitle: ExerciseTitle) {
        viewModelScope.launch {
            exerciseTitleRepository.deleteExerciseTitle(exerciseTitle)
        }
    }

}