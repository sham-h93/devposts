plugins {
    alias(libs.plugins.derief.android.feature)
}

android {
    namespace = "com.hshamkhani.navigation"
}

dependencies {
    implementation(projects.feature.articles)
    implementation(projects.feature.articledetails)
    implementation(libs.navigation.compose)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
}
