plugins {
    alias(libs.plugins.derief.kotlin.library)
    alias(libs.plugins.derief.library.test)
}

dependencies {
    api(projects.libraries.core)
    implementation(libs.paging.common)
}
