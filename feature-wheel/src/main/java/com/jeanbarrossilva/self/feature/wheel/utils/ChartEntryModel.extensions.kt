package com.jeanbarrossilva.self.feature.wheel.utils

import com.jeanbarrossilva.self.feature.wheel.domain.FeatureArea
import com.jeanbarrossilva.self.feature.wheel.domain.wheel.FeatureWheel
import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import com.patrykandpatrick.vico.core.entry.entryModelOf

internal fun entryModelOf(wheel: FeatureWheel): ChartEntryModel {
    val values = wheel.areas.map(FeatureArea::attention).toTypedArray()
    return entryModelOf(*values)
}
