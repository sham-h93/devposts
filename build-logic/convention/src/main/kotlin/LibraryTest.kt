import com.hshamkhani.derief.coroutinesTest
import com.hshamkhani.derief.jUnit
import com.hshamkhani.derief.libs
import com.hshamkhani.derief.mockk
import com.hshamkhani.derief.truth
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class LibraryTest : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add("testImplementation", libs.jUnit.get())
                add("testImplementation", libs.mockk.get())
                add("testImplementation", libs.truth.get())
                add("testImplementation", libs.coroutinesTest.get())
            }
        }
    }
}