package com.jeanbarrossilva.self.feature.wheel

import android.content.res.Configuration
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import com.jeanbarrossilva.aurelius.ui.layout.background.Background
import com.jeanbarrossilva.aurelius.ui.layout.scaffold.FloatingActionButton
import com.jeanbarrossilva.aurelius.utils.`if`
import com.jeanbarrossilva.aurelius.utils.plus
import com.jeanbarrossilva.aurelius.utils.toDp
import com.jeanbarrossilva.aurelius.utils.toDpSize
import com.jeanbarrossilva.loadable.Loadable
import com.jeanbarrossilva.loadable.contentOrNull
import com.jeanbarrossilva.loadable.list.serialize
import com.jeanbarrossilva.loadable.map
import com.jeanbarrossilva.self.feature.wheel.core.loadable.LoadableList
import com.jeanbarrossilva.self.feature.wheel.core.loadable.flatMap
import com.jeanbarrossilva.self.feature.wheel.core.placeholder.PlaceholderSize
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureArea
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureToDo
import com.jeanbarrossilva.self.feature.wheel.domain.wheel.FeatureWheel
import com.jeanbarrossilva.self.feature.wheel.ui.still.Chart
import com.jeanbarrossilva.self.feature.wheel.ui.still.todo.ToDos
import com.jeanbarrossilva.self.feature.wheel.utils.placeholder
import com.jeanbarrossilva.self.feature.wheel.utils.toLoadableList
import com.jeanbarrossilva.self.feature.wheel.utils.vertical
import com.jeanbarrossilva.self.platform.ui.layout.scaffold.TopAppBar
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme

@Composable
internal fun Wheel(
    viewModel: WheelViewModel,
    onEditRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    val loadable by viewModel.wheelLoadableFlow.collectAsState()
    Wheel(loadable, onToDoToggle = viewModel::toggleToDo, onEditRequest, modifier)
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun Wheel(
    loadable: Loadable<FeatureWheel>,
    onToDoToggle: (area: FeatureArea, toDo: FeatureToDo, isDone: Boolean) -> Unit,
    onEditRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val fabMargin = SelfTheme.sizes.margin.fab
    val navigationBarMargin = SelfTheme.sizes.margin.navigationBar
    val isFabVisible = remember(loadable) { loadable is Loadable.Loaded }
    val title = remember(loadable) { loadable.map(FeatureWheel::name).contentOrNull.orEmpty() }
    val lazyColumnState = rememberLazyListState()
    val lazyColumnHeight =
        remember(lazyColumnState) { lazyColumnState.layoutInfo.viewportSize.height.toDp(density) }
    val areasLoadable = remember(loadable) {
        loadable.map { wheel -> wheel.areas.serialize() }
    }
    val toDosLoadable = remember(areasLoadable) {
        areasLoadable.toLoadableList().flatMap {
            it.toDos.serialize()
        }
    }
    var toDosTop by remember { mutableStateOf(Dp.Unspecified) }
    var toDosHeight by remember { mutableStateOf(Dp.Unspecified) }
    val toDosOffsetY by animateDpAsState(
        if (
            toDosTop.isSpecified &&
            toDosHeight.isSpecified &&
            lazyColumnHeight > 0.dp &&
            toDosLoadable is LoadableList.Empty
        ) {
            (lazyColumnHeight - toDosTop) / 2 - toDosHeight / 2 - fabMargin.vertical - navigationBarMargin.vertical
        } else {
            0.dp
        },
        SelfTheme.animation.spec { fast }
    )

    Scaffold(
        modifier,
        floatingActionButton = {
            FloatingActionButton(onClick = onEditRequest, isVisible = isFabVisible) {
                @Suppress("SpellCheckingInspection")
                Icon(Icons.Rounded.Edit, contentDescription = "Adicionar afazer")
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
                    Modifier.fillMaxSize(),
                    lazyColumnState,
                    contentPadding = it
                        .plus(PaddingValues(SelfTheme.sizes.spacing.large))
                        .plus(navigationBarMargin)
                        .`if`(isFabVisible) { plus(fabMargin) },
                    verticalArrangement = Arrangement.spacedBy(SelfTheme.sizes.spacing.huge)
                ) {
                    item {
                        Chart(loadable)
                    }

                    item {
                        ToDos(
                            areasLoadable,
                            onToDoToggle,
                            Modifier
                                .offset(y = toDosOffsetY)
                                .onPlaced { coordinates ->
                                    toDosTop = coordinates.positionInParent().y.toDp(density)
                                    toDosHeight = coordinates.size.toDpSize(density).height
                                }
                        )
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
private fun LoadedWheelWithoutToDosPreview() {
    SelfTheme {
        Wheel(Loadable.Loaded(FeatureWheel.sample.withoutToDos))
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun LoadedWheelWithToDosPreview() {
    SelfTheme {
        Wheel(Loadable.Loaded(FeatureWheel.sample))
    }
}

@Composable
private fun Wheel(loadable: Loadable<FeatureWheel>, modifier: Modifier = Modifier) {
    Wheel(loadable, onToDoToggle = { _, _, _ -> }, onEditRequest = { }, modifier)
}
