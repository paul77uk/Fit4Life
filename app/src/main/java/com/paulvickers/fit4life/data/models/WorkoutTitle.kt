package com.paulvickers.fit4life.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WorkoutTitle(
    @PrimaryKey()
    var id: Int? = null,
    var title: String
)