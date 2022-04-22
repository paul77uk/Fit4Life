package com.paulvickers.fit4life.data.database

import android.content.res.Resources
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.paulvickers.fit4life.R
import com.paulvickers.fit4life.data.data_access_objects.*
import com.paulvickers.fit4life.data.database.perpopulateddata.exerciseTitles
import com.paulvickers.fit4life.data.database.perpopulateddata.workoutDays
import com.paulvickers.fit4life.data.database.perpopulateddata.workoutSets
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
        private val resources: Resources
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val workoutTitleDao = database.get().workoutTitleDao
            val workoutWeekDao = database.get().workoutWeekDao
            val workoutDayDao = database.get().workoutDayDao
            val exerciseTitleDao = database.get().exerciseTitleDao
            val setDao = database.get().setDao

            applicationScope.launch {

                prePopulateWithWorkoutTitle(workoutTitleDao)
//                workoutTitles.forEach {
//                    workoutTitleDao.insertWorkoutTitle(
//                        it
//                    )
//                }

//                workoutWeeks().forEach {
//                    workoutWeekDao.insertWorkoutWeek(
//                        it
//                    )
//                }

                prePopulateWithWorkoutWeek(workoutWeekDao)

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
//                prePopulateWithExerciseTitle(exerciseTitleDao)

                workoutSets().forEach {
                    setDao.insertSet(
                        it
                    )
                }

            }

        }

//        private suspend fun prePopulateWithExerciseTitle(exerciseTitleDao: ExerciseTitleDao) {
//
//            val jsonString = resources.openRawResource(R.raw.exercise_list).bufferedReader().use {
//                it.readText()
//            }
//
//            val typeToken = object : TypeToken<List<ExerciseTitle>>() {}.type
//            val exerciseTitles = Gson().fromJson<List<ExerciseTitle>>(jsonString, typeToken)
//
//            exerciseTitleDao.insertExerciseTitleList(exerciseTitles)
//        }

        private suspend fun prePopulateWithWorkoutTitle(workoutTitleDao: WorkoutTitleDao) {

            val jsonString = resources.openRawResource(R.raw.workout_titles).bufferedReader().use {
                it.readText()
            }

            val typeToken = object : TypeToken<List<WorkoutTitle>>() {}.type
            val workoutTitles = Gson().fromJson<List<WorkoutTitle>>(jsonString, typeToken)

            workoutTitleDao.insertWorkoutTitleList(workoutTitles)
        }

        private suspend fun prePopulateWithWorkoutWeek(workoutWeekDao: WorkoutWeekDao) {

            val jsonString = resources.openRawResource(R.raw.workout_weeks).bufferedReader().use {
                it.readText()
            }

            val typeToken = object : TypeToken<List<WorkoutWeek>>() {}.type
            val workoutWeeks = Gson().fromJson<List<WorkoutWeek>>(jsonString, typeToken)

            workoutWeekDao.insertWorkoutWeekList(workoutWeeks)
        }

    }

    companion object {
        const val DATABASE_NAME = "fit4life_db60" // 20 had current workout data
    }

}