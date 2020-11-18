package io.testaxis.coveragepertest

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ApplicationPlugin

const val EXTENSION_NAME = "coveragePerTestConfig"
const val TASK_NAME = "coveragePerTest"
const val RUNTIME_VERSION = "0.0.1"

abstract class CoveragePerTestPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply(ApplicationPlugin::class.java)

        val extension = project.extensions.create(EXTENSION_NAME, CoveragePerTestExtension::class.java, project)

        project.tasks.register(TASK_NAME, CoveragePerTestTask::class.java) {
            it.inputDirectory.set(extension.inputDirectory)
            it.outputDirectory.set(extension.outputDirectory)
        }

        addRuntimeDependency(project)
    }

    private fun addRuntimeDependency(project: Project) {
        project.repositories.maven {
            it.setUrl("https://dl.bintray.com/casperboone/testaxis")
        }

        project.configurations.getAt("testRuntimeOnly").dependencies.add( // possibly only runtime?
            project.dependencies.create("io.testaxis:coverage-per-test-runtime:$RUNTIME_VERSION")
        )
    }
}
