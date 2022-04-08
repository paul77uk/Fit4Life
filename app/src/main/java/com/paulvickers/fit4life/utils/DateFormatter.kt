package com.paulvickers.fit4life.utils

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("EEE, d MMM k:mm",
        Locale.getDefault())
    return format.format(date)
}