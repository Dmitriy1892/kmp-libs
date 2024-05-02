package io.github.dmitriy1892.kmp.libs.extensions

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.tasks.Exec
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.konan.properties.Properties
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private const val VERSION_MAJOR = "VERSION_MAJOR"
private const val VERSION_MINOR = "VERSION_MINOR"
private const val VERSION_PATCH = "VERSION_PATCH"

internal val Project.projectJavaVersion: JavaVersion
    get() = JavaVersion.toVersion(libs.versions.java.get().toInt())

internal val Project.libs: LibrariesForLibs
    get() = the<LibrariesForLibs>()

val Project.projectDirPath: String
    get() = this.projectDir.path

fun Project.getApplicationVersionCode(): Int = useVersionProperties { major, minor, patch ->
    major.toInt() * 10000 + minor.toInt() * 100 + patch.toInt()
}

fun Project.getApplicationVersionName(): String = useVersionProperties { major, minor, patch ->
    "$major.$minor.$patch"
}

fun Project.incrementVersionProperties(): Unit = useProperties(
    "version.properties"
) { props, _, writer ->
    val versionPatch = props.getProperty(VERSION_PATCH).toInt() + 1
    props.setProperty(VERSION_PATCH, versionPatch.toString())
    props.store(writer, null)
}

fun Project.getCommandLineProperty(key: String): String? {
    return try {
        project.property(key)?.toString()
    } catch (e: Throwable) {
        null
    }
}

fun Project.getSecretProperties(): Properties {
    return try {
        useProperties("secret.properties") { properties: Properties -> properties }
    } catch (e: Throwable) {
        Properties()
    }
}

fun Project.registerIncrementVersionCatalogPropertyTask(
    taskName: String,
    propertyKey: String,
    pathToIncrementScriptFile: String = "$projectDirPath/scripts"
) {
    tasks.register(taskName, Exec::class.java) {
        println("===> Path to \"increment_library_version.sh\": $pathToIncrementScriptFile")
        workingDir(pathToIncrementScriptFile)
        commandLine("sh", "increment_library_version.sh", propertyKey)
    }
}


private fun <T> Project.useVersionProperties(
    block: (versionMajor: String, versionMinor: String, versionPatch: String) -> T
): T = useProperties("version.properties") { props ->
    val versionMajor = props.getProperty(VERSION_MAJOR)
    val versionMinor = props.getProperty(VERSION_MINOR)
    val versionPatch = props.getProperty(VERSION_PATCH)

    block(versionMajor, versionMinor, versionPatch)
}

private fun <T> Project.useProperties(
    fileName: String,
    block: (Properties) -> T
): T {
    val propertiesFile = project.rootProject.file(fileName)
    return propertiesFile.reader().use {
        val props = Properties().apply { load(it) }
        block(props)
    }
}

private fun <T> Project.useProperties(
    fileName: String,
    block: (Properties, InputStreamReader, OutputStreamWriter) -> T
): T {
    val propertiesFile = project.rootProject.file(fileName)
    return propertiesFile.reader().use { reader ->
        val props = Properties().apply { load(reader) }

        propertiesFile.writer().use { writer ->
            block(props, reader, writer)
        }
    }
}