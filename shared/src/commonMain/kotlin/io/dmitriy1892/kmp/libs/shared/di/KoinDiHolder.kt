package io.dmitriy1892.kmp.libs.shared.di

import io.github.dmitriy1892.kmp.libs.utils.coroutines.mutex.RestrictedMutexApi
import io.github.dmitriy1892.kmp.libs.utils.coroutines.mutex.withThreadLock
import io.github.dmitriy1892.kmp.libs.utils.logger.KmpLogger
import io.github.dmitriy1892.kmp.libs.utils.platform.PlatformConfiguration
import kotlinx.coroutines.sync.Mutex
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.ksp.generated.module
import kotlin.jvm.JvmStatic
import kotlin.native.concurrent.ThreadLocal

class KoinDiHolder private constructor(koinApp: KoinApplication) {

    val koin: Koin by lazy { koinApp.koin }

    @ThreadLocal
    companion object {
        private var INSTANCE: KoinDiHolder? = null
        private val mutex = Mutex()

        @OptIn(RestrictedMutexApi::class)
        @JvmStatic
        fun getInstance(platformConfiguration: PlatformConfiguration): KoinDiHolder {
            return mutex.withThreadLock {
                INSTANCE ?: run {
                    KmpLogger.initialize()

                    val koinApp = startKoin {
                        modules(
                            module { single { platformConfiguration } },
                            KoinScannerModule().module
                        )
                    }

                    INSTANCE = KoinDiHolder(koinApp)
                    INSTANCE!!
                }
            }
        }

        fun getKoin(platformConfiguration: PlatformConfiguration): Koin =
            getInstance(platformConfiguration).koin
    }
}