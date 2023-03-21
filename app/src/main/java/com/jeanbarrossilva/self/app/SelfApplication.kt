package com.jeanbarrossilva.self.app

import android.app.Application
import com.jeanbarrossilva.self.feature.wheel.WheelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal class SelfApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        inject()
    }

    private fun inject() {
        startKoin {
            androidContext(this@SelfApplication)
            modules(WheelModule())
        }
    }
}
