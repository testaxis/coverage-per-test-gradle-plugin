<h1 align="center">
    Coverage per Test - Gradle Plugin
</h1>

<p align="center">
    <a href="https://github.com/testaxis/coverage-per-test-gradle-plugin/actions?query=workflow%3ABuild">
        <img src="https://img.shields.io/github/workflow/status/testaxis/coverage-per-test-gradle-plugin/Pre Merge Checks?style=for-the-badge" />
    </a>
    <a href="https://github.com/testaxis/coverage-per-test-gradle-plugin/actions?query=workflow%3ADeploy">
        <img src="https://img.shields.io/github/workflow/status/testaxis/coverage-per-test-gradle-plugin/Pre Merge Checks?label=Deploy&style=for-the-badge" />
    </a>
</p>

Collect coverage reports per test case execution instead of the entire test suite.

This is a gradle plugin that works with JUnit 5 and Jacoco.

## Getting Started

*   Add this plugin (and Jacoco) to the plugins section of your Gradle.
    
    _Kotlin:_
    ```kotlin
    plugins {
        jacoco
        id("io.testaxis.coveragepertest") version "1.0.0"
    }
    ```
    
    _Groovy:_
    ```groovy
    plugins {
        id 'jacoco'
        id "io.testaxis.coveragepertest" version "1.0.0"
    }
    ```
    
*   Set the tool version of Jacoco to the version below or higher.

    _Kotlin/Groovy:_
    ```kotlin
    jacoco {
        toolVersion = "0.8.6"
    }
    ```

*   Set JUnit's extension autodetection property to true to enable the runtime.

    _Kotlin:_
    ```kotlin
    tasks.withType<Test> {
        systemProperty("junit.jupiter.extensions.autodetection.enabled", "true")
        useJUnitPlatform()
    }
    ```
    
    _Note that the runtime interferes with regular coverage reports._
    _Enable the runtime dynamically if you want to generate a regular coverage report for your entire test suite._

*   Optionally, configure any of the options of the plugin below.
    You can leave the configuration block out if you do not want to set any custom options.
    
    _Kotlin:_
    ```kotlin
    coveragePerTestConfig {
        inputDirectory.set(file("/custom-reports-input-directory"))
        outputDirectory.set(file("/custom-reports-output-directory"))
    }
    ```
    
## Tasks

Run tests and generate coverage reports per test:

```bash
$ gradlew test coveragePerTest
```

By default, the reports will be located in `build/coveragepertest/xml`.

## Development

The plugin consists of two parts: the actual Gradle plugin (`plugin-build/`) and the runtime library (`runtime/`).

Run the tests and static analysis tools:
```
$ gradlew preMerge
```

## Security

If you discover any security related issues, please email mail@casperboone.nl instead of using the issue tracker.

## Credits

- [Casper Boone](https://github.com/casperboone)
- [All Contributors](../../contributors)

## License

The MIT License (MIT). Please see [License File](LICENSE.md) for more information.
