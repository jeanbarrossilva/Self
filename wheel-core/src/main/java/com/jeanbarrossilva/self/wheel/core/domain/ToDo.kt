package com.jeanbarrossilva.self.wheel.core.domain

import java.io.Serializable

data class ToDo(val title: String, val isDone: Boolean) : Serializable {
    init {
        assert(title.isNotBlank())
    }
}
