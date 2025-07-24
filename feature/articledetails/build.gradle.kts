plugins {
    alias(libs.plugins.hotlineNewsAndroidFeature)
    alias(libs.plugins.hotlineNewsLibraryTest)
    alias(libs.plugins.hotlineNewsAndroidLibraryTest)
    alias(libs.plugins.hotlineNewsHilt)
}

android {
    namespace = "com.hshamkhani.articledetails"
}

dependencies {
    implementation(projects.domain)
    implementation(projects.libraries.common)
    implementation(projects.libraries.designsystem)
}
