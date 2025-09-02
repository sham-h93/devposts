package com.hshamkhani.base_domain

import javax.inject.Qualifier

/**
 * A custom qualifier annotation for dependency injection to specify the type of dispatcher.
 * */

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultDispatcher
