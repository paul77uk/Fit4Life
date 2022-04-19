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
    var weight: Int,
    val repsDistTime: Int,
    val exerciseId: Int,
    val isCompleted: Int,
    val isRepsDistTime: Int = 1,
    val exerciseForSetsId: Int,
    val dayId: Int
)