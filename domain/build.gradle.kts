plugins {
    alias(libs.plugins.hotlineNewsKotlinlibrary)
    alias(libs.plugins.hotlineNewsLibraryTest)
}

dependencies {
    api(projects.libraries.core)
    implementation(libs.paging.common)
}
