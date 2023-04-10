package com.jeanbarrossilva.self.platform.ui.core.sheet

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.aurelius.ui.layout.background.Background
import com.jeanbarrossilva.aurelius.ui.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme
import com.jeanbarrossilva.self.platform.ui.utils.elevated

@Composable
internal fun Tongue(modifier: Modifier = Modifier) {
    val density = LocalDensity.current
    val color = SelfTheme.colors.elevated
    val dpSize = remember { DpSize(width = 56.dp, height = 4.dp) }
    val size = remember(dpSize) {
        with(density) {
            dpSize.toSize()
        }
    }
    val cornerRadiusAxis = SelfTheme.shapes.tiny.topStart.toPx(size, density)
    val cornerRadius = remember { CornerRadius(cornerRadiusAxis, cornerRadiusAxis) }

    Canvas(modifier.size(dpSize)) {
        drawRoundRect(color, cornerRadius = cornerRadius)
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun TonguePreview() {
    SelfTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            Tongue()
        }
    }
}
