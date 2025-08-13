package com.hshamkhani.common

infix fun String.and(that: String): String =
    this + that.takeIf { it.isNotEmpty() }?.let { "-$it" }.orEmpty()

fun List<String>.withHashtag(): List<String> = this.map { "#$it" }
