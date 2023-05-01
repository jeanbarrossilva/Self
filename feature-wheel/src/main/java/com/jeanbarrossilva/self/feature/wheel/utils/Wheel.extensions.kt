package com.jeanbarrossilva.self.feature.wheel.utils

import com.jeanbarrossilva.self.feature.wheel.domain.wheel.FeatureWheel
import com.jeanbarrossilva.self.wheel.core.domain.area.Area
import com.jeanbarrossilva.self.wheel.core.domain.wheel.Wheel

internal fun Wheel.feature(): FeatureWheel {
    val areas = areas.map(Area::feature)
    return FeatureWheel(name, areas)
}
