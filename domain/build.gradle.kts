plugins {
    alias(libs.plugins.devposts.kotlin.library)
    alias(libs.plugins.devposts.library.test)
}

dependencies {
    api(projects.libraries.core)
    implementation(libs.paging.common)
}
