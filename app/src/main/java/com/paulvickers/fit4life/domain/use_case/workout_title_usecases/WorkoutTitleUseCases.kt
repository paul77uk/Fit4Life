package com.paulvickers.fit4life.domain.use_case.workout_title_usecases

class WorkoutTitleUseCases(
    val getWorkoutTitlesUseCase: GetWorkoutTitlesUseCase,
    val getWorkoutTitleByIdUseCase: GetWorkoutTitleByIdUseCase,
    val insertWorkoutTitleUseCase: InsertWorkoutTitleUseCase,
    val deleteWorkoutTitleUseCase: DeleteWorkoutTitleUseCase,
    val updateWorkoutTitleUseCase: UpdateWorkoutTitleUseCase
) {

}