pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        jcenter()
    }
}

rootProject.name = ("coverage-per-test")

include(":example")
includeBuild("plugin-build")
