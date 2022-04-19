package com.paulvickers.fit4life.presentation.sets.sets_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulvickers.fit4life.data.models.Set
import com.paulvickers.fit4life.data.repositories.SetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetViewModel @Inject constructor(
    private val setRepository: SetRepository,
) : ViewModel() {

    private var _sets = MutableStateFlow<List<Set>>(emptyList())
    val sets: StateFlow<List<Set>> = _sets

//    var weightState by mutableStateOf(0)

    fun getSets(exerciseId: Int) {
        viewModelScope.launch {
            setRepository.getSetByDayId(exerciseId).collect {
                _sets.value = it
            }
        }
    }

    fun deleteSet(set: Set) {
        viewModelScope.launch {
            setRepository.deleteSet(set)
        }
    }

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
}