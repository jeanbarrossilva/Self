package com.jeanbarrossilva.self.feature.wheel.utils

import androidx.compose.material3.Shapes
import androidx.compose.runtime.ProvidableCompositionLocal

internal val LocalShapes
    @Suppress("UNCHECKED_CAST")
    get() = Class
        .forName("androidx.compose.material3.ShapesKt")
        ?.getDeclaredField("LocalShapes")
        ?.apply { isAccessible = true }
        ?.get(null)
        ?.let { it as ProvidableCompositionLocal<Shapes> }
        ?: throw IllegalStateException("Could not get androidx.compose.material3.LocalShapes.")
