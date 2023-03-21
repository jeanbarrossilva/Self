package com.jeanbarrossilva.self.feature.wheel.utils

import com.jeanbarrossilva.self.feature.wheel.domain.FeatureArea
import com.jeanbarrossilva.self.wheel.core.domain.Area

internal fun Area.feature(): FeatureArea {
    return FeatureArea(name, attention, toDos = emptyList())
}
