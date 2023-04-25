package com.jeanbarrossilva.self.wheel.core.domain.todo

import java.io.Serializable

data class ToDo(val title: String, val isDone: Boolean = false) : Serializable {
    init {
        assert(title.isNotBlank())
    }
}
