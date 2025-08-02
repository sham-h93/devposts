import com.android.build.api.dsl.ApplicationExtension
import com.hshamkhani.derief.configureKotlinAndroid
import com.hshamkhani.derief.libs
import com.hshamkhani.derief.targetSdk
import com.hshamkhani.derief.GradleExtensionType
import com.hshamkhani.derief.configureBuildTypes
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplication: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply {
                plugin("org.jetbrains.kotlin.android")
                plugin("com.android.application")
            }
            extensions.configure<ApplicationExtension> {
                defaultConfig {
                    targetSdk = libs.targetSdk
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    vectorDrawables {
                        useSupportLibrary = true
                    }
                }
                configureKotlinAndroid(this)
                configureBuildTypes(
                    commonExtension = this,
                    gradleExtensionType = GradleExtensionType.Application
                )
            }
        }
    }
}