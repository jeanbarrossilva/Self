package com.jeanbarrossilva.self.app.demo

import android.app.Application
import android.content.Context
import androidx.preference.PreferenceManager
import com.jeanbarrossilva.self.app.BuildConfig
import com.jeanbarrossilva.self.app.utils.register
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureWheel
import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import com.jeanbarrossilva.self.wheel.core.infra.WheelRegister
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

internal object Demo {
    fun start(application: Application) {
        if (!BuildConfig.DEBUG || hasAlreadyBeenStarted(application)) return
        addSampleWheel(application)
    }

    private fun addSampleWheel(application: Application) {
        val register = application.get<WheelRegister>()
        val editor = application.get<WheelEditor>()
        val wheel = FeatureWheel.sample
        MainScope().launch { register.register(editor, wheel) }
    }

    private fun hasAlreadyBeenStarted(context: Context): Boolean {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(
            "has_demo_started",
            false
        )
    }
}
