plugins {
    alias(libs.plugins.hotlineNewsAndroidLibrary)
    alias(libs.plugins.hotlineNewsLibraryTest)
    alias(libs.plugins.hotlineNewsRoom)
    alias(libs.plugins.hotlineNewsHilt)
}

android {
    namespace = "com.hshamkhani.datasource"

    buildTypes {
        getByName("debug") {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://newsapi.org/v2/\"",
            )
            buildConfigField(
                "String",
                "API_KEY",
                "\"87d761730ed34e2b9466ea6dcdd6dd22\"",
            )
        }
    }
}

dependencies {
    implementation(projects.data.repository)
    implementation(projects.libraries.common)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.bundles.ktor)
    implementation(libs.paging.common)
}
