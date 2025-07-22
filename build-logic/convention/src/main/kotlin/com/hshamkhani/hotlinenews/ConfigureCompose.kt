package com.hshamkhani.hotlinenews

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.configureComposeAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }
    }
    dependencies {
        val bom = libs.androidxComposeBom.get()
        add("implementation", platform(bom))
        add("androidTestImplementation", platform(bom))
        add("debugImplementation", platform(bom))
    }
}