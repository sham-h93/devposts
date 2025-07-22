import com.hshamkhani.hotlinenews.hiltAndroid
import com.hshamkhani.hotlinenews.hiltCompiler
import com.hshamkhani.hotlinenews.hiltExtCompiler
import com.hshamkhani.hotlinenews.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("dagger.hilt.android.plugin")
                apply("com.google.devtools.ksp")
            }

            dependencies {
                add("implementation", libs.hiltAndroid.get())
                add("ksp", libs.hiltCompiler.get())
            }
        }
    }

}