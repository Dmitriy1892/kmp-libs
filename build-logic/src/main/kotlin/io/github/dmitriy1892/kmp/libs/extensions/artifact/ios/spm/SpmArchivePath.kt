package io.github.dmitriy1892.kmp.libs.extensions.artifact.ios.spm

/**
 * Sealed class that provides two types of SPM archive path:
 * - [Local] - for local stored SPM archive.
 * - [Remote] - for published to remote storage SPM archive.
 *
 * @property localPath [String] path to generated SPM ***".zip"*** archive and ***"Package.swift"*** file.
 */
sealed class SpmArchivePath(open val localPath: String) {

    /**
     * Local stored SPM archive path wrapper class.
     *
     * @property localPath [String] path to generated SPM ***".zip"*** archive and ***"Package.swift"*** file.
     */
    class Local(override val localPath: String) : SpmArchivePath(localPath)

    /**
     * Path to remotely saved SPM archive.
     *
     * @property localPath [String] path to generated SPM ***".zip"*** archive and ***"Package.swift"*** file.
     * @property remoteUrl [String] URL for downloading a remotely saved SPM archive.
     * @constructor Create empty Remote
     */
    class Remote(
        override val localPath: String,
        val remoteUrl: String
    ) : SpmArchivePath(remoteUrl)
}