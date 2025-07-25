plugins {
    alias(libs.plugins.hotlineNewsAndroidLibrary)
    alias(libs.plugins.hotlineNewsLibraryTest)
    alias(libs.plugins.hotlineNewsHilt)
}
android {
    namespace = "com.hshamkhani.repository"
}

dependencies {
    implementation(projects.domain)
    implementation(libs.paging.common)
}
