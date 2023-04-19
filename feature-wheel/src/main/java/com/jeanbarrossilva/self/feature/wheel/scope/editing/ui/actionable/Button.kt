package com.jeanbarrossilva.self.feature.wheel.scope.editing.ui.actionable

import android.content.res.Configuration
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme
import com.jeanbarrossilva.self.feature.wheel.scope.editing.ui.actionable.Button as _Button
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme

@Composable
internal fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick,
        modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = AureliusTheme.colors.container.tertiary,
            contentColor = AureliusTheme.colors.content.tertiary
        ),
        content = content
    )
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun ButtonPreview() {
    SelfTheme {
        _Button(onClick = { }) {
            Text("Rótulo")
        }
    }
}
