package com.paulvickers.fit4life.data.repositories

import com.paulvickers.fit4life.data.data_access_objects.SetDao
import com.paulvickers.fit4life.data.models.Set
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class SetRepository @Inject constructor(private val setDao: SetDao) {

    fun getSetByDayId(dayId: Int): Flow<List<Set>> {
        return setDao.getSetsByDayId(dayId)
    }

    suspend fun insertSet(set: Set) {
        setDao.insertSet(set)
    }

    suspend fun updateSet(set: Set) {
        setDao.updateSet(set)
    }

    suspend fun updateWeightById(weight: Int, id: Int) {
        setDao.updateWeightById(weight, id)
    }

    suspend fun updateRepsById(reps: Int, id: Int) {
        setDao.updateRepsById(reps, id)
    }

    suspend fun updateDistanceById(distance: Int, id: Int) {
        setDao.updateDistanceById(distance, id)
    }

    suspend fun updateTimeById(time: Double, id: Int) {
        setDao.updateTimeById(time, id)
    }

    suspend fun updateIsCompletedById(isCompleted: Int, id: Int) {
        setDao.updateIsCompletedById(isCompleted, id)
    }

    suspend fun deleteSet(set: Set) {
        setDao.deleteSet(set)
    }

}