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
            id = libs.plugins.hotlineNewsAndroidApplication.get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("hotlineNewsAndroidComposeApplication") {
            id = libs.plugins.hotlineNewsAndroidComposeApplication.get().pluginId
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("hotlineNewsFeatureConvention") {
            id = libs.plugins.hotlineNewsAndroidFeature.get().pluginId
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("hotlineNewsHiltConvention") {
            id = libs.plugins.hotlineNewsHilt.get().pluginId
            implementationClass = "HiltConventionPlugin"
        }
        register("hotlineNewsHiltTestConvention") {
            id = libs.plugins.hotlineNewsHiltTestConvention.get().pluginId
            implementationClass = "HiltTestConventionPlugin"
        }
        register("hotlineNewslibraryConvention") {
            id = libs.plugins.hotlineNewsAndroidLibrary.get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("hotlineNewsRoomConvention") {
            id = libs.plugins.hotlineNewsRoom.get().pluginId
            implementationClass = "RoomConventionPlugin"
        }
        register("hotlineNewsJvmlibraryConvention") {
            id = libs.plugins.hotlineNewsJvmlibrary.get().pluginId
            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("hotlineNewsKotlinlibraryConvention") {
            id = libs.plugins.hotlineNewsKotlinlibrary.get().pluginId
            implementationClass = "KotlinLibraryConventionPlugin"
        }
        register("hotlineNewsAndroidLibraryTestConvention") {
            id = libs.plugins.hotlineNewsAndroidLibraryTest.get().pluginId
            implementationClass = "AndroidTestConventionPlugin"
        }
        register("hotlineNewsLibraryTestConvention") {
            id = libs.plugins.hotlineNewsLibraryTest.get().pluginId
            implementationClass = "LibraryTestConventionPlugin"
        }
    }
}