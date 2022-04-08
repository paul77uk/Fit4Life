package com.paulvickers.fit4life.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.paulvickers.fit4life.data.data_access_objects.*
import com.paulvickers.fit4life.data.models.*
import com.paulvickers.fit4life.data.models.Set
import com.paulvickers.fit4life.utils.DateConverter

@Database(
    entities = [WorkoutTitle::class, WorkoutWeek::class, WorkoutDay::class, ExerciseTitle::class, Set::class, History::class],
    version = 1
)
@TypeConverters(DateConverter::class)

abstract class Fit4LifeDatabase : RoomDatabase() {

    abstract val workoutTitleDao: WorkoutTitleDao
    abstract val workoutWeekDao: WorkoutWeekDao
    abstract val workoutDayDao: WorkoutDayDao
    abstract val exerciseTitleDao: ExerciseTitleDao
    abstract val setDao: SetDao
    abstract val historyDao: HistoryDao


    companion object {
        const val DATABASE_NAME = "fit4life_db"
    }

}



