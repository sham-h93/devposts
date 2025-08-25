import com.android.build.api.dsl.LibraryExtension
import com.hshamkhani.devposts.androidxComposeMaterial3
import com.hshamkhani.devposts.androidxComposeUi
import com.hshamkhani.devposts.androidxComposeUiGraphics
import com.hshamkhani.devposts.androidxComposeUiToolingPreview
import com.hshamkhani.devposts.androidxCoreKtx
import com.hshamkhani.devposts.androidxLifecycleRuntimeKtx
import com.hshamkhani.devposts.androidxLifecycleViewModelCompose
import com.hshamkhani.devposts.androidxUiTestManifest
import com.hshamkhani.devposts.androidxUiTooling
import com.hshamkhani.devposts.configureComposeAndroid
import com.hshamkhani.devposts.hiltNavigationCompose
import com.hshamkhani.devposts.libs
import com.hshamkhani.devposts.GradleExtensionType
import com.hshamkhani.devposts.configureBuildTypes
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeature : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply {
                plugin("devposts.android.library")
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