plugins {
    alias(libs.plugins.devposts.android.feature)
    alias(libs.plugins.devposts.library.test)
    alias(libs.plugins.devposts.android.library.test)
    alias(libs.plugins.devposts.hilt)
}

android {
    namespace = "com.hshamkhani.articles"
}

dependencies {
    implementation(projects.domain)
    implementation(projects.libraries.baseFeature)
    implementation(projects.devposts.libraries.designSystem)
    implementation(projects.libraries.common)
    implementation(libs.paging.compose)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
}
