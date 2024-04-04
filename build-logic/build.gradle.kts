import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.github.dmitriy1892.kmp.libs"

dependencies {
    implementation(libs.gradleplugin.android)
    implementation(libs.gradleplugin.compose)
    implementation(libs.gradleplugin.kotlin)
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