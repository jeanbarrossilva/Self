package com.jeanbarrossilva.self.feature.wheel.ui.still

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.aurelius.R
import com.jeanbarrossilva.loadable.Loadable
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureWheel
import com.jeanbarrossilva.self.feature.wheel.utils.entryModelOf
import com.jeanbarrossilva.self.feature.wheel.utils.typefaceResource
import com.jeanbarrossilva.self.platform.ui.still.Container
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme
import com.patrykandpatrick.vico.compose.axis.axisLabelComponent
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart

@Composable
internal fun Chart(wheelLoadable: Loadable<FeatureWheel>, modifier: Modifier = Modifier) {
    Container(modifier) {
        when (wheelLoadable) {
            is Loadable.Loading -> LoadingChart()
            is Loadable.Loaded -> LoadedChart(wheelLoadable.value)
            is Loadable.Failed -> {}
        }
    }
}

@Composable
private fun BoxScope.LoadingChart(modifier: Modifier = Modifier) {
    Box(
        modifier
            .height(160.dp)
            .fillMaxWidth()
    ) {
        CircularProgressIndicator(with(this@LoadingChart) { Modifier.align(Alignment.Center) })
    }
}

@Composable
private fun LoadedChart(wheel: FeatureWheel, modifier: Modifier = Modifier) {
    Chart(
        lineChart(),
        entryModelOf(wheel),
        modifier,
        startAxis(
            axisLabelComponent(typeface = typefaceResource(R.font.dm_sans_bold)),
            valueFormatter = { attention, _ -> "${attention.times(100).toInt()}%" }
        ),
        bottomAxis = bottomAxis(
            axisLabelComponent(typeface = typefaceResource(R.font.dm_sans_bold)),
            valueFormatter = { index, _ ->
                val indexAsInt = index.toInt()
                wheel.areas.elementAtOrNull(indexAsInt)?.name.orEmpty()
            }
        )
    )
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun LoadingChartPreview() {
    SelfTheme {
        Chart(Loadable.Loading())
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun LoadedChartPreview() {
    SelfTheme {
        Chart(Loadable.Loaded(FeatureWheel.sample))
    }
}
