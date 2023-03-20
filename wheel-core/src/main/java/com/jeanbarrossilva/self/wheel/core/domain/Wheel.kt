package com.jeanbarrossilva.self.wheel.core.domain

import java.io.Serializable

data class Wheel internal constructor(val name: String, val areas: List<Area> = emptyList()) :
    Serializable {
    init {
        assert(name.isNotBlank())
    }
}
