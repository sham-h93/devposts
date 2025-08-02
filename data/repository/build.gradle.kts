plugins {
    alias(libs.plugins.derief.android.library)
    alias(libs.plugins.derief.library.test)
    alias(libs.plugins.derief.hilt)
}
android {
    namespace = "com.hshamkhani.repository"
}

dependencies {
    implementation(projects.domain)
    implementation(libs.paging.common)
}
