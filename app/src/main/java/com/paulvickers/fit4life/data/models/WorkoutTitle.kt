package com.paulvickers.fit4life.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class WorkoutTitle(
    @PrimaryKey() var id: Int? = null,
    var title: String
): Parcelable