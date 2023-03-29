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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.ui.layout.background.Background
import com.jeanbarrossilva.aurelius.ui.layout.scaffold.FloatingActionButton
import com.jeanbarrossilva.aurelius.ui.layout.scaffold.Scaffold
import com.jeanbarrossilva.aurelius.utils.plus
import com.jeanbarrossilva.loadable.utils.collectAsState
import com.jeanbarrossilva.loadable.utils.valueOrNull
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureArea
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureToDo
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureWheel
import com.jeanbarrossilva.self.feature.wheel.ui.still.Chart
import com.jeanbarrossilva.self.feature.wheel.ui.still.ToDos
import com.jeanbarrossilva.self.platform.ui.layout.scaffold.TopAppBar
import com.jeanbarrossilva.self.platform.ui.still.Container
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme

@Composable
internal fun Wheel(viewModel: WheelViewModel, onEdit: () -> Unit, modifier: Modifier = Modifier) {
    viewModel.wheelFlow.collectAsState().value.valueOrNull?.let {
        Wheel(it, onToDoToggle = viewModel::toggleToDo, onEdit, modifier)
    }
}

@Composable
internal fun Wheel(
    wheel: FeatureWheel,
    onToDoToggle: (area: FeatureArea, toDo: FeatureToDo, isDone: Boolean) -> Unit,
    onEdit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier,
        floatingActionButton = {
            FloatingActionButton(onClick = onEdit) {
                Icon(Icons.Rounded.Edit, contentDescription = "Editar")
            }
        }
    ) {
        TopAppBar({ modifier, style -> Text(wheel.name, modifier, style = style) }) {
            Background {
                LazyColumn(
                    contentPadding = PaddingValues(SelfTheme.sizes.spacing.large) +
                        SelfTheme.sizes.margin.navigationBar + SelfTheme.sizes.margin.fab,
                    verticalArrangement = Arrangement.spacedBy(SelfTheme.sizes.spacing.huge)
                ) {
                    item {
                        Container {
                            Chart(wheel)
                        }
                    }

                    item {
                        ToDos(wheel.areas, onToDoToggle)
                    }
                }
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun WheelPreview() {
    SelfTheme {
        Wheel(FeatureWheel.sample, onToDoToggle = { _, _, _ -> }, onEdit = { })
    }
}
