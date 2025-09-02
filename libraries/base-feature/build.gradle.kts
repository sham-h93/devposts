plugins {
    alias(libs.plugins.devposts.android.feature)
}

android {
    namespace = "com.hshamkhani.base_feature"
}

dependencies {
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
}
