package com.jeanbarrossilva.self.platform.ui.layout.scaffold

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme
import com.jeanbarrossilva.self.platform.ui.utils.elevated
import com.jeanbarrossilva.self.platform.ui.utils.fontSize
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
fun TopAppBar(
    title: @Composable (Modifier, TextStyle) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val scaffoldState = rememberCollapsingToolbarScaffoldState()

    CollapsingToolbarScaffold(
        modifier,
        scaffoldState,
        ScrollStrategy.EnterAlways,
        toolbar = {
            val titleModifier = Modifier
                .road(whenCollapsed = Alignment.CenterStart, whenExpanded = Alignment.BottomStart)
                .padding(SelfTheme.sizes.spacing.large)
                .fillMaxSize()
            val baseTitleTextStyle =
                SelfTheme.text.headline.copy(color = SelfTheme.colors.text.highlighted)
            val titleFontSize = scaffoldState.toolbarState.fontSize(
                collapsed = SelfTheme.text.title.large.fontSize,
                expanded = baseTitleTextStyle.fontSize
            )
            val resizedTitleTextStyle = baseTitleTextStyle.copy(fontSize = titleFontSize)

            Spacer(Modifier.height(56.dp))

            Box(
                Modifier
                    .pin()
                    .background(SelfTheme.colors.elevated)
                    .padding(SelfTheme.sizes.margin.statusBar)
                    .height(128.dp)
                    .fillMaxWidth()
            )

            title(titleModifier, resizedTitleTextStyle)
        }
    ) {
        content()
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun TopAppBarPreview() {
    SelfTheme {
        TopAppBar({ modifier, style -> Text("Title", modifier, style = style) }) {
        }
    }
}
