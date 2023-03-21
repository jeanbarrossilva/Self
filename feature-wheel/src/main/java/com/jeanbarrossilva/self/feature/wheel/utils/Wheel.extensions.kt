package com.jeanbarrossilva.self.feature.wheel.utils

import com.jeanbarrossilva.self.feature.wheel.domain.FeatureWheel
import com.jeanbarrossilva.self.wheel.core.domain.Area
import com.jeanbarrossilva.self.wheel.core.domain.Wheel

internal fun Wheel.feature(): FeatureWheel {
    val areas = areas.map(Area::feature)
    return FeatureWheel(name, areas)
}
