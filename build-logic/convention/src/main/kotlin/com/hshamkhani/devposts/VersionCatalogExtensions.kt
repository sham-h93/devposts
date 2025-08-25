package com.hshamkhani.devposts

import org.gradle.api.Project
import org.gradle.api.artifacts.ExternalModuleDependencyBundle
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import org.gradle.api.provider.Provider


val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun VersionCatalog.getVersion(version: String) = findVersion(version).get()

internal fun VersionCatalog.getLibrary(library: String) = findLibrary(library).get()

internal fun VersionCatalog.getBundle(bundle: String) = findBundle(bundle).get()

//versions
internal val VersionCatalog.minSdk: Int
    get() = getVersion("minSdk").toString().toInt()

internal val VersionCatalog.compileSdk: Int
    get() = getVersion("compileSdk").toString().toInt()


internal val VersionCatalog.targetSdk: Int
    get() = getVersion("targetSdk").toString().toInt()

// Libraries
internal val VersionCatalog.androidxCoreKtx: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("androidx.core.ktx")

internal val VersionCatalog.androidxLifecycleRuntimeKtx: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("androidx.lifecycle.runtime.ktx")

internal val VersionCatalog.androidxLifecycleViewModelCompose: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("androidx.lifecycle.viewmodel.compose")

internal val VersionCatalog.androidxActivityCompose: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("androidx.activity.compose")

internal val VersionCatalog.androidxComposeBom: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("androidx.compose.bom")

internal val VersionCatalog.androidxComposeBundle: Provider<ExternalModuleDependencyBundle>
    get() = getBundle("compose")

internal val VersionCatalog.androidxComposeUi: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("androidx-ui")

internal val VersionCatalog.androidxComposeUiGraphics: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("androidx-ui-graphics")

internal val VersionCatalog.androidxComposeUiToolingPreview: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("androidx-ui-tooling-preview")

internal val VersionCatalog.androidxComposeMaterial3: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("androidx-material3")

internal val VersionCatalog.androidxUiTooling: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("androidx.ui.tooling")

internal val VersionCatalog.androidxUitestJunit: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("androidx.ui.test.junit4")

internal val VersionCatalog.androidxEspressoCore: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("androidx.espresso.core")

internal val VersionCatalog.androidxJunit: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("androidx.junit")

internal val VersionCatalog.jUnit: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("junit")

internal val VersionCatalog.mockk: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("mockk")

internal val VersionCatalog.mockkAgent: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("mockk.agent")

internal val VersionCatalog.mockAndroid: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("mockk.android")

internal val VersionCatalog.truth: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("google.truth")

internal val VersionCatalog.androidxUiTestManifest: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("androidx.ui.test.manifest")

internal val VersionCatalog.navigationCompose: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("navigation.compose")

internal val VersionCatalog.hiltNavigationCompose: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("hilt.navigation.compose")

internal val VersionCatalog.coroutinesCore: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("coroutines.core")

internal val VersionCatalog.coroutinesTest: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("coroutines.test")

internal val VersionCatalog.hiltCore: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("hilt.core")

internal val VersionCatalog.hiltAndroid: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("hilt.android")

internal val VersionCatalog.hiltCompiler: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("hilt.compiler")

internal val VersionCatalog.hiltExtCompiler: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("hilt.ext.compiler")

internal val VersionCatalog.hiltAndroidTest: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("hilt-android-test")

internal val VersionCatalog.javaXInject: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("javax.inject")

internal val VersionCatalog.roomRuntime: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("room.runtime")

internal val VersionCatalog.roomKtx: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("room.ktx")

internal val VersionCatalog.roomCompiler: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("room.compiler")

internal val VersionCatalog.roomPaging: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("room.paging")

internal val VersionCatalog.roomTesting: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("room.testing")

