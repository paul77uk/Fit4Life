package com.paulvickers.fit4life.data

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.paulvickers.fit4life.data.data_access_objects.WorkoutTitleDao
import com.paulvickers.fit4life.data.models.WorkoutTitle
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class Fit4LifeDatabaseTest {

    private lateinit var workoutDao: WorkoutTitleDao
    private lateinit var db: Fit4LifeDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context, Fit4LifeDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        workoutDao = db.workoutTitleDao
    }

    @After
    @Throws(IOException::class)
    fun deleteDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetTodo() = runBlocking {
        val workoutItem = WorkoutTitle(title = "Dummy Workout")
        workoutDao.insertWorkoutTitle(workoutItem)
        val oneItem = workoutDao.getWorkoutTitleById(1)
        assertThat(oneItem?.title).isEqualTo("Dummy Workout")
    }

    @Test
    @Throws(Exception::class)
    fun updateAndGetTodo() = runBlocking {
        val workoutItem1 = WorkoutTitle(title = "Dummy Workout")
        workoutDao.insertWorkoutTitle(workoutItem1)
        val workoutItem2 = WorkoutTitle(id = 1, title = "Updated Workout")
        workoutDao.updateWorkoutTitle(workoutItem2)
        val oneItem = workoutDao.getWorkoutTitleById(1)
        assertThat(oneItem?.title).isEqualTo("Updated Workout")
    }
}