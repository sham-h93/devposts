package com.hshamkhani.base_domain

import java.io.IOException

/**
 * Represents possible error types that can occur in the application.
 */
sealed interface Error {

    /**
     * Represents errors related to local operations (e.g., database, file system).
     */
    enum class Local : Error {
        IO,
        Unknown,
    }

    /**
     * Represents an error response received from the API.
     */
    enum class Api : Error {
        BadRequest,
        Unauthorized,
        ToManyRequests,
        ServerError,
        Unknown,
        // You can add more!
    }

    /**
     * Represents a network connectivity error (e.g., no internet).
     */
    data object Network : Error

    /**
     * Represents an unknown or unexpected error.
     */
    data object Unknown : Error
}

/**
 * Maps an exception to a corresponding [Error] of type [Error.Local].
 *
 * @param Exception The given Exception.
 * @return The corresponding [Error] of type [Error.Local] for known Exceptions.
 */
fun Exception.asLocalError(): Error.Local = when (this) {
    is IOException -> Error.Local.IO
    else -> Error.Local.Unknown
}

/**
 * Maps an HTTP status code to a corresponding [Error] of type [Error.Api].
 *
 * @param code The HTTP status code received from the API.
 * @return The corresponding [Error.Api] for known codes.
 */
fun Int.asApiError(): Error.Api = when (this) {
    400 -> Error.Api.BadRequest
    401 -> Error.Api.Unauthorized
    429 -> Error.Api.ToManyRequests
    in 500..599 -> Error.Api.ServerError
    else -> Error.Api.Unknown
}
