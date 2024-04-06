import com.github.dmitriy1892.kmp.libs.extensions.projectDirPath
import com.github.dmitriy1892.kmp.libs.extensions.registerIncrementVersionCatalogPropertyTask

plugins {
    id("build-logic-plugin").apply(false)
}

registerIncrementVersionCatalogPropertyTask(
    taskName = "incrementMvvmLibVersion",
    propertyKey = "kmp-libs-mvvm",
    pathToIncrementScriptFile = "$projectDirPath/../scripts"
)