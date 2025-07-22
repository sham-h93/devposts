plugins {
    alias(libs.plugins.hotlineNewsAndroidLibrary)
    alias(libs.plugins.hotlineNewsHilt)
    alias(libs.plugins.hotlineNewsRoom)
}
android {
    namespace = "com.hshamkhani.data"
}

dependencies {
    implementation(projects.domain)
}