package com.paulvickers.fit4life.domain.use_case.workout_title_usecases

import com.google.common.truth.Truth.assertThat
import com.paulvickers.fit4life.data.repositories.FakeWorkoutRepository
import com.paulvickers.fit4life.utils.InvalidInputException
import com.paulvickers.fit4life.data.models.WorkoutTitle
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetWorkoutTitlesUseCaseTest {

    private lateinit var getWorkoutTitlesUseCase: GetWorkoutTitlesUseCase
    private lateinit var getWorkoutTitleByIdUseCase: GetWorkoutTitleByIdUseCase
    private lateinit var insertWorkoutTitleUseCase: InsertWorkoutTitleUseCase
    private lateinit var deleteWorkoutTitleUseCase: DeleteWorkoutTitleUseCase
    private lateinit var fakeWorkoutRepository: FakeWorkoutRepository

    @Before
    fun setUp() {
        fakeWorkoutRepository = FakeWorkoutRepository()
        getWorkoutTitlesUseCase = GetWorkoutTitlesUseCase(fakeWorkoutRepository)
        getWorkoutTitleByIdUseCase = GetWorkoutTitleByIdUseCase(fakeWorkoutRepository)
        insertWorkoutTitleUseCase = InsertWorkoutTitleUseCase(fakeWorkoutRepository)
        deleteWorkoutTitleUseCase = DeleteWorkoutTitleUseCase(fakeWorkoutRepository)
    }

    @Test
    fun `insert workoutTitle, inserted correctly`() = runBlocking {
        val title = "Dummy Data 1"
        insertWorkoutTitleUseCase(
            WorkoutTitle(
                title = title
            )
        )
        val workoutTitle = getWorkoutTitlesUseCase().first()
        assertThat(workoutTitle[0].title).isEqualTo(title)
    }

    @Test
    fun `insert emptyWorkoutTitle, throws InvalidNoteException`() = runBlocking {
        val title = ""
        var exceptionMessage = ""

        try {
            insertWorkoutTitleUseCase(
                WorkoutTitle(
                    title = title
                )
            )
        } catch (e: InvalidInputException){
            exceptionMessage = e.message.toString()
        }

        assertThat(exceptionMessage).isEqualTo("The title cannot be empty.")
    }

}