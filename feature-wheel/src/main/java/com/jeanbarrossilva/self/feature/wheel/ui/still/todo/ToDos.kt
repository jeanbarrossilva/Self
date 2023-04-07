package com.jeanbarrossilva.self.feature.wheel.ui.still.todo

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.ui.layout.background.Background
import com.jeanbarrossilva.aurelius.ui.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.loadable.Loadable
import com.jeanbarrossilva.loadable.type.SerializableList
import com.jeanbarrossilva.loadable.utils.serialize
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureArea
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureToDo
import com.jeanbarrossilva.self.feature.wheel.utils.placeholder
import com.jeanbarrossilva.self.feature.wheel.utils.placeholder.PlaceholderSize
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme

@Composable
fun ToDos(
    loadable: Loadable<SerializableList<FeatureArea>>,
    onToggle: (area: FeatureArea, toDo: FeatureToDo, isDone: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxWidth(), Arrangement.spacedBy(SelfTheme.sizes.spacing.huge)) {
        when (loadable) {
            is Loadable.Loading -> LoadingTitledToDos()
            is Loadable.Loaded -> LoadedTitledToDos(loadable.value, onToggle)
            is Loadable.Failed -> { }
        }
    }
}

@Composable
private fun @Suppress("Unused") ColumnScope.LoadingTitledToDos() {
    repeat(16) {
        TitledToDosColumn {
            Box(
                Modifier.placeholder(
                    PlaceholderSize.Text({ ToDosDefaults.titleTextStyle }),
                    isVisible = true
                )
            )

            repeat(4) {
                ToDo(Loadable.Loading(), onToggle = { })
            }
        }
    }
}

@Composable
private fun @Suppress("Unused") ColumnScope.LoadedTitledToDos(
    areas: List<FeatureArea>,
    onToggle: (area: FeatureArea, toDo: FeatureToDo, isDone: Boolean) -> Unit
) {
    areas.forEach { area ->
        LoadedTitledToDos(
            area,
            onToggle = { toDo, isDone -> onToggle(area, toDo, isDone) }
        )
    }
}

@Composable
private fun LoadedTitledToDos(
    area: FeatureArea,
    onToggle: (toDo: FeatureToDo, isDone: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    TitledToDosColumn(modifier) {
        Text(area.name, style = ToDosDefaults.titleTextStyle)

        Column(verticalArrangement = Arrangement.spacedBy(SelfTheme.sizes.spacing.small)) {
            area.toDos.sortedBy(FeatureToDo::isDone).forEach { toDo ->
                ToDo(Loadable.Loaded(toDo), onToggle = { isDone -> onToggle(toDo, isDone) })
            }
        }
    }
}

@Composable
private fun TitledToDosColumn(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier,
        verticalArrangement = Arrangement.spacedBy(SelfTheme.sizes.spacing.medium),
        content = content
    )
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun LoadingToDosPreview() {
    SelfTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            ToDos(Loadable.Loading())
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun LoadedToDosPreview() {
    SelfTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            ToDos(Loadable.Loaded(FeatureArea.samples.serialize()))
        }
    }
}

@Composable
private fun ToDos(
    loadable: Loadable<SerializableList<FeatureArea>>,
    modifier: Modifier = Modifier
) {
    ToDos(loadable, onToggle = { _, _, _ -> }, modifier)
}
