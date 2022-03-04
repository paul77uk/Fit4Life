package com.paulvickers.fit4life.presentation.sets.sets_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulvickers.fit4life.data.models.History
import com.paulvickers.fit4life.data.repositories.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyRepository: HistoryRepository,
) : ViewModel() {

    private var _histories = MutableStateFlow<List<History>>(emptyList())
    val histories: StateFlow<List<History>> = _histories

//    var weightState by mutableStateOf(0)

    fun getHistories(exerciseId: Int) {
        viewModelScope.launch {
            historyRepository.getHistoryByExerciseId(exerciseId).collect {
                _histories.value = it
            }
        }
    }

    fun deleteHistory(history: History) {
        viewModelScope.launch {
            historyRepository.deleteHistory(history)
        }
    }

    fun addHistory(
        setTitle: String,
        setNum: Int,
        weight: Int,
        reps: Int,
        exerciseId: Int,
    ) {
        viewModelScope.launch {
            historyRepository.insertHistory(
                History(
                    setTitle = setTitle,
                    setNum = setNum,
                    weight = weight,
                    reps = reps,
                    exerciseId = exerciseId
                )
            )
        }
    }
}