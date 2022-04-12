package com.paulvickers.fit4life.data.database.perpopulateddata

import com.paulvickers.fit4life.data.models.*
import com.paulvickers.fit4life.data.models.Set

val workoutTitles = listOf<WorkoutTitle>(
    WorkoutTitle(
        id = 1,
        title = "F4L Workout"
    ),
//    WorkoutTitle(
//        title = "Kaz Workout"
//    ),
)

val exerciseTitles = listOf<ExerciseTitle>(
    ExerciseTitle(
        id = 1,
        title = "Shouldering"
    ),
    ExerciseTitle(
        id = 2,
        title = "Squat"
    ),
    ExerciseTitle(
        id = 3,
        title = "Lunges (each side)"
    ),
    ExerciseTitle(
        id = 4,
        title = "Romanian DL"
    ),
    ExerciseTitle(
        id = 5,
        title = "DL"
    ),
    ExerciseTitle(
        id = 6,
        title = "Farmers"
    ),
    ExerciseTitle(
        id = 7,
        title = "Dips"
    ),
)

fun workoutWeeks(): List<WorkoutWeek> {

    val workoutWeeksList = mutableListOf<WorkoutWeek>()

    for (i in 1..6) {
        workoutWeeksList.add(
            WorkoutWeek(
                week = "Week $i",
                workoutTitleId = 1
            )
        )
    }

    return workoutWeeksList
}

fun workoutDays(): List<WorkoutDay> {

    val workoutDaysList = mutableListOf<WorkoutDay>()

    for (i in 1..6) { // workoutWeek id's
        for (j in 1..6) {
            workoutDaysList.add(
                WorkoutDay(
                    day = "Day $j",
                    workoutWeekId = i
                )
            )
        }
    }

    return workoutDaysList
}

fun workoutSets(): List<Set> {

    val setsList = mutableListOf<Set>()

    for (i in 1..36 step 6) { // 6 weeks, 6 days = 36, step 6 starts at 1st day of each week, these are 1st 36 days, other days will have to start from a different dayId and count from there
        for (j in 1..3) { // 3 sets
            setsList.add(
                Set(
                    setNum = j,
                    weight = 0,
                    reps = 10,
                    exerciseId = 1,
                    isCompleted = 0,
                    dayId = i
                )
            )
        }
    }

    return setsList
}