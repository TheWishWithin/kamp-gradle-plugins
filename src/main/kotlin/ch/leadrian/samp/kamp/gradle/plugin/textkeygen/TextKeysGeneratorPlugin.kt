package ch.leadrian.samp.kamp.gradle.plugin.textkeygen

import org.gradle.api.NonNullApi
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginConvention
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.compile.JavaCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@NonNullApi
open class TextKeysGeneratorPlugin : Plugin<Project> {

    companion object {

        const val GENERATED_SOURCE_DIRECTORY = "generated-src/main/java"

    }

    override fun apply(project: Project) {
        createExtension(project)
        configureTask(project)
        configureSourceSets(project)
    }

    private fun createExtension(project: Project) {
        project.extensions.create("textKeyGenerator", TextKeysGeneratorPluginExtension::class.java)
    }

    private fun configureTask(project: Project) {
        val generateTextKeysTask = project.tasks.create("generateTextKeys", GenerateTextKeysTask::class.java)
        project.tasks.withType(JavaCompile::class.java) { it.dependsOn(generateTextKeysTask) }
        project.tasks.withType(KotlinCompile::class.java) { it.dependsOn(generateTextKeysTask) }
    }

    private fun configureSourceSets(project: Project) {
        project
                .convention
                .findPlugin(JavaPluginConvention::class.java)
                ?.sourceSets
                ?.getByName(SourceSet.MAIN_SOURCE_SET_NAME)
                ?.java
                ?.srcDir(project.buildDir.resolve(GENERATED_SOURCE_DIRECTORY))
    }
}
