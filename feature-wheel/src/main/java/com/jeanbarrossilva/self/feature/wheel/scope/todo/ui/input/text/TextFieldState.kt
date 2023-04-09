package com.jeanbarrossilva.self.feature.wheel.scope.todo.ui.input.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme

internal sealed class TextFieldState {
    open val content: (@Composable (Modifier) -> Unit)? = null

    object Idle : TextFieldState()

    object Valid : TextFieldState()

    data class Invalid(val message: String) : TextFieldState() {
        override val content: @Composable (Modifier) -> Unit
            get() = {
                ErrorContainer(it) {
                    Row(
                        horizontalArrangement = Arrangement
                            .spacedBy(SelfTheme.sizes.spacing.medium),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        @Suppress("SpellCheckingInspection")
                        Text(message)
                    }
                }
            }
    }
}
