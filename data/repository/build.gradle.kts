plugins {
    alias(libs.plugins.hotlineNewsAndroidLibrary)
    alias(libs.plugins.hotlineNewsHilt)
    alias(libs.plugins.hotlineNewsLibraryTest)
}
android {
    namespace = "com.hshamkhani.repository"
}

dependencies {
    implementation(projects.domain)
    implementation(libs.paging.common)
}
