package io.dmitriy1892.kmp.libs.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import io.dmitriy1892.kmp.libs.shared.ui.App

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val defaultComponentContext = defaultComponentContext()

        setContent {
            App(defaultComponentContext = defaultComponentContext)
        }
    }
}

