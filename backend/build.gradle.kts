@file:Suppress("SuspiciousCollectionReassignment")

plugins {
    application
    distribution
    kotlin("jvm")
    kotlin("plugin.serialization")
}

val KTOR_VERSION: String by project
val LOGBACK_VERSION: String by project

val website by configurations.creating {
    isCanBeConsumed = false
    isCanBeResolved = true
}
val websiteDev by configurations.creating {
    isCanBeConsumed = false
    isCanBeResolved = true
}

kotlin {
    target {
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_11.toString()
                languageVersion = "1.7"
                apiVersion = "1.7"
            }
        }
    }
}

val runDir = "$buildDir/run"

dependencies {
    // implementation(project(":shared"))
    
    implementation("io.ktor:ktor-server-core:$KTOR_VERSION")
    implementation("io.ktor:ktor-server-netty:$KTOR_VERSION")
    
    implementation("io.ktor:ktor-server-locations:$KTOR_VERSION")
    
    implementation("io.ktor:ktor-server-webjars:$KTOR_VERSION")
    
    implementation("io.ktor:ktor-server-html-builder:$KTOR_VERSION")
    
    // implementation("io.ktor:ktor-serialization:$KTOR_VERSION")
    // implementation("io.ktor:ktor-serialization-jackson:$KTOR_VERSION")
    // implementation("io.ktor:ktor-serialization-kotlinx:$KTOR_VERSION")
    // implementation("io.ktor:ktor-serialization-kotlinx-json:$KTOR_VERSION")
    // implementation("io.ktor:ktor-serialization-kotlinx-cbor:$KTOR_VERSION")
    // implementation("io.ktor:ktor-serialization-kotlinx-xml:$KTOR_VERSION")
    
    implementation("io.ktor:ktor-server-conditional-headers:$KTOR_VERSION")
    implementation("io.ktor:ktor-server-default-headers:$KTOR_VERSION")
    implementation("io.ktor:ktor-server-forwarded-header:$KTOR_VERSION")
    implementation("io.ktor:ktor-server-caching-headers:$KTOR_VERSION")
    implementation("io.ktor:ktor-server-cors:$KTOR_VERSION")
    
    implementation("io.ktor:ktor-server-call-logging:$KTOR_VERSION")
    implementation("io.ktor:ktor-server-compression:$KTOR_VERSION")
    implementation("io.ktor:ktor-server-partial-content:$KTOR_VERSION")
    implementation("io.ktor:ktor-server-auto-head-response:$KTOR_VERSION")
    implementation("io.ktor:ktor-server-content-negotiation:$KTOR_VERSION")
    implementation("io.ktor:ktor-server-status-pages:$KTOR_VERSION")
    
    implementation("io.ktor:ktor-server-thymeleaf:$KTOR_VERSION")
    implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect:3.1.0")
    
    implementation("com.vladsch.flexmark:flexmark-all:0.64.0")
    implementation("com.vladsch.flexmark:flexmark-ext-anchorlink:0.64.0")
    
    implementation("ca.solo-studios:slf4k:0.4.6")
    implementation("ch.qos.logback:logback-classic:$LOGBACK_VERSION")
    
    implementation("com.charleskorn.kaml:kaml:0.44.0")
    
    implementation("org.kohsuke:github-api:1.306")
    
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")
    
    implementation("com.github.hazendaz:htmlcompressor:1.7.3")
    
    implementation(project(":backend:emoji"))
    
    websiteDev(project(path = ":frontend", configuration = "browserDevDist"))
    
    website(project(path = ":frontend", configuration = "browserDist"))
    // testImplementation("io.ktor:ktor-server-tests:$KTOR_VERSION")
    // testImplementation("org.jetbrains.kotlin:kotlin-test:$KOTLIN_VERSION")
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
    // applicationDefaultJvmArgs = listOf("-Dio.ktor.development=true")
}

val prepRun by tasks.creating(Sync::class) {
    dependsOn(tasks.classes)
    from(websiteDev) {
        into("public")
    }
    
    from("$projectDir/config.yaml")
    from("files") {
        into("files/markdown/")
    }
    
    into(runDir)
}

tasks.run.configure {
    args("-P:ktor.development=true", "-P:ktor.environment=dev")
    dependsOn(prepRun)
    workingDir = file(runDir)
}

tasks.build.configure {
    dependsOn(prepRun)
}

distributions {
    main {
        contents {
            from(website) {
                into("public")
            }
    
            from("$projectDir/config.yaml")
            from("files") {
                into("files/markdown/")
            }
        }
    }
}

tasks.jar {
    println(this.source.asPath)
    from(sourceSets.main.get().output)
}

val isProduction: Boolean
    get() = project.hasProperty("isProduction")
