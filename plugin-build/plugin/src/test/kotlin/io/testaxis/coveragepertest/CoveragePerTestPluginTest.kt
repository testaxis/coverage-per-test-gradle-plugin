package io.testaxis.coveragepertest

import org.assertj.core.api.Assertions.assertThat
import org.gradle.testkit.runner.GradleRunner
import org.junit.jupiter.api.Test
import java.io.File

class CoveragePerTestPluginTest {
    @Test
    fun `the plugin task can be executed to generate xml reports per-test`() {
        val exampleProjectDirectory = File("../../example/")

        GradleRunner.create()
            .withProjectDir(exampleProjectDirectory)
            .withArguments("clean", "test", "coveragePerTest")
            .withPluginClasspath()
            .build()

        val xmlReportsDirectory = File(exampleProjectDirectory, "build/coveragepertest/xml")

        assertThat(File(xmlReportsDirectory, "test-com.example.CalculatorTest##testAddsNumbers.xml")).exists()
        assertThat(File(xmlReportsDirectory, "test-com.example.CalculatorTest##testSubtractsNumbers.xml")).exists()
        assertThat(File(xmlReportsDirectory, "test-com.example.CounterTest##testDecreaseCounter.xml")).exists()
        assertThat(File(xmlReportsDirectory, "test-com.example.CounterTest##testIncreaseCounter.xml")).exists()
    }
}
