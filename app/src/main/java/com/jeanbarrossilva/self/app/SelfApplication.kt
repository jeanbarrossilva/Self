package com.jeanbarrossilva.self.app

import android.app.Application
import com.jeanbarrossilva.self.app.demo.Demo
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal class SelfApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        inject()
        demo()
    }

    private fun inject() {
        startKoin {
            androidContext(this@SelfApplication)
            modules(SelfModule())
        }
    }

    private fun demo() {
        Demo.start(this)
    }
}
