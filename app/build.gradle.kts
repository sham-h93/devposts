plugins {
    alias(libs.plugins.hotlineNewsAndroidApplication)
    alias(libs.plugins.hotlineNewsAndroidComposeApplication)
    alias(libs.plugins.hotlineNewsHilt)
}

android {
    namespace = "com.hshamkhani.hotlinenews"

    defaultConfig {
        applicationId = "com.hshamkhani.hotlinenews"
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
