package com.jeanbarrossilva.self.feature.wheel.scope.editing

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.aurelius.utils.copy
import com.jeanbarrossilva.self.feature.wheel.scope.editing.ui.actionable.Button
import com.jeanbarrossilva.self.platform.ui.core.sheet.Sheet
import com.jeanbarrossilva.self.platform.ui.core.sheet.SheetDefaults
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme

@Composable
internal fun EditingSheet(
    onToDoCompositionRequest: () -> Unit,
    onEditRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()

    Sheet(
        title = {
            @Suppress("SpellCheckingInspection")
            Text("Editar")
        },
        modifier,
        isContentPadded = false
    ) {
        LazyRow(
            Modifier.fillMaxWidth(),
            lazyListState,
            SheetDefaults.padding.copy(top = 0.dp),
            horizontalArrangement = Arrangement
                .spacedBy(SelfTheme.sizes.spacing.medium, Alignment.CenterHorizontally)
        ) {
            item {
                Button(onClick = onToDoCompositionRequest) {
                    @Suppress("SpellCheckingInspection")
                    Text("Adicionar afazer")
                }
            }

            item {
                Button(onClick = onEditRequest) {
                    @Suppress("SpellCheckingInspection")
                    Text("Modificar áreas")
                }
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun EditingSheetPreview() {
    SelfTheme {
        EditingSheet(onToDoCompositionRequest = { }, onEditRequest = { })
    }
}
