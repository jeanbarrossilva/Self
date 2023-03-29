package com.jeanbarrossilva.self.platform.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme
import com.jeanbarrossilva.aurelius.ui.theme.colors.Colors
import com.jeanbarrossilva.aurelius.ui.theme.colors.layered.LayeredColorsDefaults
import com.jeanbarrossilva.aurelius.ui.theme.text.Text
import com.jeanbarrossilva.self.platform.ui.core.ime.local.ProvideImeController

typealias SelfTheme = AureliusTheme

@Composable
fun SelfTheme(content: @Composable () -> Unit) {
    val activity = LocalContext.current as? Activity

    AureliusTheme(
        colors = Colors.default.copy(
            container = LayeredColorsDefaults.container.copy(
                secondary = if (isSystemInDarkTheme()) Color(0xFF383838) else Color(0xFFF0F0F0),
                tertiary = if (isSystemInDarkTheme()) Color(0xFF202020) else Color(0xFFE9E9E9)
            ),
            content = LayeredColorsDefaults.content.copy(
                tertiary = if (isSystemInDarkTheme()) Color(0xFF9B9B9B) else Color(0xFFA0A0A0)
            )
        ),
        text = Text.default.copy(
            body = Text.default.body.copy(color = Colors.default.text.highlighted.copy(alpha = .8f))
        )
    ) {
        ProvideImeController(content)
    }
}
