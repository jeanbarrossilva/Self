package com.jeanbarrossilva.self.feature.wheel.ui.still

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.R
import com.jeanbarrossilva.aurelius.ui.layout.background.Background
import com.jeanbarrossilva.aurelius.ui.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureWheel
import com.jeanbarrossilva.self.feature.wheel.ui.theme.SelfTheme
import com.jeanbarrossilva.self.feature.wheel.utils.entryModelOf
import com.jeanbarrossilva.self.feature.wheel.utils.typefaceResource
import com.patrykandpatrick.vico.compose.axis.axisLabelComponent
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart

@Composable
internal fun Chart(wheel: FeatureWheel, modifier: Modifier = Modifier) {
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
            valueFormatter = { index, _ -> wheel.areas[index.toInt()].name }
        )
    )
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun ChartPreview() {
    SelfTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            Chart(FeatureWheel.sample)
        }
    }
}
