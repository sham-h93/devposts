package com.hshamkhani.core

sealed interface Error {
    data class Local(
        val error: Exception,
    ) : Error

    data class Api(
        val code: ApiError,
    ) : Error

    data object Network : Error

    data object Unknown : Error
}
