package com.jeanbarrossilva.self.feature.wheel.ui.theme

import androidx.compose.runtime.Composable
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme
import com.jeanbarrossilva.aurelius.ui.theme.colors.Colors
import com.jeanbarrossilva.aurelius.ui.theme.text.Text

typealias SelfTheme = AureliusTheme

@Composable
fun SelfTheme(content: @Composable () -> Unit) {
    AureliusTheme(
        text = Text.default.copy(
            body = Text.default.body.copy(color = Colors.default.text.highlighted.copy(alpha = .8f))
        ),
        content = content
    )
}
