package com.paulvickers.fit4life.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = ExerciseTitle::class,
        parentColumns = ["id"],
        childColumns = ["exerciseId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Set(
    @PrimaryKey() val id: Int? = null,
    val setTitle: String,
    val setNum: Int,
    var weight: Int,
    val reps: Int,
    val exerciseId: Int
)