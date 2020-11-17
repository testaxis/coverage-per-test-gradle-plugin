package io.testaxis.coveragepertest

import org.gradle.api.Plugin
import org.gradle.api.Project

const val EXTENSION_NAME = "coveragePerTestConfig"
const val TASK_NAME = "coveragePerTest"

abstract class CoveragePerTestPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        // Add the 'template' extension object
        val extension = project.extensions.create(EXTENSION_NAME, CoveragePerTestExtension::class.java, project)

        // Add a task that uses configuration from the extension object
        project.tasks.register(TASK_NAME, CoveragePerTestTask::class.java) {
            it.inputDirectory.set(extension.inputDirectory)
            it.outputDirectory.set(extension.outputDirectory)
        }
    }
}
