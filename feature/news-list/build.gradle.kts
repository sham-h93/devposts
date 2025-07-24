plugins {
    alias(libs.plugins.hotlineNewsAndroidFeature)
    alias(libs.plugins.hotlineNewsLibraryTest)
    alias(libs.plugins.hotlineNewsAndroidLibraryTest)
    alias(libs.plugins.hotlineNewsHilt)
}

android {
    namespace = "com.hshamkhani.news_list"
}

dependencies {
    implementation(projects.domain)
    implementation(libs.paging.compose)
}
