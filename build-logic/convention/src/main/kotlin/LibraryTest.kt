import com.hshamkhani.devposts.coroutinesTest
import com.hshamkhani.devposts.jUnit
import com.hshamkhani.devposts.libs
import com.hshamkhani.devposts.mockk
import com.hshamkhani.devposts.truth
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