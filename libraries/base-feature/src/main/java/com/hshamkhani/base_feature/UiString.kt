package com.hshamkhani.base_feature

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

/**
 * Represents a string that can either be  a raw/dynamic string (e.g., from server response or user
 * input), or a string resource (defined in `res/values/strings.xml`).
 */
sealed class UiString {

    /**
     * Represents a dynamic (raw) string that comes directly as text.
     */
    data class Dynamic(val string: String) : UiString()

    /**
     * Represents a localized string resource, identified by its resource ID.
     */
    data class Resource(val resId: Int) : UiString()
}

/**
 * Extension for converting a [UiString] into a regular [String] inside a Composable.
 */
@Composable
fun UiString.asString(): String {
    val context = LocalContext.current
    return when (this) {
        is UiString.Dynamic -> string
        is UiString.Resource -> context.getString(resId)
    }
}

/**
 * Extension for converting a [UiString] into a regular [String]
 * when you already have a [Context] available (for non-Compose environments).
 */
fun UiString.asString(context: Context): String = when (this) {
    is UiString.Dynamic -> string
    is UiString.Resource -> context.getString(resId)
}

/**
 * Infix helper to build a [UiString] with fallback.
 *
 * If the current String is `null` or blank, a [UiString.Resource] will be used.
 * Otherwise, it wraps the non-null String into [UiString.Dynamic].
 *
 * Useful when you have optional server data but want to provide
 * a fallback from resources`.
 */
infix fun String?.or(resId: Int): UiString = if (this.isNullOrBlank()) {
    UiString.Resource(resId = resId)
} else {
    UiString.Dynamic(string = this)
}
