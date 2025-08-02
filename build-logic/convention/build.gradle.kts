import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.hshamkhani.derief.buildlogic"

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
            id = "derief.android.application"
            implementationClass = "AndroidApplication"
        }
        register("AndroidComposeApplicationPlugin") {
            id = libs.plugins.derief.android.application.compose.get().pluginId
            implementationClass = "AndroidApplicationCompose"
        }
        register("FeatureConventionPlugin") {
            id = libs.plugins.derief.android.feature.get().pluginId
            implementationClass = "AndroidFeature"
        }
        register("HiltConventionPlugin") {
            id = "derief.android.hilt"
            implementationClass = "Hilt"
        }
        register("HiltTestConventionPlugin") {
            id = libs.plugins.derief.hilt.test.get().pluginId
            implementationClass = "HiltTest"
        }
        register("libraryConventionPlugin") {
            id = "derief.android.library"
            implementationClass = "AndroidLibrary"
        }
        register("RoomConventionPlugin") {
            id = libs.plugins.derief.room.get().pluginId
            implementationClass = "Room"
        }
        register("JvmlibraryConventionPlugin") {
            id = libs.plugins.derief.jvm.library.get().pluginId
            implementationClass = "JvmLibrary"
        }
        register("KotlinlibraryConventionPlugin") {
            id = libs.plugins.derief.kotlin.library.get().pluginId
            implementationClass = "KotlinLibrary"
        }
        register("AndroidLibraryTestConventionPlugin") {
            id = libs.plugins.derief.android.library.test.get().pluginId
            implementationClass = "AndroidTest"
        }
        register("LibraryTestConventionPlugin") {
            id = libs.plugins.derief.library.test.get().pluginId
            implementationClass = "LibraryTest"
        }
    }
}