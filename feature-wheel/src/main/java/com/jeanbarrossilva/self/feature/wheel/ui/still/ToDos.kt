package com.jeanbarrossilva.self.feature.wheel.ui.still

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.ui.layout.background.Background
import com.jeanbarrossilva.aurelius.ui.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureArea
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureToDo
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme

@Composable
internal fun ToDos(
    areas: List<FeatureArea>,
    onToggle: (area: FeatureArea, toDo: FeatureToDo, isDone: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier, Arrangement.spacedBy(SelfTheme.sizes.spacing.huge)) {
        areas.forEach { area ->
            ToDos(area, onToggle = { toDo, isDone -> onToggle(area, toDo, isDone) })
        }
    }
}

@Composable
internal fun ToDos(
    area: FeatureArea,
    onToggle: (toDo: FeatureToDo, isDone: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier,
        verticalArrangement = Arrangement.spacedBy(SelfTheme.sizes.spacing.medium)
    ) {
        Text(area.name, style = SelfTheme.text.title.large)

        Column(verticalArrangement = Arrangement.spacedBy(SelfTheme.sizes.spacing.small)) {
            area.toDos.sortedBy(FeatureToDo::isDone).forEach { toDo ->
                ToDo(toDo, onToggle = { isDone -> onToggle(toDo, isDone) })
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun ToDosPreview() {
    SelfTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            ToDos(FeatureArea.sample, onToggle = { _, _ -> })
        }
    }
}
