import io.github.dmitriy1892.kmp.libs.extensions.androidOptions
import io.github.dmitriy1892.kmp.libs.extensions.buildComposeMetricsParameters
import io.github.dmitriy1892.kmp.libs.extensions.implementation
import io.github.dmitriy1892.kmp.libs.extensions.libs
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

androidOptions {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.androidx.compose)
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        freeCompilerArgs.set(buildComposeMetricsParameters())
    }
}