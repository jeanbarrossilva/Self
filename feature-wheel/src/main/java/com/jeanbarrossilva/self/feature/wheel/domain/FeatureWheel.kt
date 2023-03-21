package com.jeanbarrossilva.self.feature.wheel.domain

import java.io.Serializable

internal data class FeatureWheel(val name: String, val areas: List<FeatureArea>) : Serializable {
    companion object {
        val sample = FeatureWheel(name = "Roda da vida", FeatureArea.samples)
    }
}
