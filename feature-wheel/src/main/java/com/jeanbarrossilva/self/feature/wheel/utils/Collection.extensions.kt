package com.jeanbarrossilva.self.feature.wheel.utils

import com.jeanbarrossilva.self.feature.wheel.domain.FeatureArea

internal fun Collection<FeatureArea>.filterHasToDos(): List<FeatureArea> {
    return filter { area ->
        area.toDos.isNotEmpty()
    }
}
