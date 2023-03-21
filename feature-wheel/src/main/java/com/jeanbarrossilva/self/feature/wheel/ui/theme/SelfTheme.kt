package com.jeanbarrossilva.self.feature.wheel.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme
import com.jeanbarrossilva.aurelius.ui.theme.colors.Colors
import com.jeanbarrossilva.aurelius.ui.theme.colors.layered.LayeredColorsDefaults
import com.jeanbarrossilva.aurelius.ui.theme.text.Text

typealias SelfTheme = AureliusTheme

@Composable
fun SelfTheme(content: @Composable () -> Unit) {
    AureliusTheme(
        colors = Colors.default.copy(
            container = LayeredColorsDefaults.container.copy(
                secondary = if (isSystemInDarkTheme()) Color(0xFF181818) else Color(0xFFF0F0F0)
            )
        ),
        text = Text.default.copy(
            body = Text.default.body.copy(color = Colors.default.text.highlighted.copy(alpha = .8f))
        ),
        content = content
    )
}
