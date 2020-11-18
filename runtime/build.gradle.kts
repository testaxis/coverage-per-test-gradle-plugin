plugins {
    kotlin("jvm")

    `maven-publish`
    id("com.jfrog.bintray") version "1.8.5"
}

version = "0.0.1"
group = "io.testaxis"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
}

tasks.jar {
    manifest {
        attributes(
            mapOf(
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version
            )
        )
    }
}

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.getByName("main").allSource)
}

val artifactName = "${rootProject.name}-${project.name}"
val artifactGroup = project.group.toString()
val artifactVersion = project.version.toString()

val pomUrl = "https://github.com/testaxis/coverage-per-test-gradle-plugin"
val pomScmUrl = "https://github.com/testaxis/coverage-per-test-gradle-plugin"
val pomIssueUrl = "https://github.com/testaxis/coverage-per-test-gradle-plugin"
val description = "Runtime of the Coverage per Test Gradle plugin."

val githubRepo = "testaxis/coverage-per-test-gradle-plugin"
val githubReadme = "README.md"

val licenseName = "MIT"
val pomLicenseUrl = "https://opensource.org/licenses/mit-license.php"
val pomLicenseDist = "repo"

val developerId = "casperboone"
val developerName = "Casper Boone"

publishing {
    publications {
        create<MavenPublication>("coverage-per-test-runtime") {
            groupId = artifactGroup
            artifactId = artifactName
            version = artifactVersion
            from(components["java"])

            artifact(sourcesJar)
            pom.withXml {
                asNode().apply {
                    appendNode("description", description)
                    appendNode("name", rootProject.name)
                    appendNode("url", pomUrl)
                    appendNode("licenses").appendNode("license").apply {
                        appendNode("name", licenseName)
                        appendNode("url", pomLicenseUrl)
                        appendNode("distribution", pomLicenseDist)
                    }
                    appendNode("developers").appendNode("developer").apply {
                        appendNode("id", developerId)
                        appendNode("name", developerName)
                    }
                    appendNode("scm").apply {
                        appendNode("url", pomScmUrl)
                    }
                }
            }
        }
    }
}

bintray {
    user = project.findProperty("bintrayUser").toString()
    key = project.findProperty("bintrayKey").toString()
    publish = true
    override = true

    setPublications("coverage-per-test-runtime")

    pkg.apply {
        repo = "testaxis"
        name = "coverage-per-test-runtime"
        userOrg = developerId
        this.githubRepo = githubRepo
        vcsUrl = pomScmUrl
        setLicenses(licenseName)
        this.desc = description
        websiteUrl = pomUrl
        issueTrackerUrl = pomIssueUrl
        githubReleaseNotesFile = githubReadme

        version.apply {
            name = artifactVersion
            desc = desc
            vcsTag = artifactVersion
        }
    }
}
