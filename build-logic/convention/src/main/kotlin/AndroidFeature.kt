import com.android.build.api.dsl.LibraryExtension
import com.hshamkhani.derief.androidxComposeMaterial3
import com.hshamkhani.derief.androidxComposeUi
import com.hshamkhani.derief.androidxComposeUiGraphics
import com.hshamkhani.derief.androidxComposeUiToolingPreview
import com.hshamkhani.derief.androidxCoreKtx
import com.hshamkhani.derief.androidxLifecycleRuntimeKtx
import com.hshamkhani.derief.androidxLifecycleViewModelCompose
import com.hshamkhani.derief.androidxUiTestManifest
import com.hshamkhani.derief.androidxUiTooling
import com.hshamkhani.derief.configureComposeAndroid
import com.hshamkhani.derief.hiltNavigationCompose
import com.hshamkhani.derief.libs
import com.hshamkhani.derief.GradleExtensionType
import com.hshamkhani.derief.configureBuildTypes
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeature : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply {
                plugin("derief.android.library")
                plugin("org.jetbrains.kotlin.plugin.compose")
            }
            extensions.configure<LibraryExtension> {
                configureComposeAndroid(this)
                configureBuildTypes(
                    commonExtension = this,
                    gradleExtensionType = GradleExtensionType.Library
                )
            }

            dependencies {
                add("implementation", libs.androidxCoreKtx.get())
                add("implementation", libs.androidxLifecycleRuntimeKtx.get())
                add("implementation", libs.androidxLifecycleViewModelCompose.get())
                add("implementation", libs.androidxComposeUi.get())
                add("implementation", libs.androidxComposeUiGraphics.get())
                add("implementation", libs.androidxComposeUiToolingPreview.get())
                add("implementation", libs.androidxComposeMaterial3.get())
                add("debugImplementation", libs.androidxUiTooling.get())
                add("debugImplementation", libs.androidxUiTestManifest.get())
                add("implementation", libs.hiltNavigationCompose.get())
            }
        }
    }

}