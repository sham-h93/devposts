import com.android.build.gradle.LibraryExtension
import com.hshamkhani.devposts.configureKotlinAndroid
import com.hshamkhani.devposts.javaXInject
import com.hshamkhani.devposts.libs
import com.hshamkhani.devposts.targetSdk
import com.hshamkhani.devposts.GradleExtensionType
import com.hshamkhani.devposts.configureBuildTypes
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