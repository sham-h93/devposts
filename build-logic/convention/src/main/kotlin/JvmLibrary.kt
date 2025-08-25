import com.hshamkhani.devposts.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project

class JvmLibrary: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply {
                plugin("org.jetbrains.kotlin.jvm")
            }
            configureKotlinJvm()
        }
    }

}