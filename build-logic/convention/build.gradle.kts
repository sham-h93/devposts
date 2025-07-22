import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.hshamkhani.hotlinenews.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
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
        register("hotlineNewsAndroidApplication") {
            id = "hotlinenews.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("hotlineNewsAndroidComposeApplication") {
            id = "hotlinenews.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("hotlineNewsFeatureConvention") {
            id = "hotlinenews.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("hotlineNewsHiltConvention") {
            id = "hotlinenews.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("hotlineNewsHiltTestConvention") {
            id = "hotlinenews.test.hilt"
            implementationClass = "AndroidHiltTestConventionPlugin"
        }
        register("hotlineNewslibraryConvention") {
            id = "hotlinenews.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("hotlineNewsRoomConvention") {
            id = "hotlinenews.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("hotlineNewsJvmlibraryConvention") {
            id = "hotlinenews.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("hotlineNewsKotlinlibraryConvention") {
            id = "hotlinenews.kotlin.library"
            implementationClass = "KotlinLibraryConventionPlugin"
        }
        register("hotlineNewsAndroidLibraryTestConvention") {
            id = "hotlinenews.test.android"
            implementationClass = "AndroidTestConventionPlugin"
        }
        register("hotlineNewsLibraryTestConvention") {
            id = "hotlinenews.test.library"
            implementationClass = "LibraryTestConventionPlugin"
        }
    }
}