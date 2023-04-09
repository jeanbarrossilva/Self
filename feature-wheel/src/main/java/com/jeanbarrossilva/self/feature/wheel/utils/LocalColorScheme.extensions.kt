package com.jeanbarrossilva.self.feature.wheel.utils

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.ProvidableCompositionLocal

internal val LocalColorScheme
    @Suppress("UNCHECKED_CAST")
    get() = Class
        .forName("androidx.compose.material3.ColorSchemeKt")
        ?.getDeclaredField("LocalColorScheme")
        ?.apply { isAccessible = true }
        ?.get(null)
        ?.let { it as ProvidableCompositionLocal<ColorScheme> }
        ?: throw IllegalStateException("Could not get androidx.compose.material3.LocalColorScheme.")
