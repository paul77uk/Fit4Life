package com.paulvickers.fit4life.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WorkoutTitle(
    @PrimaryKey val id: Int? = null,
    val title: String
)

class InvalidWorkoutTitleException(message: String): Exception(message)
