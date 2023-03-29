package com.jeanbarrossilva.self.wheel.core.domain

import java.io.Serializable

data class Area(val name: String, val attention: Float, val toDos: List<ToDo> = emptyList()) :
    Serializable {
    init {
        assert(name.isNotBlank())
        assert(attention in MIN_ATTENTION..MAX_ATTENTION)
    }

    companion object {
        const val MIN_ATTENTION = 0f
        const val MAX_ATTENTION = 1f
    }
}
