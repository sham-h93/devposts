package com.hshamkhani.common

import com.google.common.truth.Truth.assertThat
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import org.junit.Before
import org.junit.Test

internal class TimeFormatterTest {
    private lateinit var now: ZonedDateTime

    @Before
    fun setUp() {
        now = ZonedDateTime.now()
    }

    @Test
    fun toTimeStampInMillis_convertIsoTime_returnsCorrectTimeStampInMillis() {
        // Given
        val timestampString = now.toString()

        // When
        val result = timestampString.toTimeStampInMillis()

        // Then
        val expected = ZonedDateTime.parse(timestampString).toInstant().toEpochMilli()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun toReadableTime_timeStampWasEarlierThanOneMinuteAgo_returnsJustNow() {
        // Given
        val timestamp = now.minusSeconds(30).toInstant().toEpochMilli()
        val expected = "just now"
        // When
        val result = timestamp.toReadableFormat()

        // Then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun toReadableTime_timeStampWas50SecondsAgo_returnsOneMinuteAgo() {
        // Given
        val timestamp = now.minusSeconds(60).toInstant().toEpochMilli()
        val expected = "1 minute ago"
        // When
        val result = timestamp.toReadableFormat()

        // Then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun toReadableTime_timeStampWasEarlierThen60MinuteAgo_returnsNMinutesAgo() {
        // Given
        val timestamp = now.minusMinutes(20).toInstant().toEpochMilli()
        val expected = "20 minutes ago"
        // When
        val result = timestamp.toReadableFormat()

        // Then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun toReadableTime_timeStampWasEarlierThenTenHourAgo_returnsOneHourAgo() {
        // Given
        val timestamp = now.minusHours(1).toInstant().toEpochMilli()
        val expected = "1 hour ago"
        // When
        val result = timestamp.toReadableFormat()

        // Then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun toReadableTime_timeStampWasEarlierThen24HoursAgo_returnsNHoursAgo() {
        // Given
        val timestamp = now.minusHours(20).toInstant().toEpochMilli()
        val expected = "20 hours ago"
        // When
        val result = timestamp.toReadableFormat()

        // Then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun toReadableTime_timeStampWasGreater24HoursAgo_returnsFormattedDateTime() {
        // Given
        val instant = now.minusDays(1).toInstant()
        val timestamp = instant.toEpochMilli()
        val formatter = DateTimeFormatter.ofPattern("yyyy MMM dd")
            .withZone(ZoneId.systemDefault())
        val expected = formatter.format(instant)

        // When
        val result = timestamp.toReadableFormat()

        // Then
        assertThat(result).isEqualTo(expected)
    }
}
