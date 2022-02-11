package com.paulvickers.fit4life.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.paulvickers.fit4life.data.data_source.WorkoutDayDao
import com.paulvickers.fit4life.data.data_source.WorkoutTitleDao
import com.paulvickers.fit4life.di.ApplicationScope
import com.paulvickers.fit4life.domain.model.WorkoutDay
import com.paulvickers.fit4life.domain.model.WorkoutTitle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(
    entities = [WorkoutTitle::class, WorkoutDay::class],
    version = 1
)
abstract class Fit4LifeDatabase : RoomDatabase() {

    abstract val workoutTitleDao: WorkoutTitleDao
    abstract val workoutDayDao: WorkoutDayDao

    class Callback @Inject constructor(
        private val database: Provider<Fit4LifeDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope,
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val workoutTitleDao = database.get().workoutTitleDao
            val workoutDayDao = database.get().workoutDayDao

            applicationScope.launch {
                listOfWorkoutTitles.forEach {
                    workoutTitleDao.insertWorkoutTitle(
                        it
                    )
                }
                for (i in 1..4) {
                    workoutDayDao.insertWorkoutDay(WorkoutDay(day = "Day $i", workoutTitleId = 1))
                }
                for (i in 1..5) {
                    workoutDayDao.insertWorkoutDay(WorkoutDay(day = "Day $i", workoutTitleId = 2))
                }
                for (i in 1..6) {
                    workoutDayDao.insertWorkoutDay(WorkoutDay(day = "Day $i", workoutTitleId = 3))
                }
            }
        }
    }

    companion object {
        const val DATABASE_NAME = "fit4life_db"
    }

}

val listOfWorkoutTitles = listOf(
   WorkoutTitle(title = "Kaz Workout"), // 4 days
   WorkoutTitle(title = "Big Z Workout"), // 5 days
   WorkoutTitle(title = "Toomey Workout") // 6 days
)



