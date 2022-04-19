package com.paulvickers.fit4life.data.data_access_objects

import androidx.room.*
import com.paulvickers.fit4life.data.models.Set
import kotlinx.coroutines.flow.Flow

@Dao
interface SetDao {

    @Query("SELECT * FROM `Set` Where dayId = :dayId")
    fun getSetsByDayId(dayId: Int): Flow<List<Set>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSet(set: Set)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSet(set: Set)

    @Delete
    suspend fun deleteSet(set: Set)

}