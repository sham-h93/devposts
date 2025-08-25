import com.hshamkhani.devposts.hiltAndroid
import com.hshamkhani.devposts.hiltCompiler
import com.hshamkhani.devposts.libs
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