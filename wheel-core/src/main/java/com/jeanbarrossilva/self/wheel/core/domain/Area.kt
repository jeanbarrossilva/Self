package com.jeanbarrossilva.self.wheel.core.domain

import java.io.Serializable

data class Area(val name: String, val attention: Float, val toDos: List<ToDo> = emptyList()) :
    Serializable {
    init {
        assert(name.isNotBlank())
        assert(attention in 0f..1f)
    }
}
