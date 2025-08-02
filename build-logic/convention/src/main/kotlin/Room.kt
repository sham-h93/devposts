import androidx.room.gradle.RoomExtension
import com.google.devtools.ksp.gradle.KspExtension
import com.hshamkhani.derief.libs
import com.hshamkhani.derief.roomCompiler
import com.hshamkhani.derief.roomKtx
import com.hshamkhani.derief.roomPaging
import com.hshamkhani.derief.roomRuntime
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class Room : Plugin<Project> {

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
                add("implementation", libs.roomPaging.get())
                add("ksp", libs.roomCompiler.get())
            }
        }
    }

}