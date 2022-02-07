package com.paulvickers.fit4life.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WorkoutDay(
    @PrimaryKey val id: Int? = null,
    val day: String,
    val workoutTitleId: Int
)
