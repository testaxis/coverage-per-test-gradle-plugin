package io.testaxis.coveragepertest

import org.gradle.api.DefaultTask
import org.gradle.api.PolymorphicDomainObjectContainer
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.internal.provider.DefaultProviderFactory
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.TaskAction
import org.gradle.language.base.plugins.LifecycleBasePlugin
import org.gradle.testing.jacoco.tasks.JacocoReport
import java.io.File

inline fun <reified U : Any> PolymorphicDomainObjectContainer<in U>.create(
    name: String,
    noinline configuration: U.() -> Unit
) = this.create(name, U::class.java, configuration)

abstract class CoveragePerTestTask : DefaultTask() {

    @get:InputDirectory
    abstract val inputDirectory: DirectoryProperty

    @get:OutputDirectory
    abstract val outputDirectory: DirectoryProperty

    private lateinit var currentExecFile: File

    private val jacocoDbTestReport: JacocoReport

    init {
        description = "Generate coverage reports per test."
        group = LifecycleBasePlugin.VERIFICATION_GROUP

        jacocoDbTestReport = project.tasks.create<JacocoReport>("jacocoDbTestReport") {
            reports {
                it.xml.isEnabled = true
                it.xml.setDestination(
                    DefaultProviderFactory().provider {
                        File(outputDirectory.asFile.get(), "${currentExecFile.nameWithoutExtension}.xml")
                    }
                )
            }

            executionData({ currentExecFile })

            group = LifecycleBasePlugin.VERIFICATION_GROUP

            sourceSets(project.extensions.getByType(SourceSetContainer::class.java).getByName("main"))
        }
    }

    @TaskAction
    fun generateReports() {
        outputDirectory.asFile.get().mkdirs()

        println("tesst")

        inputDirectory.asFile.get()
            .listFiles()
            ?.filter { it.isFile }
            ?.filter { println(it.extension); it.extension == "exec" }
            .let { files ->
                if (files.isNullOrEmpty()) {
                    logger.error("No Jacoco .exec files were found in ${inputDirectory.asFile.get().path}.")
                    return
                }

                files.forEach { file ->
                    logger.info("Generating report for $file.")
                    this.currentExecFile = file
                    jacocoDbTestReport.generate()
                }
            }
    }
}
