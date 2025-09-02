package com.hshamkhani.base_domain

/**
 * Represents common HTTP API error types.
 *
 * This enum class maps known HTTP status codes to meaningful error types
 * used in the application logic.
 *
 * @see fromCode Used to convert raw HTTP status codes to [fromCode] values.
 */
enum class ApiError {
    BadRequest,
    Unauthorized,
    ToManyRequests,
    ServerError,
    ;

    companion object {
        /**
         * Maps an HTTP status code to a corresponding [ApiError].
         *
         * @param code The HTTP status code received from the API.
         * @return The corresponding [ApiError] for known codes.
         * @throws IllegalArgumentException If the status code is not recognized.
         */
        fun fromCode(code: Int): ApiError = when (code) {
            400 -> BadRequest
            401 -> Unauthorized
            429 -> ToManyRequests
            in 500..599 -> ServerError
            else -> throw IllegalArgumentException("Unknown API error code: $code")
        }
    }
}
