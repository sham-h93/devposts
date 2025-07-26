package com.hshamkhani.common

import java.time.LocalDateTime
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
fun String.ToTimeStampInMillis(): Long = ZonedDateTime.parse(this).toInstant().toEpochMilli()
