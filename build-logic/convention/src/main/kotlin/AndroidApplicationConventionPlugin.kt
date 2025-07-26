import com.android.build.api.dsl.ApplicationExtension
import com.hshamkhani.hotlinenews.configureKotlinAndroid
import com.hshamkhani.hotlinenews.libs
import com.hshamkhani.hotlinenews.targetSdk
import com.plcoding.convention.GradleExtensionType
import com.plcoding.convention.configureBuildTypes
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin: Plugin<Project> {

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