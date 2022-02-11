package com.paulvickers.fit4life.domain.model

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
data class WorkoutDay(
    @PrimaryKey val id: Int? = null,
    val day: String,
    val workoutTitleId: Int
)