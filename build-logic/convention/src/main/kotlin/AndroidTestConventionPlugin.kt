import com.android.build.gradle.LibraryExtension
import com.hshamkhani.hotlinenews.androidxEspressoCore
import com.hshamkhani.hotlinenews.androidxJunit
import com.hshamkhani.hotlinenews.androidxUitestJunit
import com.hshamkhani.hotlinenews.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                testOptions.unitTests {
                    isIncludeAndroidResources = true
                    isReturnDefaultValues = true
                }
            }
            dependencies {
                add("androidTestImplementation", libs.androidxUitestJunit.get())
                add("androidTestImplementation", libs.androidxJunit.get())
                add("androidTestImplementation", libs.androidxEspressoCore.get())
            }
        }
    }
}