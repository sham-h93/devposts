plugins {
    alias(libs.plugins.devposts.android.application)
    alias(libs.plugins.devposts.android.application.compose)
    alias(libs.plugins.devposts.hilt)
}

android {
    namespace = "com.hshamkhani.devposts"

    defaultConfig {
        applicationId = "com.hshamkhani.devposts"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(projects.domain)
    implementation(projects.data.datasource)
    implementation(projects.data.repository)
    implementation(projects.features.articles)
    implementation(projects.features.articleDetails)
    implementation(projects.libraries.baseFeature)
    implementation(projects.libraries.baseDomain)
    implementation(projects.libraries.common)
    implementation(projects.libraries.navigation)
    implementation(libs.navigation.compose)
}
