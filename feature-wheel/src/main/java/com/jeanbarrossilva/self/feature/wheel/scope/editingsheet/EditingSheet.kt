package com.jeanbarrossilva.self.feature.wheel.scope.editingsheet

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.self.feature.wheel.ui.layout.sheet.Sheet
import com.jeanbarrossilva.self.feature.wheel.ui.still.EditingButtons
import com.jeanbarrossilva.self.feature.wheel.ui.theme.SelfTheme

@Composable
internal fun EditingSheet(
    onEditAreas: () -> Unit,
    onAddToDo: () -> Unit,
    modifier: Modifier = Modifier
) {
    Sheet(modifier) {
        LazyColumn(
            contentPadding = it,
            verticalArrangement = Arrangement.spacedBy(SelfTheme.sizes.spacing.large)
        ) {
            item {
                Text(
                    "Editar",
                    color = SelfTheme.colors.text.highlighted,
                    style = SelfTheme.text.title.large
                )
            }

            item {
                EditingButtons(onEditAreas, onAddToDo)
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun EditingSheetPreview() {
    SelfTheme {
        EditingSheet(onEditAreas = { }, onAddToDo = { })
    }
}
