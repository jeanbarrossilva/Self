package com.jeanbarrossilva.self.feature.wheel.domain.wheel

import com.jeanbarrossilva.self.feature.wheel.domain.FeatureArea
import java.io.Serializable

data class FeatureWheel internal constructor(val name: String, val areas: List<FeatureArea>) :
    Serializable {
    val withoutToDos
        get() = copy(areas = areas.map { area -> area.copy(toDos = emptyList()) })

    companion object {
        val sample = FeatureWheel(name = "Roda da vida", FeatureArea.samples)
    }
}
