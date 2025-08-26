package com.hshamkhani.common

import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class StringUtilsTest {
    @Test
    fun withOrWithout_whenThatHasString_returnsOnlyThisDashThat() {
        // GIVEN
        val thisString = "First Word"
        val thatString = "Second Word"
        val expectedString = "$thisString - $thatString"

        // WHEN
        val outputString = thisString withOrWithout thatString

        // THEN
        assertThat(outputString).isEqualTo(expectedString)
    }

    @Test
    fun withOrWithout_whenThatIsNull_returnsOnlyThis() {
        // GIVEN
        val thisString = "First Word"
        val thatString = null
        val expectedString = thisString

        // WHEN
        val outputString = thisString withOrWithout thatString

        // THEN
        assertThat(outputString).isEqualTo(expectedString)
    }

    @Test
    fun withOrWithout_whenThatIsEmptyString_returnsOnlyThis() {
        // GIVEN
        val thisString = "First Word"
        val thatString = ""
        val expectedString = thisString

        // WHEN
        val outputString = thisString withOrWithout thatString

        // THEN
        assertThat(outputString).isEqualTo(expectedString)
    }

    @Test
    fun withHashtag_prependHashtagToItems_returnsListWithHashtags() {
        // GIVEN
        val items = listOf(
            "TagA",
            "TagB",
            "TagC",
        )
        val exceptedItems = items.map { "#$it" }

        // WHEN
        val output = items.withHashtag()

        // THEN
        assertThat(output).isEqualTo(exceptedItems)
    }
}
