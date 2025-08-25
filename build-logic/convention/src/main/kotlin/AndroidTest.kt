import com.android.build.gradle.LibraryExtension
import com.hshamkhani.devposts.androidxEspressoCore
import com.hshamkhani.devposts.androidxJunit
import com.hshamkhani.devposts.androidxUitestJunit
import com.hshamkhani.devposts.coroutinesTest
import com.hshamkhani.devposts.jUnit
import com.hshamkhani.devposts.libs
import com.hshamkhani.devposts.mockAndroid
import com.hshamkhani.devposts.mockkAgent
import com.hshamkhani.devposts.truth
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
                add("androidTestImplementation", libs.jUnit.get())
                add("androidTestImplementation", libs.androidxJunit.get())
                add("androidTestImplementation", libs.androidxUitestJunit.get())
                add("androidTestImplementation", libs.androidxEspressoCore.get())
                add("androidTestImplementation", libs.mockkAgent.get())
                add("androidTestImplementation", libs.mockAndroid.get())
                add("androidTestImplementation", libs.truth.get())
                add("androidTestImplementation", libs.coroutinesTest.get())
            }
        }
    }
}