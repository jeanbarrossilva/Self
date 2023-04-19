package com.jeanbarrossilva.self.platform.ui.core.sheet

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.aurelius.utils.end
import com.jeanbarrossilva.aurelius.utils.start
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme
import com.jeanbarrossilva.self.platform.ui.utils.top

@Composable
fun Sheet(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    isContentPadded: Boolean = true,
    content: @Composable () -> Unit
) {
    Sheet(modifier) {
        LazyColumn(
            Modifier.fillMaxWidth(),
            contentPadding = if (isContentPadded) SheetDefaults.padding else PaddingValues(0.dp),
            verticalArrangement = Arrangement.spacedBy(SelfTheme.sizes.spacing.large)
        ) {
            item {
                ProvideTextStyle(
                    SelfTheme.text.title.large.copy(SelfTheme.colors.text.highlighted)
                ) {
                    if (!isContentPadded) {
                        Box(
                            Modifier.padding(
                                start = SheetDefaults.padding.start,
                                top = SheetDefaults.padding.calculateTopPadding(),
                                end = SheetDefaults.padding.end
                            )
                        ) {
                            title()
                        }
                    } else {
                        title()
                    }
                }
            }

            item {
                content()
            }
        }
    }
}

@Composable
fun Sheet(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column {
        Box(
            Modifier
                .padding(SelfTheme.sizes.spacing.small)
                .fillMaxWidth(),
            Alignment.Center
        ) {
            Tongue()
        }

        Card(
            modifier.fillMaxWidth(),
            SelfTheme.shapes.large.top,
            CardDefaults.cardColors(SheetDefaults.containerColor),
            CardDefaults.cardElevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                focusedElevation = 0.dp,
                hoveredElevation = 0.dp,
                draggedElevation = 0.dp,
                disabledElevation = 0.dp
            )
        ) {
            content()
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun UntitledSheetPreview() {
    SelfTheme {
        Sheet {
            @Suppress("SpellCheckingInspection")
            Text("Conteúdo", Modifier.padding(SheetDefaults.padding))
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun TitledSheetPreview() {
    SelfTheme {
        Sheet(
            title = {
                @Suppress("SpellCheckingInspection")
                Text("Título")
            }
        ) {
            @Suppress("SpellCheckingInspection")
            Text("Conteúdo")
        }
    }
}
