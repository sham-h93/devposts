import com.hshamkhani.hotlinenews.hiltAndroid
import com.hshamkhani.hotlinenews.hiltCompiler
import com.hshamkhani.hotlinenews.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class HiltConventionPlugin : Plugin<Project> {

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