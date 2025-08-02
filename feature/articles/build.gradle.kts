plugins {
    alias(libs.plugins.derief.android.feature)
    alias(libs.plugins.derief.library.test)
    alias(libs.plugins.derief.android.library.test)
    alias(libs.plugins.derief.hilt)
}

android {
    namespace = "com.hshamkhani.articles"
}

dependencies {
    implementation(projects.domain)
    implementation(projects.libraries.designsystem)
    implementation(libs.paging.compose)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
}
