package com.hshamkhani.core

typealias ErrorResult = Error

sealed class Result<out D, out E : ErrorResult> {
    data class Error<out E : ErrorResult>(val error: E) : Result<Nothing, E>()

    data class Success<out D>(val data: D) : Result<D, Nothing>()
}
