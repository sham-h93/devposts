plugins {
    alias(libs.plugins.hotlineNewsAndroidApplication)
    alias(libs.plugins.hotlineNewsAndroidComposeApplication)
}

android {
    namespace = "com.hshamkhani.hotlinenews"

    defaultConfig {
        applicationId = "com.hshamkhani.hotlinenews"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

}

dependencies {

}