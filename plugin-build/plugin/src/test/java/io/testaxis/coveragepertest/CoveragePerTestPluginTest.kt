package io.testaxis.coveragepertest

import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

class CoveragePerTestPluginTest {

    @Test
    fun `plugin is applied correctly to the project`() {
        val project = ProjectBuilder.builder().build()
        project.pluginManager.apply("io.testaxis.coveragepertest")

        assert(project.tasks.getByName("coveragePerTest") is CoveragePerTestTask)
    }
}
