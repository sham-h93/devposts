import com.hshamkhani.hotlinenews.hiltCompiler
import com.hshamkhani.hotlinenews.hiltTestingCompiler
import com.hshamkhani.hotlinenews.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltTestConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add("testImplementation", libs.hiltTestingCompiler.get())
                add("androidTestImplementation", libs.hiltTestingCompiler.get())
                add("kspTest", libs.hiltCompiler.get())
                add("kspAndroidTest", libs.hiltCompiler.get())
            }
        }
    }

}