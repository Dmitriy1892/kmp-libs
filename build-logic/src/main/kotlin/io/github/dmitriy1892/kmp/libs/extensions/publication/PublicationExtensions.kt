package io.github.dmitriy1892.kmp.libs.extensions.publication

import io.github.dmitriy1892.kmp.libs.extensions.getCommandLineProperty
import io.github.dmitriy1892.kmp.libs.extensions.getSecretProperties
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.plugins.ExtraPropertiesExtension
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.gradle.plugins.signing.SigningExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

private const val KEY_SIGNING_KEY_ID = "signing.keyId"
private const val KEY_SIGNING_PASSWORD = "signing.password"
private const val KEY_SIGNING_SECRET_KEY_RING_FILE = "signing.secretKeyRingFile"
private const val KEY_OSSRH_USERNAME = "ossrhUsername"
private const val KEY_OSSRH_PASSWORD = "ossrhPassword"
private const val KEY_DEVELOPER_ID = "developerId"
private const val KEY_DEVELOPER_NAME = "developerName"
private const val KEY_DEVELOPER_EMAIL = "developerEmail"

private val Project.ext: ExtraPropertiesExtension
    get() = extensions.getByName("ext") as ExtraPropertiesExtension

private val Project.publishing: PublishingExtension
    get() = (this as org.gradle.api.plugins.ExtensionAware).extensions.getByName("publishing") as PublishingExtension

private fun Project.publishing(configure: Action<PublishingExtension>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("publishing", configure)

private fun Project.signing(configure: Action<SigningExtension>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("signing", configure)

fun Project.setMavenPublishConfig(
    libraryName: String,
    libraryDescription: String,
    librarySourceUrl: String,
    libraryGroupId: String,
    libraryVersion: String,
    licenseUrl: String,
    libraryVariants: List<String> = listOf("release", "debug"),
    licenseName: String = "Apache-2.0",
    artifactRepositoryName: String = "sonatype",
    artifactRepositoryUrl: String = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
) {
    loadSigningProperties()

    group = libraryGroupId
    version = libraryVersion

    configure<KotlinMultiplatformExtension> {
        androidTarget {
            publishLibraryVariants(*libraryVariants.toTypedArray())
        }
    }

    publishing {
        repositories {
            maven {
                name = artifactRepositoryName
                setUrl(artifactRepositoryUrl)

                credentials {
                    username = ext[KEY_OSSRH_USERNAME]?.toString()
                    password = ext[KEY_OSSRH_PASSWORD]?.toString()
                }
            }
        }

        publications.withType<MavenPublication> {
            pom {
                name.set(libraryName)
                description.set(libraryDescription)
                url.set(librarySourceUrl)

                licenses {
                    license {
                        name.set(licenseName)
                        url.set(licenseUrl)
                    }
                }

                developers {
                    developer {
                        id.set(ext[KEY_DEVELOPER_ID]?.toString())
                        name.set(ext[KEY_DEVELOPER_NAME]?.toString())
                        email.set(ext[KEY_DEVELOPER_EMAIL]?.toString())
                    }
                }

                scm {
                    url.set(librarySourceUrl)
                }
            }
        }
    }

    signing {
        sign(publishing.publications)
    }
}

private fun Project.loadSigningProperties() {
    // tries to find properties in command-line arguments
    ext[KEY_SIGNING_KEY_ID] = getCommandLineProperty(KEY_SIGNING_KEY_ID)
    ext[KEY_SIGNING_PASSWORD] = getCommandLineProperty(KEY_SIGNING_PASSWORD)
    ext[KEY_SIGNING_SECRET_KEY_RING_FILE] = getCommandLineProperty(KEY_SIGNING_SECRET_KEY_RING_FILE)
    ext[KEY_OSSRH_USERNAME] = getCommandLineProperty(KEY_OSSRH_USERNAME)
    ext[KEY_OSSRH_PASSWORD] = getCommandLineProperty(KEY_OSSRH_PASSWORD)
    ext[KEY_DEVELOPER_ID] = getCommandLineProperty(KEY_DEVELOPER_ID)
    ext[KEY_DEVELOPER_NAME] = getCommandLineProperty(KEY_DEVELOPER_NAME)
    ext[KEY_DEVELOPER_EMAIL] = getCommandLineProperty(KEY_DEVELOPER_EMAIL)

    val signingProperties = getSecretProperties()

    signingProperties.onEach { (key, value) ->
        // if property wasn't passed as command-line parameter,
        // it will be set with parameter from "signing.properties" file.
        val property = ext[key.toString()] as? String
        if (property.isNullOrBlank()) {
            ext[key.toString()] = value
        }
    }
}