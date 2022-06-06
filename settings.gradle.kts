rootProject.name = "solonovamax.gay"

pluginManagement {
    val KOTLIN_VERSION: String by settings
    
    resolutionStrategy {
        eachPlugin {
            when {
                requested.id.id.startsWith("org.jetbrains.kotlin") -> {
                    useVersion(KOTLIN_VERSION)
                }
            }
        }
    }
    
    plugins {
        kotlin("js").version(KOTLIN_VERSION)
        kotlin("jvm").version(KOTLIN_VERSION)
        kotlin("multiplatform").version(KOTLIN_VERSION)
        kotlin("plugin.serialization").version(KOTLIN_VERSION)
        // kotlin("plugin.noarg").version(KOTLIN_VERSION)
        // id("ca.cutterslade.analyze") version "1.6.0"
    }
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

include(":backend")
include(":backend:emoji")
// include(":shared")
// include(":frontend:icons")
include(":frontend")
