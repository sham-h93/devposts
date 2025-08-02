import com.android.build.gradle.LibraryExtension
import com.hshamkhani.derief.androidxEspressoCore
import com.hshamkhani.derief.androidxJunit
import com.hshamkhani.derief.androidxUitestJunit
import com.hshamkhani.derief.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidTest : Plugin<Project> {
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