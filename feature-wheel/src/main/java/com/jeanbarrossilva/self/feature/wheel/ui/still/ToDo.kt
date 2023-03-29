package com.jeanbarrossilva.self.feature.wheel.ui.still

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.aurelius.ui.input.Checkbox
import com.jeanbarrossilva.aurelius.ui.layout.background.Background
import com.jeanbarrossilva.aurelius.ui.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureArea
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureToDo
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme

@Composable
internal fun ToDo(
    toDo: FeatureToDo,
    onToggle: (isDone: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val contentAlpha =
        with(SelfTheme.colors.text) { if (toDo.isDone) default else highlighted }.alpha
    val textDecoration = remember(toDo) {
        if (toDo.isDone) TextDecoration.LineThrough else TextDecoration.None
    }

    Row(
        modifier.alpha(contentAlpha),
        Arrangement.spacedBy(SelfTheme.sizes.spacing.medium),
        Alignment.CenterVertically
    ) {
        Checkbox(isChecked = toDo.isDone, onToggle, Modifier.size(24.dp))
        Text(toDo.title, textDecoration = textDecoration)
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Suppress("SpellCheckingInspection")
private fun ToDoPreview() {
    SelfTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            ToDo(FeatureArea.sample.toDos.first(), onToggle = { })
        }
    }
}
