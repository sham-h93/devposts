plugins {
    alias(libs.plugins.derief.android.library)
    alias(libs.plugins.derief.library.test)
    alias(libs.plugins.derief.room)
    alias(libs.plugins.derief.hilt)
}

android {
    namespace = "com.hshamkhani.datasource"
}

dependencies {
    implementation(projects.data.repository)
    implementation(projects.libraries.common)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.bundles.ktor)
    implementation(libs.paging.common)
}
