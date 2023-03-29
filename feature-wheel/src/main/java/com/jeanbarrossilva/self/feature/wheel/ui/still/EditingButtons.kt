package com.jeanbarrossilva.self.feature.wheel.ui.still

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.ui.layout.background.Background
import com.jeanbarrossilva.aurelius.ui.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.self.platform.ui.actionable.Button
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme

@Composable
internal fun EditingButtons(
    onEditAreas: () -> Unit,
    onAddToDo: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier, Arrangement.spacedBy(SelfTheme.sizes.spacing.small)) {
        Button(onClick = onEditAreas, Modifier.fillMaxWidth()) {
            Text("Adicionar/remover áreas")
        }

        Button(onClick = onAddToDo, Modifier.fillMaxWidth()) {
            Text("Adicionar tarefa")
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun EditingButtonsPreview() {
    SelfTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            EditingButtons(onEditAreas = { }, onAddToDo = { })
        }
    }
}
