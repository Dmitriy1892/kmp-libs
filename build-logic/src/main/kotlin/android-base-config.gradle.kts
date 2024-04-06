import com.android.build.gradle.BaseExtension
import com.github.dmitriy1892.kmp.libs.extensions.libsCatalog
import com.github.dmitriy1892.kmp.libs.extensions.projectJavaVersion

configure<BaseExtension> {
    compileSdkVersion(libsCatalog.versions.compileSdk.get().toInt())

    defaultConfig {
        minSdk = libsCatalog.versions.minSdk.get().toInt()
        targetSdk = libsCatalog.versions.targetSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = projectJavaVersion
        targetCompatibility = projectJavaVersion
    }

    sourceSets["main"].apply {
        kotlin.srcDirs("src/androidMain/kotlin", "src/androidMain/java", "build/generated/ksp/**")
        java.srcDirs("src/androidMain/kotlin", "src/androidMain/java")
        manifest.srcFile("src/androidMain/AndroidManifest.xml")
        res.srcDirs("src/androidMain/resources", "src/androidMain/res")
        resources.srcDirs("src/commonMain/resources")
    }

    packagingOptions {
        resources {
            excludes += listOf(
                "META-INF/{AL2.0,LGPL2.1}",
                "META-INF/DEPENDENCIES",
                "META-INF/DEPENDENCIES.txt",
                "META-INF/LICENSE",
                "META-INF/LICENSE.txt",
                "META-INF/LICENSE-FIREBASE.txt",
                "META-INF/NOTICE",
                "META-INF/NOTICE.txt",
                "META-INF/*.kotlin_module",
                "META-INF/versions/9/previous-compilation-data.bin"
            )
        }
    }
}