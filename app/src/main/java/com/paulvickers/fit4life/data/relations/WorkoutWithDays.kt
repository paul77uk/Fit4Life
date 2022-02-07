package com.paulvickers.fit4life.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.paulvickers.fit4life.domain.model.WorkoutDay
import com.paulvickers.fit4life.domain.model.WorkoutTitle

data class WorkoutWithDays(
    @Embedded val title: WorkoutTitle,
    @Relation(
        parentColumn = "id",
        entityColumn = "workoutTitleId"
    )
    val workoutDay: List<WorkoutDay>
)
