package io.testaxis.coveragepertest

import org.gradle.api.Project
import org.gradle.api.file.DirectoryProperty
import javax.inject.Inject

const val DEFAULT_INPUT_DIRECTORY = "coveragepertest"
const val DEFAULT_OUTPUT_DIRECTORY = "coveragepertest/xml"

@Suppress("UnnecessaryAbstractClass")
abstract class CoveragePerTestExtension @Inject constructor(project: Project) {

    private val objects = project.objects

    val inputDirectory: DirectoryProperty = objects.directoryProperty().convention(
        project.layout.buildDirectory.dir(DEFAULT_INPUT_DIRECTORY)
    )

    val outputDirectory: DirectoryProperty = objects.directoryProperty().convention(
        project.layout.buildDirectory.dir(DEFAULT_OUTPUT_DIRECTORY)
    )
}
