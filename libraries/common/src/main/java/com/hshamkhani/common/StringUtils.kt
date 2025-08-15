package com.hshamkhani.common

/**
 * Adds [that] to this string, separated by " - ", only if [that] is not null or empty,
 * otherwise returns only this.
 */
infix fun String.withOrWithout(that: String?): String =
    this + that.takeIf { it.isNullOrEmpty().not() }?.let { " - $it" }.orEmpty()

fun List<String>.withHashtag(): List<String> = this.map { "#$it" }
