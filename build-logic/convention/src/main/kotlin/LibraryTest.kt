import com.hshamkhani.derief.jUnit
import com.hshamkhani.derief.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class LibraryTest : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add("testImplementation", libs.jUnit.get())
            }
        }
    }
}