package com.hshamkhani.core

typealias ErrorResult = Error

sealed class Result<out D, out E : Error> {
    data class Error<out E>(
        val error: E,
    ) : Result<Nothing, ErrorResult>()

    data class Success<out D>(
        val data: D,
    ) : Result<D, Nothing>()
}
