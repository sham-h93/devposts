plugins {
    alias(libs.plugins.devposts.android.feature)
}

android {
    namespace = "com.hshamkhani.navigation"
}

dependencies {
    implementation(projects.features.articles)
    implementation(projects.features.articleDetails)
    implementation(libs.navigation.compose)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
}
