plugins {
    alias(libs.plugins.hotlineNewsAndroidFeature)
}

android {
    namespace = "com.hshamkhani.navigation"
}

dependencies {
    implementation(projects.feature.articles)
    implementation(projects.feature.articledetails)
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
}
