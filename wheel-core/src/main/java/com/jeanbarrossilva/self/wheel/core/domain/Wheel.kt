package com.jeanbarrossilva.self.wheel.core.domain

import java.io.Serializable

data class Wheel(val name: String, val areas: List<Area> = emptyList()) : Serializable {
    init {
        assert(name.isNotBlank())
    }

    operator fun get(areaName: String): Area? {
        return areas.find {
            it.name == areaName
        }
    }
}
