package com.jeanbarrossilva.self.platform.ui.actionable

import android.content.res.Configuration
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.ui.actionable.button.Button
import com.jeanbarrossilva.self.platform.ui.actionable.Button as _Button
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme

@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val contentColor = SelfTheme.colors.content.secondary

    Button(
        onClick,
        modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = SelfTheme.colors.container.secondary,
            contentColor
        )
    ) {
        ProvideTextStyle(LocalTextStyle.current.copy(color = contentColor)) {
            content()
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun ButtonPreview() {
    SelfTheme {
        _Button(onClick = { }) {
            Text("Label")
        }
    }
}
