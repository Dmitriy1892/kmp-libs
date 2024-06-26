package io.github.dmitriy1892.kmp.libs.extensions.artifact.ios.spm

import io.github.dmitriy1892.kmp.libs.extensions.kotlinMultiplatformExtension
import org.gradle.api.Project
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.bundling.Zip
import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework
import java.io.ByteArrayOutputStream

/**
 * Default SPM output path directory.
 * Looks like ***"<module-name>/build/spm"***
 */
val Project.defaultSpmOutputPath: String
    get() = layout.buildDirectory.get().toString() + "/spm"

/**
 * Default XCFramework path directory.
 * Looks like ***"<module-name>/build/XCFrameworks/release"***
 */
val Project.defaultXcFrameworkPath: String
    get() = layout.buildDirectory.get().toString() + "/XCFrameworks/release"

/**
 * Function for generating a Swift package manager framework.
 *
 * For SPM archive generating, run the following task:
 * ```
 * ./gradlew assembleSpmArchive
 * ```
 *
 * ***assembleSpmArchive*** task runs the chain of tasks under the hood:
 * - ***assembleSharedReleaseXCFramework*** - task for building a ***.xcframework*** folder with binary sources for iOS.
 * - ***generateSpmArchive*** - task for packing a ***.xcframework*** folder to ***.zip*** archive
 * - ***calculateSpmArchiveChecksum*** - task for ***.zip*** archive checksum string calculation.
 * It's optional task, executes if path to SPM archive passed as URL
 * - ***generateSpmPackageSwiftFile*** - task for generating a ***Package.swift*** file for the SPM
 *
 * @param archiveName [String] SPM archive name without ***".zip"*** extension.
 * @param bundleId [String] bundle id for SPM archive.
 * @param swiftToolsVersion [String] version of Swift tool that uses for SPM.
 * @param pathToXcFramework [String] path to generated by
 * ***assembleSharedReleaseXCFramework*** task output ***".framework"*** folder.
 * @param pathToSaveSpmArchive [String] path to saving place for SPM archive.
 * @param configuration block with [Framework] receiver. Configure your exports inside that lambda.
 */
fun Project.swiftPackageManagerFramework(
    archiveName: String,
    bundleId: String,
    swiftToolsVersion: String = "5.10",
    pathToXcFramework: String = defaultXcFrameworkPath,
    pathToSaveSpmArchive: SpmArchivePath = SpmArchivePath.Local(defaultSpmOutputPath),
    configuration: Framework.() -> Unit = {}
) {
    with(kotlinMultiplatformExtension) {
        val xcFramework = XCFramework(archiveName)

        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64()
        ).forEach {
            it.binaries.framework {
                baseName = archiveName

                binaryOption("bundleId", bundleId)
                xcFramework.add(this)
                isStatic = true

                configuration()
            }
        }
    }

    registerAssembleSpmArchiveTask(
        archiveName = archiveName,
        swiftToolsVersion = swiftToolsVersion,
        pathToXcFramework = pathToXcFramework,
        pathToSaveSpmArchive = pathToSaveSpmArchive
    )
}

/**
 * Field for storing a generated SPM archive checksum.
 * Used by:
 * - ***calculateSpmArchiveChecksum*** task (that task provides the checksum as result of it's work)
 * - ***generateSpmPackageSwiftFile*** task (that task receives the checksum and use it for ***"Package.swift"*** file generating).
 */
private var spmArchiveChecksum = ""

/**
 * Register task for assembling the SPM ***".zip"*** archive file and ***"Package.swift"*** file.
 *
 * Run the following command from the command line for generating a
 * SPM ***".zip"*** archive file and ***"Package.swift"*** file:
 * ```
 * ./gradlew assembleSpmArchive
 * ```
 *
 * @param archiveName [String] SPM archive name without ***".zip"*** extension.
 * @param swiftToolsVersion [String] version of Swift tool that uses for SPM.
 * @param pathToXcFramework [String] path to generated by
 * ***assembleSharedReleaseXCFramework*** task output ***".framework"*** folder.
 * @param pathToSaveSpmArchive [String] path to saving place for SPM archive.
 */
private fun Project.registerAssembleSpmArchiveTask(
    archiveName: String,
    swiftToolsVersion: String,
    pathToXcFramework: String,
    pathToSaveSpmArchive: SpmArchivePath
) {
    registerGenerateSpmArchiveTask(archiveName, pathToXcFramework, pathToSaveSpmArchive.localPath)

    registerCalculateSpmArchiveChecksumTask(
        archiveName = archiveName,
        localPathToSaveSpmArchive = pathToSaveSpmArchive.localPath
    )

    registerGenerateSpmPackageSwiftFileTask(archiveName, swiftToolsVersion, pathToSaveSpmArchive)

    tasks.register("assembleSpmArchive") {
        dependsOn("generateSpmPackageSwiftFile")
    }
}

/**
 * Register task for SPM ***"Package.swift"*** file generating.
 *
 * **NOTE:** Task ***"generateSpmArchive"*** must be already registered on [Project].
 *
 * Run the following command from the command line for generating a ***"Package.swift"*** file:
 * ```
 * ./gradlew generateSpmPackageSwiftFile
 * ```
 *
 * @param archiveName [String] SPM archive name without ***".zip"*** extension.
 * @param swiftToolsVersion [String] version of Swift tool that uses for SPM.
 * @param pathToSpmArchive [String] path to already generated SPM archive.
 */
private fun Project.registerGenerateSpmPackageSwiftFileTask(
    archiveName: String,
    swiftToolsVersion: String,
    pathToSpmArchive: SpmArchivePath
) {
    tasks.register("generateSpmPackageSwiftFile") {
        val parentTask = when (pathToSpmArchive) {
            is SpmArchivePath.Local -> "generateSpmArchive"
            is SpmArchivePath.Remote -> "calculateSpmArchiveChecksum"
        }
        dependsOn(parentTask)

        val file = file("${pathToSpmArchive.localPath}/Package.swift")

        doLast {
            val binaryTarget = when (pathToSpmArchive) {
                is SpmArchivePath.Local -> {
                    "      .binaryTarget(\n" +
                            "         name: \"$archiveName\",\n" +
                            "         path: \"$archiveName.zip\")\n"
                }
                is SpmArchivePath.Remote -> {
                    check(pathToSpmArchive.remoteUrl.startsWith("https", true)) {
                        "Remote URL for SPM must be started from \"https\" keyword"
                    }

                    "      .binaryTarget(\n" +
                            "         name: \"$archiveName\",\n" +
                            "         url: \"${pathToSpmArchive.remoteUrl}\",\n" +
                            "         checksum: \"$spmArchiveChecksum\")\n"
                }

            }

            spmArchiveChecksum = ""

            file.bufferedWriter().use { writer ->
                writer.write("// swift-tools-version: $swiftToolsVersion")
                writer.newLine()
                writer.write("import PackageDescription")
                writer.newLine()
                writer.newLine()
                writer.write(
                    "let package = Package(\n" +
                            "   name: \"$archiveName\",\n" +
                            "   platforms: [\n" +
                            "     .iOS(.v14),\n" +
                            "   ],\n" +
                            "   products: [\n" +
                            "      .library(name: \"$archiveName\", targets: [\"$archiveName\"])\n" +
                            "   ],\n" +
                            "   targets: [\n" +
                            binaryTarget +
                            "   ]\n" +
                            ")"
                )
            }
        }
    }
}

/**
 * Register task for SPM ***.zip*** archive checksum calculation.
 * Result of checksum calculation writes to the [spmArchiveChecksum] variable.
 *
 * @param archiveName SPM archive name without ***".zip"*** extension.
 * @param localPathToSaveSpmArchive [String] local path to saving place for SPM archive.
 */
private fun Project.registerCalculateSpmArchiveChecksumTask(
    archiveName: String,
    localPathToSaveSpmArchive: String
) {
    tasks.register("calculateSpmArchiveChecksum", Exec::class.java) {
        dependsOn("generateSpmArchive")

        setWorkingDir(localPathToSaveSpmArchive)

        val checksumFile = file("$localPathToSaveSpmArchive/checksum.sh")

        doFirst {
            standardOutput = ByteArrayOutputStream()

            checksumFile.bufferedWriter().use {
                it.write("swift package compute-checksum $archiveName.zip")
            }
        }

        commandLine("sh", "checksum.sh")

        doLast {
            checksumFile.delete()
            println("===> SPM Archive checksum: $standardOutput")
            spmArchiveChecksum = standardOutput.toString().replace("\n", "")
        }
    }
}

/**
 * Register task for SPM archive generating.
 *
 * Run the following command from the command line for generating a ***".zip"*** SPM archive file:
 * ```
 * ./gradlew generateSpmArchive
 * ```
 *
 * @param archiveName [String] SPM archive name without ***".zip"*** extension.
 * @param pathToXcFramework [String] path to generated by
 * ***assembleSharedReleaseXCFramework*** task output ***".framework"*** folder.
 * @param localPathToSaveSpmArchive [String] local path to saving place for SPM archive.
 */
private fun Project.registerGenerateSpmArchiveTask(
    archiveName: String,
    pathToXcFramework: String,
    localPathToSaveSpmArchive: String
) {
    tasks.register("generateSpmArchive", Zip::class.java) {
        dependsOn("assembleSharedReleaseXCFramework")

        println("===> XCFramework directory path: $pathToXcFramework")
        println("===> SPM archive directory path: $localPathToSaveSpmArchive")

        val dirToSave = file(localPathToSaveSpmArchive)

        archiveFileName.set("$archiveName.zip")
        destinationDirectory.set(dirToSave)
        from(pathToXcFramework)
    }
}