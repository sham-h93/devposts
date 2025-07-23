plugins {
    alias(libs.plugins.hotlineNewsAndroidLibrary)
    alias(libs.plugins.hotlineNewsHilt)
    alias(libs.plugins.hotlineNewsRoom)
    alias(libs.plugins.hotlineNewsLibraryTest)
}

android {
    namespace = "com.hshamkhani.datasource"
}

dependencies {
    implementation(projects.data.repository)
}
