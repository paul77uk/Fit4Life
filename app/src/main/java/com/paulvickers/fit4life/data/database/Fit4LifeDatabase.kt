package com.paulvickers.fit4life.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.paulvickers.fit4life.data.data_access_objects.*
import com.paulvickers.fit4life.data.database.perpopulateddata.*
import com.paulvickers.fit4life.data.models.*
import com.paulvickers.fit4life.data.models.Set
import com.paulvickers.fit4life.di.ApplicationScope
import com.paulvickers.fit4life.utils.DateConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

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

    class Callback @Inject constructor(
        private val database: Provider<Fit4LifeDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope,
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val workoutTitleDao = database.get().workoutTitleDao
            val workoutWeekDao = database.get().workoutWeekDao
            val workoutDayDao = database.get().workoutDayDao
            val exerciseTitleDao = database.get().exerciseTitleDao
            val setDao = database.get().setDao

            applicationScope.launch {

                workoutTitles.forEach {
                    workoutTitleDao.insertWorkoutTitle(
                        it
                    )
                }

                workoutWeeks().forEach {
                    workoutWeekDao.insertWorkoutWeek(
                        it
                    )
                }

                workoutDays().forEach {
                    workoutDayDao.insertWorkoutDay(
                        it
                    )
                }

                exerciseTitles.forEach {
                    exerciseTitleDao.insertExerciseTitle(
                        it
                    )
                }

                workoutSets().forEach {
                    setDao.insertSet(
                        it
                    )
                }

            }

        }
    }

    companion object {
        const val DATABASE_NAME = "fit4life_db21"
    }

}



