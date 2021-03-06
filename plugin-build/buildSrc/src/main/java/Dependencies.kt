object Versions {
    const val JUNIT = "5.7.0"
    const val KTLINT = "0.39.0"
    const val ASSERTJ = "3.18.0"
}

object BuildPluginsVersion {
    const val DETEKT = "1.14.2"
    const val KOTLIN = "1.3.72"
    const val KTLINT = "9.4.1"
    const val PLUGIN_PUBLISH = "0.12.0"
    const val VERSIONS_PLUGIN = "0.33.0"
}

object TestingLib {
    const val JUNIT_API = "org.junit.jupiter:junit-jupiter-api:${Versions.JUNIT}"
    const val JUNIT_ENGINE = "org.junit.jupiter:junit-jupiter-engine:${Versions.JUNIT}"
    const val ASSERTJ = "org.assertj:assertj-core:${Versions.ASSERTJ}"
}
