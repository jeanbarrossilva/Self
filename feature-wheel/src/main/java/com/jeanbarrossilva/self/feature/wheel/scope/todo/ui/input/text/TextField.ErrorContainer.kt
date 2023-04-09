package com.jeanbarrossilva.self.feature.wheel.scope.todo.ui.input.text

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.self.platform.ui.still.Container
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme

@Composable
internal fun ErrorContainer(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Container(
        modifier,
        TextFieldDefaults.shape,
        containerColor = if (isSystemInDarkTheme()) Color(0xFFB42C2C) else Color(0xFFDA4949),
        borderColor = if (isSystemInDarkTheme()) Color(0xFFB93434) else Color(0xFFD85454)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(SelfTheme.sizes.spacing.medium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            @Suppress("SpellCheckingInspection")
            Icon(Icons.Rounded.Warning, contentDescription = "Erro")

            content()
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun ErrorContainerPreview() {
    SelfTheme {
        ErrorContainer {
            @Suppress("SpellCheckingInspection")
            Text("Ocorreu um erro.")
        }
    }
}
