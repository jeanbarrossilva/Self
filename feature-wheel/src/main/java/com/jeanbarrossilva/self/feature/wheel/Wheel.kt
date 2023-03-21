package com.jeanbarrossilva.self.feature.wheel

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.ui.layout.background.Background
import com.jeanbarrossilva.aurelius.utils.plus
import com.jeanbarrossilva.loadable.utils.collectAsState
import com.jeanbarrossilva.loadable.utils.ifLoaded
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureArea
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureToDo
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureWheel
import com.jeanbarrossilva.self.feature.wheel.ui.layout.scaffold.TopAppBar
import com.jeanbarrossilva.self.feature.wheel.ui.still.Chart
import com.jeanbarrossilva.self.feature.wheel.ui.still.Container
import com.jeanbarrossilva.self.feature.wheel.ui.still.ToDos
import com.jeanbarrossilva.self.feature.wheel.ui.theme.SelfTheme
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
internal fun Wheel(viewModel: WheelViewModel, modifier: Modifier = Modifier) {
    viewModel.wheel.collectAsState().value.ifLoaded {
        Wheel(this, onToDoToggle = { _, _, _ -> }, modifier)
    }
}

@Composable
internal fun Wheel(
    wheel: FeatureWheel,
    onToDoToggle: (area: FeatureArea, toDo: FeatureToDo, isDone: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val collapsingToolbarScaffoldState = rememberCollapsingToolbarScaffoldState()

    CollapsingToolbarScaffold(
        modifier,
        collapsingToolbarScaffoldState,
        ScrollStrategy.EnterAlways,
        toolbar = { TopAppBar(collapsingToolbarScaffoldState.toolbarState, wheel) }
    ) {
        Background {
            LazyColumn(
                contentPadding = PaddingValues(SelfTheme.sizes.spacing.large) +
                    SelfTheme.sizes.margin.navigationBar,
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

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun WheelPreview() {
    SelfTheme {
        Wheel(FeatureWheel.sample, onToDoToggle = { _, _, _ -> })
    }
}
