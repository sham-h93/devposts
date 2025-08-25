import com.android.build.api.dsl.ApplicationExtension
import com.hshamkhani.devposts.androidxActivityCompose
import com.hshamkhani.devposts.androidxComposeMaterial3
import com.hshamkhani.devposts.androidxComposeUi
import com.hshamkhani.devposts.androidxComposeUiGraphics
import com.hshamkhani.devposts.androidxComposeUiToolingPreview
import com.hshamkhani.devposts.androidxCoreKtx
import com.hshamkhani.devposts.androidxEspressoCore
import com.hshamkhani.devposts.androidxJunit
import com.hshamkhani.devposts.androidxLifecycleRuntimeKtx
import com.hshamkhani.devposts.androidxLifecycleViewModelCompose
import com.hshamkhani.devposts.androidxUiTestManifest
import com.hshamkhani.devposts.androidxUiTooling
import com.hshamkhani.devposts.androidxUitestJunit
import com.hshamkhani.devposts.configureComposeAndroid
import com.hshamkhani.devposts.jUnit
import com.hshamkhani.devposts.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationCompose : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply {
                plugin("devposts.android.application")
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