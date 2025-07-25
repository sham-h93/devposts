import com.android.build.api.dsl.ApplicationExtension
import com.hshamkhani.hotlinenews.androidxActivityCompose
import com.hshamkhani.hotlinenews.androidxComposeMaterial3
import com.hshamkhani.hotlinenews.androidxComposeUi
import com.hshamkhani.hotlinenews.androidxComposeUiGraphics
import com.hshamkhani.hotlinenews.androidxComposeUiToolingPreview
import com.hshamkhani.hotlinenews.androidxCoreKtx
import com.hshamkhani.hotlinenews.androidxEspressoCore
import com.hshamkhani.hotlinenews.androidxJunit
import com.hshamkhani.hotlinenews.androidxLifecycleRuntimeKtx
import com.hshamkhani.hotlinenews.androidxLifecycleViewModelCompose
import com.hshamkhani.hotlinenews.androidxUiTestManifest
import com.hshamkhani.hotlinenews.androidxUiTooling
import com.hshamkhani.hotlinenews.androidxUitestJunit
import com.hshamkhani.hotlinenews.configureComposeAndroid
import com.hshamkhani.hotlinenews.jUnit
import com.hshamkhani.hotlinenews.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply {
                plugin("hotlinenews.android.application")
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