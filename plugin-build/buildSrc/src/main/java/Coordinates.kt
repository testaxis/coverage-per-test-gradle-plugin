object PluginCoordinates {
    const val ID = "io.testaxis.coveragepertest"
    const val GROUP = "io.testaxis"
    const val VERSION = "1.0.0"
    const val IMPLEMENTATION_CLASS = "io.testaxis.coveragepertest.CoveragePerTestPlugin"
}

object PluginBundle {
    const val VCS = "https://github.com/testaxis/coverage-per-test-gradle-plugin"
    const val WEBSITE = "https://github.com/testaxis/coverage-per-test-gradle-plugin"
    const val DESCRIPTION = "A Gradle plugin that collects and reports coverage per test."
    const val DISPLAY_NAME = "TestAxis - Coverage per Test"
    val TAGS = listOf(
        "plugin",
        "gradle",
        "testaxis",
        "coverage",
        "tests"
    )
}

