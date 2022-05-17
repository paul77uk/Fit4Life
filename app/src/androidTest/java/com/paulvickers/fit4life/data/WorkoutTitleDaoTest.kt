package com.paulvickers.fit4life.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.paulvickers.fit4life.data.data_access_objects.WorkoutTitleDao
import com.paulvickers.fit4life.data.database.Fit4LifeDatabase
import com.paulvickers.fit4life.data.models.WorkoutTitle
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WorkoutTitleDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao: WorkoutTitleDao
    private lateinit var database: Fit4LifeDatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            Fit4LifeDatabase::class.java
        ).build()
        dao = database.workoutTitleDao
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun saveWorkoutTitleTest() = runBlocking {
        val workouts = listOf(
            WorkoutTitle(id = 1, title = "first workout"),
            WorkoutTitle(id = 2, title = "second workout"),
            WorkoutTitle(id = 3, title = "third workout")
        )
        workouts.forEach { dao.insertWorkoutTitle(it) }

        val allWorkouts = dao.getWorkoutTitles()
        Truth.assertThat(allWorkouts.toList()).isEqualTo(workouts)
    }
}