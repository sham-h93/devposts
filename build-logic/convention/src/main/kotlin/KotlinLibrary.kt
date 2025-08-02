import com.hshamkhani.derief.coroutinesCore
import com.hshamkhani.derief.hiltCompiler
import com.hshamkhani.derief.hiltCore
import com.hshamkhani.derief.javaXInject
import com.hshamkhani.derief.libs
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