import com.android.build.api.dsl.ApplicationExtension
import com.hshamkhani.derief.androidxActivityCompose
import com.hshamkhani.derief.androidxComposeMaterial3
import com.hshamkhani.derief.androidxComposeUi
import com.hshamkhani.derief.androidxComposeUiGraphics
import com.hshamkhani.derief.androidxComposeUiToolingPreview
import com.hshamkhani.derief.androidxCoreKtx
import com.hshamkhani.derief.androidxEspressoCore
import com.hshamkhani.derief.androidxJunit
import com.hshamkhani.derief.androidxLifecycleRuntimeKtx
import com.hshamkhani.derief.androidxLifecycleViewModelCompose
import com.hshamkhani.derief.androidxUiTestManifest
import com.hshamkhani.derief.androidxUiTooling
import com.hshamkhani.derief.androidxUitestJunit
import com.hshamkhani.derief.configureComposeAndroid
import com.hshamkhani.derief.jUnit
import com.hshamkhani.derief.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationCompose : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply {
                plugin("derief.android.application")
                plugin("org.jetbrains.kotlin.plugin.compose")
                plugin("com.google.devtools.ksp")
            }

            extensions.configure<ApplicationExtension> {
                configureComposeAndroid(this)
            }

            dependencies {
                add("implementation", libs.androidxCoreKtx.get())
                add("implementation", libs.androidxLifecycleRuntimeKtx.get())
                add("implementation", libs.androidxLifecycleViewModelCompose.get())
                add("implementation", libs.androidxActivityCompose.get())
                add("implementation", libs.androidxComposeUi.get())
                add("implementation", libs.androidxComposeUiGraphics.get())
                add("implementation", libs.androidxComposeUiToolingPreview.get())
                add("implementation", libs.androidxComposeMaterial3.get())
                add("debugImplementation", libs.androidxUiTooling.get())
                add("debugImplementation", libs.androidxUiTestManifest.get())
                add("androidTestImplementation", libs.androidxUitestJunit.get())
                add("androidTestImplementation", libs.androidxJunit.get())
                add("androidTestImplementation", libs.androidxEspressoCore.get())
                add("testImplementation", libs.jUnit.get())
            }
        }
    }
}