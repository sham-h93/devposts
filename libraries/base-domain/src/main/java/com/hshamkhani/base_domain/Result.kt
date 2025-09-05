package com.hshamkhani.base_domain

/**
 * Represents the outcome of an operation, which can either be a success or a failure.
 *
 * @param D The type of data returned in case of success.
 * @param E The type of error returned in case of failure. Keep in mind that the error must extends [Error] class.
 */
sealed class Result<out D, out E : Error> {

    /**
     * Represents a failed result containing an error of type [E].
     *
     * @param error The error that occurred during the operation.
     */
    data class Failure<out E : Error>(val error: E) : Result<Nothing, E>()

    /**
     * Represents a successful result containing data of type [D].
     *
     * @param data The data returned from the successful operation.
     */
    data class Success<out D>(val data: D) : Result<D, Nothing>()
}

/**
 * Wraps a block of code and converts into a Result.
 * @param block The code to execute
 * @param mapError Function to convert Exception to a custom error
 */
suspend fun <T, E : Error> wrap(
    block: suspend () -> T,
    mapError: suspend (Exception) -> E,
): Result<T, E> = try {
    Result.Success(data = block())
} catch (e: Exception) {
    e.printStackTrace()
    val error = mapError.invoke(e)
    Result.Failure(error = error)
}

/**
 * Executes the appropriate callback based on the given result.
 *
 * - If the result is [Result.Success], the [onSuccess] function is called with the successful data.
 * - If the result is [Result.Failure], the [onFailure] function is called with the error.
 **
 * @param onSuccess Callback invoked with data if the result is successful.
 * @param onFailure Callback invoked with error if the result is a failure.
 */
inline fun <D, E : Error> Result<D, E>.doOnResult(success: (D) -> Unit, failure: (E) -> Unit) {
    when (this) {
        is Result.Success -> success(data)
        is Result.Failure -> failure(error)
    }
}
