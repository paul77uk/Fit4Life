package com.paulvickers.fit4life.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
//    foreignKeys = [ForeignKey(
//        entity = WorkoutDay::class,
//        parentColumns = ["id"],
//        childColumns = ["dayId"],
//        onDelete = ForeignKey.CASCADE
//    )]
)
data class ExerciseTitle(
    @PrimaryKey() val id: Int? = null,
    var title: String,
//    val dayId: Int
)