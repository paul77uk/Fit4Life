package com.paulvickers.fit4life.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.paulvickers.fit4life.data.data_access_objects.WorkoutTitleDao
import com.paulvickers.fit4life.data.database.Fit4LifeDatabase
import com.paulvickers.fit4life.data.models.WorkoutTitle
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
class Fit4LifeDatabaseTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: Fit4LifeDatabase
    private lateinit var workoutTitleDao: WorkoutTitleDao

    @Before
    fun setup() {
        hiltRule.inject()
        workoutTitleDao = database.workoutTitleDao
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertWorkoutTitle() = runBlocking {
        val workoutTitle =
            WorkoutTitle(
                id = 1,
                title = "first workout"
            )
        workoutTitleDao.insertWorkoutTitle(workoutTitle)
        var allWorkoutTitles = listOf<WorkoutTitle>()
        workoutTitleDao.getWorkoutTitles().collect {
            allWorkoutTitles = it
        }
        assertThat(allWorkoutTitles).contains(workoutTitle)
//        assertThat(allUsers).contains(user)
    }
}

//    private lateinit var workoutDao: WorkoutTitleDao
//    private lateinit var db: Fit4LifeDatabase
//
//    @Before
//    fun createDb() {
//        val context = InstrumentationRegistry.getInstrumentation().targetContext
//
//        db = Room.inMemoryDatabaseBuilder(context, Fit4LifeDatabase::class.java)
//            .allowMainThreadQueries()
//            .build()
//
//        workoutDao = db.workoutTitleDao
//    }
//
//    @After
//    @Throws(IOException::class)
//    fun deleteDb() {
//        db.close()
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun insertAndGetTodo() = runBlocking {
//        val workoutItem = WorkoutTitle(title = "Dummy Workout")
//        workoutDao.insertWorkoutTitle(workoutItem)
//        var item = ""
//        workoutDao.getWorkoutTitles().collect {
//            item = it[0].title
//        }
//        assertThat(item).isEqualTo("Dummy Workout")
//    }
//
////    @Test
////    @Throws(Exception::class)
////    fun updateAndGetTodo() = runBlocking {
////        val workoutItem1 = WorkoutTitle(title = "Dummy Workout")
////        workoutDao.insertWorkoutTitle(workoutItem1)
////        val workoutItem2 = WorkoutTitle(id = 1, title = "Updated Workout")
////        workoutDao.updateWorkoutTitle(workoutItem2)
////        val oneItem = workoutDao.getWorkoutTitleById(1)
////        assertThat(oneItem?.title).isEqualTo("Updated Workout")
////    }
//}