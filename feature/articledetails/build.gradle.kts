plugins {
    alias(libs.plugins.derief.android.feature)
    alias(libs.plugins.derief.library.test)
    alias(libs.plugins.derief.android.library.test)
    alias(libs.plugins.derief.hilt)
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
