package com.jeanbarrossilva.self.platform.ui.still

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.aurelius.ui.layout.background.Background
import com.jeanbarrossilva.aurelius.ui.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme
import com.jeanbarrossilva.self.platform.ui.utils.elevated

@Composable
fun Container(
    modifier: Modifier = Modifier,
    shape: Shape = SelfTheme.shapes.small,
    containerColor: Color = SelfTheme.colors.elevated,
    borderColor: Color = SelfTheme.colors.container.tertiary,
    content: @Composable BoxScope.() -> Unit
) {
    Card(
        modifier,
        shape,
        colors = CardDefaults.cardColors(containerColor),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            focusedElevation = 0.dp,
            hoveredElevation = 0.dp,
            draggedElevation = 0.dp,
            disabledElevation = 0.dp
        ),
        border = BorderStroke(1.dp, borderColor)
    ) {
        Box(Modifier.padding(SelfTheme.sizes.spacing.medium)) {
            CompositionLocalProvider(
                LocalContentColor provides SelfTheme.colors.content.secondary
            ) {
                content()
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun ContainerPreview() {
    SelfTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            Container {
                Text("Content")
            }
        }
    }
}
