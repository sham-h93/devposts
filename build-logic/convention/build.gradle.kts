import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.hshamkhani.devposts.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
    compileOnly(libs.androidx.room.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("AndroidApplicationPlugin") {
            id = "devposts.android.application"
            implementationClass = "AndroidApplication"
        }
        register("AndroidComposeApplicationPlugin") {
            id = libs.plugins.devposts.android.application.compose.get().pluginId
            implementationClass = "AndroidApplicationCompose"
        }
        register("FeatureConventionPlugin") {
            id = libs.plugins.devposts.android.feature.get().pluginId
            implementationClass = "AndroidFeature"
        }
        register("HiltConventionPlugin") {
            id = "devposts.android.hilt"
            implementationClass = "Hilt"
        }
        register("HiltTestConventionPlugin") {
            id = libs.plugins.devposts.hilt.test.get().pluginId
            implementationClass = "HiltTest"
        }
        register("libraryConventionPlugin") {
            id = "devposts.android.library"
            implementationClass = "AndroidLibrary"
        }
        register("RoomConventionPlugin") {
            id = libs.plugins.devposts.room.get().pluginId
            implementationClass = "Room"
        }
        register("JvmlibraryConventionPlugin") {
            id = libs.plugins.devposts.jvm.library.get().pluginId
            implementationClass = "JvmLibrary"
        }
        register("KotlinlibraryConventionPlugin") {
            id = libs.plugins.devposts.kotlin.library.get().pluginId
            implementationClass = "KotlinLibrary"
        }
        register("AndroidLibraryTestConventionPlugin") {
            id = libs.plugins.devposts.android.library.test.get().pluginId
            implementationClass = "AndroidTest"
        }
        register("LibraryTestConventionPlugin") {
            id = libs.plugins.devposts.library.test.get().pluginId
            implementationClass = "LibraryTest"
        }
    }
}