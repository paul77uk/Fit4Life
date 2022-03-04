package com.paulvickers.fit4life.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.paulvickers.fit4life.data.data_access_objects.*
import com.paulvickers.fit4life.data.models.*
import com.paulvickers.fit4life.data.models.Set
import com.paulvickers.fit4life.di.ApplicationScope
import com.paulvickers.fit4life.utils.DateConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(
    entities = [WorkoutTitle::class, WorkoutDay::class, ExerciseTitle::class, Set::class, History::class],
    version = 1
)
@TypeConverters(DateConverter::class)
abstract class Fit4LifeDatabase : RoomDatabase() {

    abstract val workoutTitleDao: WorkoutTitleDao
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
            val workoutDayDao = database.get().workoutDayDao
            val exerciseTitleDao = database.get().exerciseTitleDao
            val setDao = database.get().setDao

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

                exerciseTitleDao.insertExerciseTitle(
                    ExerciseTitle(
                        title = "OverHead Press",
                        dayId = 1
                    )
                )
                exerciseTitleDao.insertExerciseTitle(
                    ExerciseTitle(
                        title = "Bench Press",
                        dayId = 1
                    )
                )
                exerciseTitleDao.insertExerciseTitle(
                    ExerciseTitle(
                        title = "Squat",
                        dayId = 2
                    )
                )
                exerciseTitleDao.insertExerciseTitle(
                    ExerciseTitle(
                        title = "DeadLift",
                        dayId = 2
                    )
                )

                for (i in 1..5) {
                    setDao.insertSet(
                        Set(
                            setTitle = "Overhead Press",
                            setNum = i,
                            weight = 50,
                            reps = 10,
                            exerciseId = 1
                        )
                    )
                }

                for (i in 1..5) {
                    setDao.insertSet(
                        Set(
                            setTitle = "Bench Press",
                            setNum = i,
                            weight = 60,
                            reps = 10,
                            exerciseId = 2
                        )
                    )
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



