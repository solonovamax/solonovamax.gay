import com.github.gradle.node.yarn.task.YarnTask

plugins {
    // kotlin("js")
    // kotlin("plugin.serialization")
    base
    distribution
    id("com.github.node-gradle.node") version "3.2.1"
}

node {
    version.set("16.14.0")
    download.set(true)
}

val parcelDevBuild by tasks.creating(YarnTask::class) {
    dependsOn(tasks.yarn)
    
    args.set(listOf("run", "buildDev"))
    inputs.dir("src")
    inputs.files(
            "package.json", "package-lock.json", "sharp.config.json", "tsconfig.json", ".htmlnanorc", ".parcelrc", ".posthtmlrc",
            ".browserslistrc",
                )
    outputs.dir("${buildDir}/dev")
}

val parcelProdBuild by tasks.creating(YarnTask::class) {
    dependsOn(tasks.yarn)
    
    args.set(listOf("run", "build"))
    inputs.dir("src")
    inputs.files(
            "package.json", "yarn.lock", "sharp.config.json", "tsconfig.json", ".htmlnanorc", ".parcelrc", ".posthtmlrc",
            ".browserslistrc",
                )
    outputs.dir("${buildDir}/prod")
}

val browserDist: Configuration by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = false
}

val browserDevDist: Configuration by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = false
}

artifacts {
    add(browserDist.name, parcelProdBuild.outputs.files.singleFile) {
        builtBy(parcelProdBuild)
    }
    add(browserDevDist.name, parcelDevBuild.outputs.files.singleFile) {
        builtBy(parcelDevBuild)
    }
}
//
// val isProduction: Boolean
//     get() = project.hasProperty("isProduction")
//
// val webpackTask = tasks.getByName<KotlinWebpack>(webpackTaskName) {
//     inputs.files("$projectDir/src/main/resources")
// }
//
// val webpackTaskName: String
//     get() = if (isProduction) "browserProductionWebpack" else "browserDevelopmentWebpack"
