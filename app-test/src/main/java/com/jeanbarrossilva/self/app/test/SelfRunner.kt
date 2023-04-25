package com.jeanbarrossilva.self.app.test

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class SelfRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, Application::class.qualifiedName, context)
    }
}
