import com.hshamkhani.derief.hiltCompiler
import com.hshamkhani.derief.hiltAndroidTest
import com.hshamkhani.derief.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltTest : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add("testImplementation", libs.hiltAndroidTest.get())
                add("androidTestImplementation", libs.hiltAndroidTest.get())
                add("kspTest", libs.hiltCompiler.get())
                add("kspAndroidTest", libs.hiltCompiler.get())
            }
        }
    }

}