plugins {
    alias(libs.plugins.devposts.android.feature)
    alias(libs.plugins.devposts.library.test)
    alias(libs.plugins.devposts.android.library.test)
    alias(libs.plugins.devposts.hilt)
}

android {
    namespace = "com.hshamkhani.article_details"
}

dependencies {
    implementation(projects.domain)
    implementation(projects.devposts.libraries.designSystem)
    implementation(projects.libraries.common)
    implementation(projects.libraries.baseFeature)
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
}
