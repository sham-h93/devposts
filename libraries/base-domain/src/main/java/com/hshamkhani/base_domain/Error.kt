package com.hshamkhani.base_domain

/**
 * Represents possible error types that can occur in the application.
 */
sealed interface Error {

    /**
     * Represents errors related to local operations (e.g., database, file system).
     *
     * @property errorMessage A human-readable description of the error.
     */
    data class Local(val errorMessage: String) : Error

    /**
     * Represents an error response received from the API.
     *
     * @property code The [ApiError] corresponding to the HTTP status code.
     */
    data class Api(val code: ApiError) : Error

    /**
     * Represents a network connectivity error (e.g., no internet).
     */
    data object Network : Error

    /**
     * Represents an unknown or unexpected error.
     */
    data object Unknown : Error
}
inline fun <D, E : Error> Result<D, E>.handleError(success: (D) -> Unit, failure: (E) -> Unit) {
    when (this) {
        is Result.Success -> success(data)
        is Result.Failure -> failure(error)
    }
}
