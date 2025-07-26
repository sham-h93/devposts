import com.android.build.api.dsl.LibraryExtension
import com.hshamkhani.hotlinenews.androidxComposeMaterial3
import com.hshamkhani.hotlinenews.androidxComposeUi
import com.hshamkhani.hotlinenews.androidxComposeUiGraphics
import com.hshamkhani.hotlinenews.androidxComposeUiToolingPreview
import com.hshamkhani.hotlinenews.androidxCoreKtx
import com.hshamkhani.hotlinenews.androidxLifecycleRuntimeKtx
import com.hshamkhani.hotlinenews.androidxLifecycleViewModelCompose
import com.hshamkhani.hotlinenews.androidxUiTestManifest
import com.hshamkhani.hotlinenews.androidxUiTooling
import com.hshamkhani.hotlinenews.configureComposeAndroid
import com.hshamkhani.hotlinenews.hiltNavigationCompose
import com.hshamkhani.hotlinenews.libs
import com.plcoding.convention.GradleExtensionType
import com.plcoding.convention.configureBuildTypes
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply {
                plugin("hotlinenews.android.library")
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