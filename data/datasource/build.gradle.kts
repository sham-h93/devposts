plugins {
    alias(libs.plugins.devposts.android.library)
    alias(libs.plugins.devposts.library.test)
    alias(libs.plugins.devposts.room)
    alias(libs.plugins.devposts.hilt)
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
