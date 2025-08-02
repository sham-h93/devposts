import com.hshamkhani.derief.hiltAndroid
import com.hshamkhani.derief.hiltCompiler
import com.hshamkhani.derief.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class Hilt : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("dagger.hilt.android.plugin")

            dependencies.apply {
                add("implementation", libs.hiltAndroid.get())
                add("ksp", libs.hiltCompiler.get())
            }
        }
    }

}