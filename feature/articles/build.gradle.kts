plugins {
    alias(libs.plugins.hotlineNewsAndroidFeature)
    alias(libs.plugins.hotlineNewsLibraryTest)
    alias(libs.plugins.hotlineNewsAndroidLibraryTest)
    alias(libs.plugins.hotlineNewsHilt)
}

android {
    namespace = "com.hshamkhani.articles"
}

dependencies {
    implementation(projects.domain)
    implementation(projects.libraries.designsystem)
    implementation(libs.paging.compose)
}
