package com.paulvickers.fit4life.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = WorkoutDay::class,
        parentColumns = ["id"],
        childColumns = ["dayId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Set(
    @PrimaryKey() val id: Int? = null,
    val setNum: Int,
    val weight: Int = 0,
    val reps: Int = 0,
    val distance: Int = 0,
    val exerciseId: Int,
    val time: Double = 00.00,
    val isCompleted: Int,
    val exerciseForSetsId: Int,
    val dayId: Int
)