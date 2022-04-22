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
        title = "Lunges"
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
    ExerciseTitle(
        id = 8,
        title = "Shoulder Carry"
    ),
    ExerciseTitle(
        id = 9,
        title = "Lateral Raise"
    ),
    ExerciseTitle(
        id = 10,
        title = "Floor Press"
    ),
    ExerciseTitle(
        id = 11,
        title = "Floor Press Hold"
    ),
    ExerciseTitle(
        id = 12,
        title = "Flys"
    ),
    ExerciseTitle(
        id = 13,
        title = "Bent Row"
    ),
    ExerciseTitle(
        id = 14,
        title = "F4L Circuit",
        isCircuit = 1
    ),
    ExerciseTitle(
        id = 15,
        title = "PushUps",
    ),
    ExerciseTitle(
        id = 16,
        title = "Clean & Press",
    ),
)

fun workoutWeeks(): List<WorkoutWeek> {

    val workoutWeeksList = mutableListOf<WorkoutWeek>()

    for (i in 1..6) {
        workoutWeeksList.add(
            WorkoutWeek(
                id = i,
                week = "Week $i",
                workoutTitleId = 1
            )
        )
    }

    return workoutWeeksList
}

fun workoutDays(): List<WorkoutDay> {

    val workoutDaysList = mutableListOf<WorkoutDay>()

    var id = 1

    for (i in 1..6) { // workoutWeek id's
        for (j in 1..6) {
            workoutDaysList.add(
                WorkoutDay(
                    id = id,
                    day = "Day $j",
                    workoutWeekId = i
                )
            )
            id++
        }
    }

    return workoutDaysList
}

fun workoutSets(): List<Set> {

    val setsList = mutableListOf<Set>()

    //day 1 of each week
    for (i in 1..36 step 6) { // 6 weeks, 6 days = 36, step 6 starts at 1st day of each week, these are 1st 36 days, other days will have to start from a different dayId and count from there
        for (j in 1..3) { // 3 sets
            setsList.add(
                Set(
                    setNum = j,
                    weight = 0,
                    repsDistTime = 10,
                    exerciseId = 1,
                    isCompleted = 0,
                    isRepsDistTime = 1,
                    exerciseForSetsId = 1, // Shouldering
                    dayId = i
                )
            )
            setsList.add(
                Set(
                    setNum = j,
                    weight = 0,
                    repsDistTime = 10,
                    exerciseId = 1,
                    isCompleted = 0,
                    isRepsDistTime = 2,
                    exerciseForSetsId = 8, // Shoulder Carry
                    dayId = i
                )
            )
            setsList.add(
                Set(
                    setNum = j,
                    weight = 0,
                    repsDistTime = 10,
                    exerciseId = 1,
                    isCompleted = 0,
                    isRepsDistTime = 1,
                    exerciseForSetsId = 9, // Lateral Raise
                    dayId = i
                )
            )
        }
        for (k in 1..5) {
            setsList.add(
                Set(
                    setNum = k,
                    weight = 0,
                    repsDistTime = 10,
                    exerciseId = 1,
                    isCompleted = 0,
                    isRepsDistTime = 1,
                    exerciseForSetsId = 10, // Floor Press
                    dayId = i
                )
            )
        }
        for (l in 1..3) {
            setsList.add(
                Set(
                    setNum = l,
                    weight = 0,
                    repsDistTime = 30,
                    exerciseId = 1,
                    isCompleted = 0,
                    isRepsDistTime = 3,
                    exerciseForSetsId = 11, // Floor Press Hold
                    dayId = i
                )
            )
            setsList.add(
                Set(
                    setNum = l,
                    weight = 0,
                    repsDistTime = 10,
                    exerciseId = 1,
                    isCompleted = 0,
                    isRepsDistTime = 1,
                    exerciseForSetsId = 12, // Flys
                    dayId = i
                )
            )
        }
    }

    //day 2 of each week
    for (m in 2..36 step 6) {
        for (n in 1..5) { // 5 sets
            setsList.add(
                Set(
                    setNum = n,
                    weight = 0,
                    repsDistTime = 10,
                    exerciseId = 1,
                    isCompleted = 0,
                    isRepsDistTime = 1,
                    exerciseForSetsId = 2, // Squat
                    dayId = m
                )
            )
        }
        for (o in 1..3) {
            setsList.add(
                Set(
                    setNum = o,
                    weight = 0,
                    repsDistTime = 10,
                    exerciseId = 1,
                    isCompleted = 0,
                    isRepsDistTime = 1,
                    exerciseForSetsId = 3, // Lunges
                    dayId = m
                )
            )
            setsList.add(
                Set(
                    setNum = o,
                    weight = 0,
                    repsDistTime = 10,
                    exerciseId = 1,
                    isCompleted = 0,
                    isRepsDistTime = 1,
                    exerciseForSetsId = 4, // Romanian DL
                    dayId = m
                )
            )
            setsList.add(
                Set(
                    setNum = o,
                    weight = 0,
                    repsDistTime = 10,
                    exerciseId = 1,
                    isCompleted = 0,
                    isRepsDistTime = 1,
                    exerciseForSetsId = 5, // Deadlift
                    dayId = m
                )
            )
            setsList.add(
                Set(
                    setNum = o,
                    weight = 0,
                    repsDistTime = 10,
                    exerciseId = 1,
                    isCompleted = 0,
                    isRepsDistTime = 2,
                    exerciseForSetsId = 6, // Farmers
                    dayId = m
                )
            )
            setsList.add(
                Set(
                    setNum = o,
                    weight = 0,
                    repsDistTime = 10,
                    exerciseId = 1,
                    isCompleted = 0,
                    isRepsDistTime = 1,
                    exerciseForSetsId = 13, // Bent Row
                    dayId = m
                )
            )
        }
    }

        for (p in 3..36 step 6) { // day 3 of each week
            for (i in 1..2) {
                setsList.add(
                    Set(
                        setNum = i,
                        weight = 0,
                        repsDistTime = 1,
                        exerciseId = 1, // Shouldering
                        isCompleted = 0,
                        isRepsDistTime = 3,
                        exerciseForSetsId = 14, // F4L Circuit
                        dayId = p
                    )
                )
            }
            setsList.add(
                Set(
                    setNum = 3,
                    weight = 0,
                    repsDistTime = 1,
                    exerciseId = 2, // Squat
                    isCompleted = 0,
                    isRepsDistTime = 3,
                    exerciseForSetsId = 14, // F4L Circuit
                    dayId = p
                )
            )
            setsList.add(
                Set(
                    setNum = 3,
                    weight = 0,
                    repsDistTime = 1,
                    exerciseId = 15, // PushUps
                    isCompleted = 0,
                    isRepsDistTime = 3,
                    exerciseForSetsId = 14, // F4L Circuit
                    dayId = p
                )
            )
            setsList.add(
                Set(
                    setNum = 3,
                    weight = 0,
                    repsDistTime = 1,
                    exerciseId = 16, // Clean & Press
                    isCompleted = 0,
                    isRepsDistTime = 3,
                    exerciseForSetsId = 14, // F4L Circuit
                    dayId = p
                )
            )
        }

return setsList
}