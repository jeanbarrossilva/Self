package com.jeanbarrossilva.self.wheel.core.domain

import java.io.Serializable

data class Area internal constructor(val name: String, val attention: Float) : Serializable {
    init {
        assert(name.isNotBlank())
        assert(attention in 0f..1f)
    }
}
