package com.hshamkhani.core

import javax.inject.Qualifier

/**
 * A custom qualifier annotation for dependency injection to specify the type of dispatcher.
 * */

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Dispatcher(val dispatchers: Dispatchers)

enum class Dispatchers {
    Default,
    IO,
}
