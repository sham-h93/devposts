import com.android.build.gradle.LibraryExtension
import com.hshamkhani.derief.configureKotlinAndroid
import com.hshamkhani.derief.javaXInject
import com.hshamkhani.derief.libs
import com.hshamkhani.derief.targetSdk
import com.hshamkhani.derief.GradleExtensionType
import com.hshamkhani.derief.configureBuildTypes
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibrary : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply {
                plugin("com.android.library")
                plugin("org.jetbrains.kotlin.android")
                plugin("com.google.devtools.ksp")
                plugin("org.jetbrains.kotlin.plugin.serialization")

            }
            extensions.configure<LibraryExtension> {
                defaultConfig.targetSdk = libs.targetSdk
                configureKotlinAndroid(this)
                configureBuildTypes(
                    commonExtension = this,
                    gradleExtensionType = GradleExtensionType.Library,
                )
            }

            dependencies {
                add("implementation", libs.javaXInject.get())
            }
        }
    }

}