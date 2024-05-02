import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "io.github.dmitriy1892.kmp.libs"

dependencies {
    implementation(libs.gradleplugin.android)
    implementation(libs.gradleplugin.compose)
    implementation(libs.gradleplugin.kotlin)
    implementation(libs.gradleplugins.google.ksp)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

private val projectJavaVersion: JavaVersion = JavaVersion.toVersion(libs.versions.java.get())

java {
    sourceCompatibility = projectJavaVersion
    targetCompatibility = projectJavaVersion
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = projectJavaVersion.toString()
}

gradlePlugin {
    plugins {
        register("kmp.koin") {
            id = "kmp.koin"
            implementationClass = "io.github.dmitriy1892.kmp.libs.plugins.KoinPlugin"
        }

        register("kmp.library") {
            id = "kmp.library"
            implementationClass = "io.github.dmitriy1892.kmp.libs.plugins.KmpLibraryPlugin"
        }

        register("kmp.compose.library") {
            id = "kmp.compose.library"
            implementationClass = "io.github.dmitriy1892.kmp.libs.plugins.KmpComposeLibraryPlugin"
        }
    }
}