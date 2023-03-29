package com.jeanbarrossilva.self.wheel.android.utils

import com.jeanbarrossilva.self.wheel.android.infra.WheelDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope

val Scope.database: WheelDatabase
    get() {
        val context = androidContext()
        return WheelDatabase.get(context)
    }
