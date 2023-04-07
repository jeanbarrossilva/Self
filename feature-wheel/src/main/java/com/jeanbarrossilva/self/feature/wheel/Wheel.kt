package com.jeanbarrossilva.self.feature.wheel

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.ui.layout.background.Background
import com.jeanbarrossilva.aurelius.ui.layout.scaffold.FloatingActionButton
import com.jeanbarrossilva.aurelius.ui.layout.scaffold.Scaffold
import com.jeanbarrossilva.aurelius.utils.plus
import com.jeanbarrossilva.loadable.Loadable
import com.jeanbarrossilva.loadable.utils.collectAsState
import com.jeanbarrossilva.loadable.utils.map
import com.jeanbarrossilva.loadable.utils.serialize
import com.jeanbarrossilva.loadable.utils.valueOrNull
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureArea
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureToDo
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureWheel
import com.jeanbarrossilva.self.feature.wheel.ui.still.Chart
import com.jeanbarrossilva.self.feature.wheel.ui.still.todo.ToDos
import com.jeanbarrossilva.self.feature.wheel.utils.placeholder
import com.jeanbarrossilva.self.feature.wheel.utils.placeholder.PlaceholderSize
import com.jeanbarrossilva.self.platform.ui.layout.scaffold.TopAppBar
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme

@Composable
internal fun Wheel(viewModel: WheelViewModel, onEdit: () -> Unit, modifier: Modifier = Modifier) {
    val loadable by viewModel.getWheelLoadableFlow().collectAsState()
    Wheel(loadable, onToDoToggle = viewModel::toggleToDo, onEdit, modifier)
}

@Composable
internal fun Wheel(
    loadable: Loadable<FeatureWheel>,
    onToDoToggle: (area: FeatureArea, toDo: FeatureToDo, isDone: Boolean) -> Unit,
    onEdit: () -> Unit,
    modifier: Modifier = Modifier
) {
    val title = remember(loadable) { loadable.map(FeatureWheel::name).valueOrNull.orEmpty() }
    val toDosLoadable = remember(loadable) {
        loadable.map { wheel ->
            wheel.areas.serialize()
        }
    }

    Scaffold(
        modifier,
        floatingActionButton = {
            FloatingActionButton(onClick = onEdit, isVisible = loadable is Loadable.Loaded) {
                @Suppress("SpellCheckingInspection")
                Icon(Icons.Rounded.Edit, contentDescription = "Editar")
            }
        }
    ) {
        TopAppBar({ modifier, style ->
            Text(
                title,
                modifier.placeholder(
                    PlaceholderSize.Text({ style }),
                    isVisible = loadable is Loadable.Loading
                ),
                style = style
            )
        }) {
            Background {
                LazyColumn(
                    contentPadding = PaddingValues(SelfTheme.sizes.spacing.large) +
                        SelfTheme.sizes.margin.navigationBar + SelfTheme.sizes.margin.fab,
                    verticalArrangement = Arrangement.spacedBy(SelfTheme.sizes.spacing.huge)
                ) {
                    item {
                        Chart(loadable)
                    }

                    item {
                        ToDos(toDosLoadable, onToDoToggle)
                    }
                }
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun LoadingWheelPreview() {
    SelfTheme {
        Wheel(Loadable.Loading())
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun LoadedWheelPreview() {
    SelfTheme {
        Wheel(Loadable.Loaded(FeatureWheel.sample))
    }
}

@Composable
private fun Wheel(loadable: Loadable<FeatureWheel>, modifier: Modifier = Modifier) {
    Wheel(loadable, onToDoToggle = { _, _, _ -> }, onEdit = { }, modifier)
}
