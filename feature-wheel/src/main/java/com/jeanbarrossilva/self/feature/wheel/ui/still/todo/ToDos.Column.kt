package com.jeanbarrossilva.self.feature.wheel.ui.still

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme

@Composable
internal fun Column(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Column(modifier, Arrangement.spacedBy(SelfTheme.sizes.spacing.huge), content = content)
}

@Composable
@Preview
private fun ColumnPreview() {
    SelfTheme {
        Column {
            @Suppress("SpellCheckingInspection")
            Text("Conteúdo 1")

            @Suppress("SpellCheckingInspection")
            Text("Conteúdo 2")
        }
    }
}
