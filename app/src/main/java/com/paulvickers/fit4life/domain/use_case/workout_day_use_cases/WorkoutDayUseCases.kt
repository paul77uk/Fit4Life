package com.paulvickers.fit4life.domain.use_case.workout_day_use_cases

class WorkoutDayUseCases(
    val insertWorkoutDayUseCase: InsertWorkoutDayUseCase,
    val updateWorkoutDayUseCase: UpdateWorkoutDayUseCase,
    val getDaysOfWorkoutUseCase: GetDaysOfWorkoutUseCase,
    val getWorkoutDayByIdUseCase: GetWorkoutDayByIdUseCase,
    val deleteDayUseCase: DeleteDayUseCase
)