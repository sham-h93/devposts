import com.hshamkhani.hotlinenews.coroutinesCore
import com.hshamkhani.hotlinenews.hiltCompiler
import com.hshamkhani.hotlinenews.hiltCore
import com.hshamkhani.hotlinenews.javaXInject
import com.hshamkhani.hotlinenews.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


class KotlinLibraryConventionPlugin : Plugin<Project> {
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