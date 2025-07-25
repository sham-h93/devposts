plugins {
    alias(libs.plugins.hotlineNewsAndroidFeature)
}

android {
    namespace = "com.hshamkhani.designsystem"
}

dependencies {
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
}
