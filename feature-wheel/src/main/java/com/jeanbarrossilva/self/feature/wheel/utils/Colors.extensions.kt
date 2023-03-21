package com.jeanbarrossilva.self.feature.wheel.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.jeanbarrossilva.aurelius.ui.theme.colors.Colors

internal val Colors.elevated
    @Composable get() = if (isSystemInDarkTheme()) container.secondary else background
