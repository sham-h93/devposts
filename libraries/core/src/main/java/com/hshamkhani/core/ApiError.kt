package com.hshamkhani.core

enum class ApiError {
    BadRequest,
    Unauthorized,
    ToManyRequests,
    ServerError,
    ;

    companion object {
        fun fromCode(code: Int): ApiError =
            when (code) {
                400 -> BadRequest
                401 -> Unauthorized
                429 -> ToManyRequests
                in 500..599 -> ServerError
                else -> throw IllegalArgumentException("Unknown API error code: $code")
            }
    }
}
