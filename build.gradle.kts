plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.1.0"
    id("org.jetbrains.intellij.platform") version "2.7.1"
}

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

// IntelliJ Platform dependencies (v2 DSL)
dependencies {
    intellijPlatform {
        // Build against IntelliJ IDEA Community 2025.2.1.
        // If you build for Ultimate instead, use: create("IU", "2025.2.1")
        create("IC", "2025.2.1")

        // Platform test framework (optional)
        testFramework(org.jetbrains.intellij.platform.gradle.TestFrameworkType.Platform)

        // Example: bundled plugins (uncomment if needed)
        // bundledPlugin("com.intellij.java")
    }
}

intellijPlatform {
    // Keep this in sync with plugin.xml
    pluginConfiguration {
        ideaVersion {
            // Target only 2025.2.x builds
            sinceBuild = "252.0"
            untilBuild = "252.*"
        }
        changeNotes = "Version ${project.version}"
    }

}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "21"
        targetCompatibility = "21"
    }

    // Disable the task that requires WSL as it's not installed
    withType<org.jetbrains.intellij.platform.gradle.tasks.BuildSearchableOptionsTask> {
        enabled = false
    }
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
    }
}
