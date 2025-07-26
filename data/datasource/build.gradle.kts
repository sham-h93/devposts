plugins {
    alias(libs.plugins.hotlineNewsAndroidLibrary)
    alias(libs.plugins.hotlineNewsLibraryTest)
    alias(libs.plugins.hotlineNewsRoom)
    alias(libs.plugins.hotlineNewsHilt)
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
