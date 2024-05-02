package io.github.dmitriy1892.kmp.libs.decompose.lifecycle.compose

import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.create
import com.arkivanov.essenty.lifecycle.destroy
import com.arkivanov.essenty.lifecycle.pause
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.essenty.lifecycle.start
import com.arkivanov.essenty.lifecycle.stop

object IosLifecycleHandler {

    val lifecycle = LifecycleRegistry()

    fun onCreate(): Unit = lifecycle.create()

    fun onStart(): Unit = lifecycle.start()

    fun onResume(): Unit = lifecycle.resume()

    fun onPause(): Unit = lifecycle.pause()

    fun onStop(): Unit = lifecycle.stop()

    fun onDestroy(): Unit = lifecycle.destroy()

}