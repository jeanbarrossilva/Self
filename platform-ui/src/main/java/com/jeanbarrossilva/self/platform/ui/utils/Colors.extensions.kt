package com.jeanbarrossilva.self.platform.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.jeanbarrossilva.aurelius.ui.theme.colors.Colors
import com.jeanbarrossilva.self.platform.ui.R

val @receiver:Suppress("Unused") Colors.elevated
    @Composable get() = colorResource(R.color.platform_ui_elevated)

val Colors.placeholder
    @Composable get() = container.secondary
