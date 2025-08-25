import androidx.room.gradle.RoomExtension
import com.google.devtools.ksp.gradle.KspExtension
import com.hshamkhani.devposts.libs
import com.hshamkhani.devposts.roomCompiler
import com.hshamkhani.devposts.roomKtx
import com.hshamkhani.devposts.roomPaging
import com.hshamkhani.devposts.roomRuntime
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