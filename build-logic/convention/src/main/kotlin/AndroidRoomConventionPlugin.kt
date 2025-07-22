import androidx.room.gradle.RoomExtension
import com.google.devtools.ksp.gradle.KspExtension
import com.hshamkhani.hotlinenews.libs
import com.hshamkhani.hotlinenews.roomCompiler
import com.hshamkhani.hotlinenews.roomKtx
import com.hshamkhani.hotlinenews.roomRuntime
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidRoomConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
                apply("androidx.room")
            }

            extensions.configure<KspExtension> {
                arg("room.generateKotlin", "true")
            }


            extensions.configure<RoomExtension> {
                schemaDirectory("$projectDir/schemas")
            }

            dependencies {
                add("implementation", libs.roomRuntime.get())
                add("implementation", libs.roomKtx.get())
                add("ksp", libs.roomCompiler.get())
            }
        }
    }

}