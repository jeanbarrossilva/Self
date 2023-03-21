package com.jeanbarrossilva.self.wheel.core.domain

import java.io.Serializable

data class ToDo internal constructor(val title: String, val isDone: Boolean) : Serializable {
    init {
        assert(title.isNotBlank())
    }
}
