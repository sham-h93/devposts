plugins {
    alias(libs.plugins.devposts.android.feature)
    alias(libs.plugins.devposts.library.test)
    alias(libs.plugins.devposts.android.library.test)
    alias(libs.plugins.devposts.hilt)
}

android {
    namespace = "com.hshamkhani.articledetails"
}

dependencies {
    implementation(projects.domain)
    implementation(projects.libraries.common)
    implementation(projects.libraries.designsystem)
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
}
