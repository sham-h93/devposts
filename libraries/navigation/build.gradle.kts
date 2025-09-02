plugins {
    alias(libs.plugins.devposts.android.feature)
}

android {
    namespace = "com.hshamkhani.navigation"
}

dependencies {
    implementation(projects.feature.articles)
    implementation(projects.feature.articleDetails)
    implementation(libs.navigation.compose)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
}
