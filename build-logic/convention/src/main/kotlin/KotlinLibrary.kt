import com.hshamkhani.devposts.coroutinesCore
import com.hshamkhani.devposts.hiltCompiler
import com.hshamkhani.devposts.hiltCore
import com.hshamkhani.devposts.javaXInject
import com.hshamkhani.devposts.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


class KotlinLibrary : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("kotlin")
                apply("com.google.devtools.ksp")
            }
            dependencies {
                add("implementation", libs.coroutinesCore.get())
                add("implementation", libs.hiltCore.get())
                add("ksp", libs.hiltCompiler.get())
                add("implementation", libs.javaXInject.get())
            }
        }
    }
}