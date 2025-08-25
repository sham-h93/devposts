plugins {
    alias(libs.plugins.devposts.android.library)
    alias(libs.plugins.devposts.library.test)
    alias(libs.plugins.devposts.hilt)
}
android {
    namespace = "com.hshamkhani.repository"
}

dependencies {
    implementation(projects.domain)
    implementation(libs.paging.common)
}
