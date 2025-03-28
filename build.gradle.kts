// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt) apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.5"
}


detekt {
    toolVersion = "1.23.5"
    config.from(
        project.rootDir.resolve("detekt.yml")
    )
    buildUponDefaultConfig = false
    allRules = false
    autoCorrect = true
    ignoreFailures = false

    tasks.register("printDetektConfig") {
        doLast {
            println("Using config: " + project.rootDir.resolve("detekt.yml").absolutePath)
            println("Exists: " + project.rootDir.resolve("detekt.yml").exists())
        }
    }

}
