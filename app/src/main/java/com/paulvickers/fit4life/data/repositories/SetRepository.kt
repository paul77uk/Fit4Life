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

    suspend fun deleteSet(set: Set) {
        setDao.deleteSet(set)
    }

}