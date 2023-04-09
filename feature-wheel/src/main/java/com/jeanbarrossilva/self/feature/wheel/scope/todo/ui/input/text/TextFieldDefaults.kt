package com.jeanbarrossilva.self.feature.wheel.scope.todo.ui.input.text

import androidx.compose.runtime.Composable
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme

internal object TextFieldDefaults {
    val shape
        @Composable get() = SelfTheme.shapes.medium
    val containerColor
        @Composable get() = SelfTheme.colors.container.tertiary
    val contentColor
        @Composable get() = SelfTheme.colors.content.tertiary
}
