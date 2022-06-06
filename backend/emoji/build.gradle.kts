@file:Suppress("SuspiciousCollectionReassignment")

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

group = "gay.solonovamax"
version = "1.0.0"

repositories {
    mavenCentral()
}

kotlin {
    target {
        compilations.all {
            kotlinOptions {
                freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
                languageSettings.optIn("kotlin.RequiresOptIn")
                jvmTarget = JavaVersion.VERSION_11.toString()
                languageVersion = "1.6"
                apiVersion = "1.6"
            }
        }
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("stdlib-jdk7"))
    implementation(kotlin("stdlib-jdk8"))
    // implementation(kotlin("stdlib-jvm"))
    
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
    
    implementation("com.vladsch.flexmark:flexmark:0.64.0")
    implementation("com.vladsch.flexmark:flexmark-util:0.64.0")
    implementation("com.vladsch.flexmark:flexmark-jira-converter:0.64.0")
    
    testImplementation("com.vladsch.flexmark:flexmark-test-util:0.64.0")
    testImplementation("com.vladsch.flexmark:flexmark-core-test:0.64.0")
}

tasks.withType<Test>() {
    useJUnit()
}
