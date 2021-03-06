package com.paulvickers.fit4life.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = WorkoutWeek::class,
        parentColumns = ["id"],
        childColumns = ["workoutWeekId"],
        onDelete = ForeignKey.CASCADE, /* when delete the parent class workoutTitle,
        also deletes workoutDay which is related by the child column workoutTitleId */
    )]
)
data class WorkoutDay(
    @PrimaryKey var id: Int? = null,
    val day: String,
    val workoutWeekId: Int
)