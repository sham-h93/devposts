package com.hshamkhani.common

import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun LocalDateTime.toDateTimeString(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())
    return format(formatter)
}

/**
 * Format iso time to readable time format
 * */
fun String.toReadableFormat(): String {
    val zonedDateTime = ZonedDateTime.parse(this)
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return zonedDateTime.format(formatter)
}

/**
 * Get timestamp in millis from string date
 * */
fun String.toTimeStampInMillis(): Long = ZonedDateTime.parse(this).toInstant().toEpochMilli()

/**
 * Format timestamp to readable time format
 * */
fun Long.toReadableFormat(): String {
    val instant = Instant.ofEpochMilli(this)
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        .withZone(ZoneId.systemDefault())
    val minutes = Duration.between(Instant.ofEpochMilli(this), Instant.now()).toMinutes()
    return when {
        minutes < 1 -> "just now"
        minutes < 60 -> "$minutes minute${if (minutes > 1) "s" else ""} ago"
        minutes < 1440 -> "${minutes / 60} hour${if (minutes / 60 > 1) "s" else ""} ago"
        else -> formatter.format(instant)
    }
}
