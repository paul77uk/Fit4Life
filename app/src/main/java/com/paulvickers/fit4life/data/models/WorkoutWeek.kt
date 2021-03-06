package com.paulvickers.fit4life.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = WorkoutTitle::class,
        parentColumns = ["id"],
        childColumns = ["workoutTitleId"],
        onDelete = ForeignKey.CASCADE, /* when delete the parent class workoutTitle,
        also deletes workoutDay which is related by the child column workoutTitleId */
    )]
)
data class WorkoutWeek(
    @PrimaryKey var id: Int? = null,
    var week: String,
    var workoutTitleId: Int
)