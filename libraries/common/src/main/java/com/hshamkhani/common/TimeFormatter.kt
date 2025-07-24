package com.hshamkhani.common

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object TimeFormatter {
    fun getYesterday(): String {
        val yesterday = LocalDate.now().minusDays(1)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return yesterday.format(formatter)
    }
}
