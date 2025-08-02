plugins {
    alias(libs.plugins.derief.android.application)
    alias(libs.plugins.derief.android.application.compose)
    alias(libs.plugins.derief.hilt)
}

android {
    namespace = "com.hshamkhani.derief"

    defaultConfig {
        applicationId = "com.hshamkhani.derief"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(projects.domain)
    implementation(projects.data.datasource)
    implementation(projects.data.repository)
    implementation(projects.feature.articles)
    implementation(projects.feature.articledetails)
    implementation(projects.libraries.designsystem)
    implementation(projects.libraries.core)
    implementation(projects.libraries.common)
    implementation(projects.libraries.navigation)
    implementation(libs.navigation.compose)
}
