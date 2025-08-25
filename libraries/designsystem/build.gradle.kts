plugins {
    alias(libs.plugins.devposts.android.feature)
}

android {
    namespace = "com.hshamkhani.designsystem"
}

dependencies {
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
}
