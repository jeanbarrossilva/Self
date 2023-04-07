package com.jeanbarrossilva.self.feature.wheel.ui.still.todo

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
import com.jeanbarrossilva.loadable.Loadable
import com.jeanbarrossilva.loadable.utils.ifLoaded
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureArea
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureToDo
import com.jeanbarrossilva.self.feature.wheel.utils.placeholder
import com.jeanbarrossilva.self.feature.wheel.utils.placeholder.PlaceholderSize
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme

@Composable
internal fun ToDo(
    loadable: Loadable<FeatureToDo>,
    onToggle: (isDone: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val contentAlpha = with(SelfTheme.colors.text) {
        loadable.ifLoaded { highlighted } ?: default
    }
        .alpha
    val isDone = remember(loadable) { loadable.ifLoaded(FeatureToDo::isDone) == true }
    val textDecoration =
        remember(loadable) { if (isDone) TextDecoration.LineThrough else TextDecoration.None }

    Row(
        modifier.alpha(contentAlpha),
        Arrangement.spacedBy(SelfTheme.sizes.spacing.medium),
        Alignment.CenterVertically
    ) {
        Checkbox(isChecked = isDone, onToggle, Modifier.size(24.dp))

        Text(
            loadable.ifLoaded(FeatureToDo::title).orEmpty(),
            Modifier.placeholder(PlaceholderSize.Text(), isVisible = loadable is Loadable.Loading),
            textDecoration = textDecoration
        )
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Suppress("SpellCheckingInspection")
private fun LoadingToDoPreview() {
    SelfTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            ToDo(Loadable.Loading())
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Suppress("SpellCheckingInspection")
private fun ToDoPreview() {
    SelfTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            ToDo(Loadable.Loaded(FeatureArea.sample.toDos.first()))
        }
    }
}

@Composable
private fun ToDo(loadable: Loadable<FeatureToDo>, modifier: Modifier = Modifier) {
    ToDo(loadable, onToggle = { }, modifier)
}
