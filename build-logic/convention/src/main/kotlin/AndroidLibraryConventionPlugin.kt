import com.android.build.gradle.LibraryExtension
import com.hshamkhani.hotlinenews.configureKotlinAndroid
import com.hshamkhani.hotlinenews.javaXInject
import com.hshamkhani.hotlinenews.libs
import com.hshamkhani.hotlinenews.targetSdk
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryConventionPlugin: Plugin<Project> {

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
            }

            dependencies {
                add("implementation", libs.javaXInject.get())
            }
        }
    }

}