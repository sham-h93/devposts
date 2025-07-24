package com.hshamkhani.common

import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object TimeFormatter {
    fun getYesterday(): String {
        val yesterday = LocalDate.now().minusDays(1)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return yesterday.format(formatter)
    }

    /**
     * Format iso time to readable time format
     * */
    fun toReadableFormat(time: String): String {
        val zonedDateTime = ZonedDateTime.parse(time)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return zonedDateTime.format(formatter)
    }
}
