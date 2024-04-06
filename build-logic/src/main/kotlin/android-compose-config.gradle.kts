import com.github.dmitriy1892.kmp.libs.extensions.androidOptions
import com.github.dmitriy1892.kmp.libs.extensions.buildComposeMetricsParameters
import com.github.dmitriy1892.kmp.libs.extensions.implementation
import com.github.dmitriy1892.kmp.libs.extensions.libsCatalog
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

androidOptions {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libsCatalog.versions.androidx.compose.compiler.get()
    }
}

dependencies {
    implementation(platform(libsCatalog.androidx.compose.bom))
    implementation(libsCatalog.bundles.androidx.compose)
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        freeCompilerArgs.set(buildComposeMetricsParameters())
    }
}