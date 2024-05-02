package io.github.dmitriy1892.kmp.libs.decompose.navigation.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.ObserveLifecycleMode
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.subscribe
import io.github.dmitriy1892.kmp.libs.decompose.navigation.core.internal.StubViewModelFactory
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

private typealias DestinationClass = Destination

/**
 * Stack navigation graph class. Holds and manages a navigation stack.
 *
 * @param Config configuration type of navigation stack. Must be [Serializable].
 * This type can contain a navigation arguments.
 * @param Destination type that describes or wraps a [DestinationClass].
 * @param componentContext [ComponentContext]
 */
abstract class NavGraph<Config: @Serializable Any, Destination: Any>(
    componentContext: ComponentContext
) : DestinationClass(componentContext) {

    /**
     * Initial config - [Config] of the first screen in a navigation stack.
     */
    protected abstract val initialConfig: Config

    /**
     * Base config serializer.
     */
    protected abstract val  configSerializer: KSerializer<Config>

    /**
     * View model factory for creating a [ViewModel] instance.
     * [StubViewModelFactory] by default.
     * Default implementation will throw an [UnsupportedOperationException] if you can
     * try to call [decomposeViewModel] function.
     */
    protected override val viewModelFactory: Lazy<ViewModelProvider.Factory> =
        lazy { StubViewModelFactory }

    /**
     * Boolean that indicates a setting of **"Back"** button/gesture handling of that nav graph.
     */
    protected open val handleBackButton: Boolean = true

    /**
     * Navigation stack of opened [Config]'s.
     */
    protected val navigation = StackNavigation<Config>()

    /**
     * Child stack. Initializes and manages a stack of components.
     */
    protected val childStack: Value<ChildStack<*, Destination>> by lazy {
        childStack(
            source = navigation,
            serializer = configSerializer,
            initialConfiguration = initialConfig,
            handleBackButton = handleBackButton,
            childFactory = ::createDestination
        )
    }

    /**
     * Function for observing a child stack changes.
     *
     * @param mode [ObserveLifecycleMode] describes what is
     * the [Lifecycle][com.arkivanov.essenty.lifecycle.Lifecycle] state must be for automatically
     * subscribing and unsubscribing a [childStack] current [Destination] observing.
     *
     * @param onReceive lambda that receives a current [Destination].
     */
    fun observeChildStack(
        mode: ObserveLifecycleMode = ObserveLifecycleMode.START_STOP,
        onReceive: (currentDestination: Destination) -> Unit
    ): Unit = childStack.subscribe(lifecycle, mode) { childStack ->
        onReceive(childStack.active.instance)
    }

    /**
     * Factory function for creating a destination
     *
     * @param config
     * @param componentContext
     * @return [Destination]
     */
    protected abstract fun createDestination(
        config: Config,
        componentContext: ComponentContext
    ): Destination
}