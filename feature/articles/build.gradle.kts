plugins {
    alias(libs.plugins.hotlineNewsAndroidFeature)
    alias(libs.plugins.hotlineNewsLibraryTest)
    alias(libs.plugins.hotlineNewsAndroidLibraryTest)
    alias(libs.plugins.hotlineNewsHilt)
    alias(libs.plugins.kotlin.serialization)
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
