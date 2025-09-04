plugins {
    alias(libs.plugins.devposts.android.feature)
}

android {
    namespace = "com.hshamkhani.design_system"
}

dependencies {
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
}
