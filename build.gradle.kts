@file:Suppress("PropertyName", "EXPERIMENTAL_API_USAGE", "UNUSED_VARIABLE")

import org.gradle.api.tasks.wrapper.Wrapper.DistributionType.BIN

plugins {
    kotlin("multiplatform") apply false
    kotlin("plugin.serialization") apply false
    idea
}

val KOTLIN_VERSION: String by project

tasks {
    wrapper {
        gradleVersion = "7.4.2"
        distributionType = BIN
        distributionSha256Sum = "29e49b10984e585d8118b7d0bc452f944e386458df27371b49b4ac1dec4b7fda"
    }
}

allprojects {
    version = "1.1.2"
    group = "gay.solonovamax"
    
    repositories {
        mavenCentral()
    }
    
    configurations.all {
        resolutionStrategy {
            eachDependency {
                when (requested.module.group) {
                    "org.jetbrains.kotlin" -> {
                        useVersion(KOTLIN_VERSION)
                    }
                }
            }
        }
    }
}

// val webpackTask = tasks.getByName<KotlinWebpack>(webpackTaskName) {
//     inputs.files("$projectDir/src/jsMain/resources")
// }
//
// val webpackDistTask by tasks.named("jsBrowserDistribution") {
// }
//
// val copyJsDist by tasks.creating(Sync::class) {
//     from(webpackDistTask) {
//         into("$buildDir/public")
//     }
// }
//
// val jvmProcessResources = tasks.getByName<ProcessResources>("jvmProcessResources") {
//     dependsOn(copyJsDist)
// }
//
// // Alias "installDist" as "stage" (for cloud providers)
// tasks.create("stage") {
//     dependsOn(tasks.getByName("installDist"))
// }
//
// val isProduction: Boolean
//     get() = project.hasProperty("isProduction")
//
// val webpackTaskName: String
//     get() = if (isProduction) "jsBrowserProductionWebpack" else "jsBrowserDevelopmentWebpack"
